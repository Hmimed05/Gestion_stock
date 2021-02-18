package fstt.Hamza.miniProjet2;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterArticle extends ArrayAdapter implements View.OnClickListener {
    ArrayList<Article> products;
    Context context;
    ShowDialog  showDialog;

    public AdapterArticle(@NonNull Context context, int resource, @NonNull ArrayList<Article> objects) {
        super(context, resource, objects);
        products = objects;
        showDialog = (ShowDialog) context;
    }
    public interface  ShowDialog{
        public void display(Article prd);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_store,parent,false);
        }

        TextView article_Price,article_Qte,article_Name;
        Button purchaseBtn;
        article_Name = convertView.findViewById(R.id.prodName);
        article_Price = convertView.findViewById(R.id.prodPrice);
        article_Qte = convertView.findViewById(R.id.prodQte);
        purchaseBtn = convertView.findViewById(R.id.purchase);
        article_Name.setText(getItem(position).getArticle_Name());
        article_Price.setText(String.format("%s DH",String.valueOf(getItem(position).getArticlePrice())));
        article_Qte.setText(String.valueOf(getItem(position).getArticle_Qte()));
        purchaseBtn.setOnClickListener(this);
        purchaseBtn.setTag(String.valueOf(position));
        return convertView;
    }


    @Nullable
    @Override
    public Article getItem(int position) {
        return products.get(position);
    }

    @Override
    public void onClick(View v) {

        Article prod = getItem(Integer.parseInt((String) v.getTag()));
        showDialog.display(prod);
    }
}
