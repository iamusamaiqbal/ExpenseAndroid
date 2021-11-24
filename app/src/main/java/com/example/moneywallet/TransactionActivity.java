package com.example.moneywallet;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    int expanse, income, result;

    TextView cashTV;
    RecyclerView recyclerView;
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

        TransactionModel[] transactionArray = transactionList.toArray(new TransactionModel[0]);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter(this,transactionArray);
        recyclerView.setAdapter(adapter);

        income = database.getSum(SQLiteHandler.KEY_ISEXPENSE);
        expanse = database.getSum(SQLiteHandler.KEY_ISINCOME);

        result = income-expanse;

        cashTV.setText("Cash : "+result);
    }
}