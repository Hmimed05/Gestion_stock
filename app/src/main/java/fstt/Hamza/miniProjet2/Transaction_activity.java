package fstt.Hamza.miniProjet2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Transaction_activity extends AppCompatActivity {
    ArrayList<Operation> operations = new ArrayList<>();
    ListView listView;
    MyDbHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        listView = findViewById(R.id.listTranscation);
        database = new MyDbHelper(this);
        operations = database.getAllOperations();
        listView.setAdapter(new OperationList(this,R.layout.cutsom_transaction_list,operations));

    }
}