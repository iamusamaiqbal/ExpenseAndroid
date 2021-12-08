package com.example.moneywallet;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
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
    private Adapter TransactionAdapter;

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


        final SwipeableRecyclerView TransactionRecycle = findViewById(R.id.TransactionRecycle);
        TransactionRecycle.setLayoutManager(new LinearLayoutManager(this));
        TransactionRecycle.setAdapter((RecyclerView.Adapter) TransactionAdapter);

        TransactionRecycle.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                transactionList.remove(position);
                ((RecyclerView.Adapter<?>) TransactionAdapter).notifyDataSetChanged();
                Snackbar.make(TransactionRecycle,
                        "Item " + position + " Marked As Read",
                        Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSwipedRight(int position) {
                transactionList.remove(position);
                ((RecyclerView.Adapter<?>) TransactionAdapter).notifyDataSetChanged();
                Snackbar.make(TransactionRecycle,
                        "Item " + position + " Removed",
                        Snackbar.LENGTH_LONG).show();
            }
        });

        /*rv.setRightBg(R.color.blue);
        rv.setRightImage(R.drawable.ic_v);
        rv.setRightText("Right Text");

        rv.setLeftBg(R.color.red);
        rv.setLeftImage(R.drawable.ic_trash);
        rv.setLeftText("Left Text");

        rv.setTextSize(62);
        rv.setTextColor(R.color.white);*/
    }

}
