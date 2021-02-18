package fstt.Hamza.miniProjet2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterArticle.ShowDialog {
    ListView listView;
    Button ajouter ;
    Button Lister ;
    ArrayList<Article> products  = new ArrayList();
    MyDbHelper database;
    AdapterArticle adapterStrore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listStore);
        ajouter = findViewById(R.id.addarticle);
        Lister = findViewById(R.id.showTranscations);
        database = new MyDbHelper(this);
        products = database.getAllArticles();
        adapterStrore = new AdapterArticle(this,R.layout.custom_list_store,products);
        listView.setAdapter(adapterStrore);

        ajouter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),add_article_activity.class);
                startActivityForResult(intent,1);

            }
        });

        Lister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentShow = new Intent(getApplicationContext(),Transaction_activity.class);
                startActivity(intentShow);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                    Article product = (Article) data.getSerializableExtra("newArticle");
                    try{
                        long prodId = database.addArticle(product);
                        product.setArticle_Id((int) prodId);
                        products.add(product);
                        adapterStrore.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"Article est ajouté avec succée",Toast.LENGTH_SHORT).show();
                    }catch(SQLException e){
                        Toast.makeText(getApplicationContext(),"Article a mal ajoutée",Toast.LENGTH_SHORT).show();
                    }
            }else{

                Toast.makeText(getApplicationContext(),"réssayer une autre fois",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void display(Article prd) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(true);
        EditText qte = dialog.findViewById(R.id.qteSelected);
        Button btn  = dialog.findViewById(R.id.addQte);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qteValue = qte.getText().toString().trim();

                if(qteValue.equals("")){
                    qte.setError("SVP ajouter la Quantité");
                    return;
                }
                dialog.dismiss();

                if( prd.getArticle_Qte() >= Integer.parseInt(qteValue)){
                    try{
                        long idTrans = database.addOperation(prd,Integer.parseInt(qteValue));
                        Toast.makeText(getApplicationContext(),"operation est ajouté avec succée",Toast.LENGTH_SHORT).show();
                        int prodId = products.indexOf(prd);
                        int newQte  = prd.getArticle_Qte() - Integer.parseInt(qteValue);
                        products.get(prodId).setArticle_Qte(newQte);
                        adapterStrore.notifyDataSetChanged();

                    }catch(SQLException e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"opération est échouée",Toast.LENGTH_SHORT).show();
                    }


                }else{

                    Toast.makeText(getApplicationContext(),"la quantité est insuffisante",Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();

    }

}