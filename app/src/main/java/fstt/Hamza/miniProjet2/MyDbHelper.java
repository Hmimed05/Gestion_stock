package fstt.Hamza.miniProjet2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(@Nullable Context context) {
        super(context, "gestion_articles",null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE articles(article_id INTEGER not null Primary key autoIncrement," +
                "article_name TEXT not null," +
                "article_price REAL NOT NULL," +
                "article_qte INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE operations(trans_id INTEGER not null Primary key autoIncrement," +
                "article_name TEXT not null," +
                "article_price REAL NOT NULL," +
                "qte INTEGER NOT NULL," +
                "dateOperation TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS articles");
        db.execSQL("DROP TABLE IF EXISTS operations");
        onCreate(db);
    }

    public long addArticle(Article article) throws SQLException {
        SQLiteDatabase  db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("article_name",article.getArticle_Name());
        contentValues.put("article_price",article.getArticlePrice());
        contentValues.put("article_qte",article.getArticle_Qte());
        return db.insertOrThrow("articles",null,contentValues);
    }


    public ArrayList<Article> getAllArticles() throws SQLException {
        SQLiteDatabase  db =  this.getReadableDatabase();
        ArrayList<Article> articles = new ArrayList<Article>();
        Cursor cursor = db.rawQuery("SELECT * FROM articles",null);
        while(cursor.moveToNext()){

            articles.add(new Article(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getInt(3)));
        }
        cursor.close();
        return  articles;
    }
    public long addOperation(Article article,int qte) throws SQLException {
        SQLiteDatabase  db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("article_qte",article.getArticle_Qte()- qte);
        contentValues.put("article_name",article.getArticle_Name());
        contentValues.put("article_price",article.getArticlePrice());
        contentValues.put("qte",qte);
        contentValues.put("dateOperation",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        long transId =  db.insertOrThrow("operations",null,contentValues);
        db.update("articles",
                contentValues2,
                "article_id = ?",
                new String[]{String.valueOf(article.getArticle_Id())});
        return  transId;
    }
    public ArrayList<Operation> getAllOperations() throws SQLException {
        SQLiteDatabase  db =  this.getReadableDatabase();
        ArrayList<Operation> operations = new ArrayList<Operation>();
        Cursor cursor = db.rawQuery("SELECT * FROM operations",null);
        while(cursor.moveToNext()){

            operations.add(new Operation(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getInt(3),
                    cursor.getString(4)));
        }
        cursor.close();
        return  operations;
    }

}
