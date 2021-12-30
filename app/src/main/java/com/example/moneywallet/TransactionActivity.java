package com.example.moneywallet;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneywallet.models.TransactionModel;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    int expanse, income, result;

    TextView cashTV;
    SwipeableRecyclerView recyclerView;
    SQLiteHandler database;
    TransactionAdapter adapter;
    List<TransactionModel> transactionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        recyclerView = findViewById(R.id.TransactionRecycle);
        cashTV = findViewById(R.id.CashTextview);

        database = new SQLiteHandler(this);

        transactionList = database.getAllTransaction();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter(this,transactionList);
        recyclerView.setAdapter(adapter);

        recyclerView.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

                int id = transactionList.get(position).id;

                boolean f = database.deleteTransaction(id);

                Log.e("eee","jjjjjjjjjjj"+position);
                adapter.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onSwipedRight(int position) {
            }
        });

        income = database.getSum(SQLiteHandler.KEY_ISEXPENSE);
        expanse = database.getSum(SQLiteHandler.KEY_ISINCOME);

        result = income-expanse;

        cashTV.setText("Cash : "+result);
    }
}