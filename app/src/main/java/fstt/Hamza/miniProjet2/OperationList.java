package fstt.Hamza.miniProjet2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OperationList extends ArrayAdapter {
    ArrayList<Operation> transactions;
    public OperationList(@NonNull Context context, int resource, @NonNull ArrayList<Operation> objects) {
        super(context, resource, objects);
        transactions = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cutsom_transaction_list, parent, false);
        }
        TextView prodNum,prodName,prodQte,prodDate;
        prodName = convertView.findViewById(R.id.prodTranscName);
        prodQte = convertView.findViewById(R.id.prodTranscQte);
        prodNum  = convertView.findViewById(R.id.prodNum);
        prodDate = convertView.findViewById(R.id.dateOperation);
        prodNum.setText(String.valueOf(getItem(position).getOperation_Id()));
        prodName.setText(String.valueOf(getItem(position).getArticle_Name()));
        prodQte.setText(String.valueOf(getItem(position).getArticle_Qte()));
        prodDate.setText(getItem(position).getDateOperation());
        return  convertView;
    }
    @Nullable
    @Override
    public Operation getItem(int position) {
        return transactions.get(position);
    }

}
