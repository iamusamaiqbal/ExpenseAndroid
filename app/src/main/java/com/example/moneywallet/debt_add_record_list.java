package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneywallet.models.DebtModel;
import com.example.moneywallet.models.RecordModel;
import com.example.moneywallet.models.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class debt_add_record_list extends AppCompatActivity {


    RecyclerView debtRV;
    RecordAdapter recordAdapter;
    SQLiteHandler database;
    List<RecordModel> recordList = new ArrayList<>();
    ImageButton debtBack;
    String id;
    DebtModel debtModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_add_record_list);
        debtBack = findViewById(R.id.debtBack);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.textView43);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.calculater)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        debtBack = findViewById(R.id.debtBack);
        debtRV = findViewById(R.id.debtRV);
        database = new SQLiteHandler(this);

        Intent i = getIntent();
        id = i.getStringExtra("did");

        debtBack.setOnClickListener(v -> {
            onBackPressed();
        });

        recordList = database.getAllRecord(id);
        debtModel = database.getDebt(id);


        RecordModel[] recordArray = recordList.toArray(new RecordModel[0]);

        debtRV.setLayoutManager(new LinearLayoutManager(this));
        recordAdapter = new RecordAdapter(this, recordArray);
        debtRV.setAdapter(recordAdapter);


    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.debt_menu, menu);
        ((MenuBuilder) menu).setOptionalIconsVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.forgive) {


            boolean f = database.updateDebt(new DebtModel(debtModel.name, debtModel.description, debtModel.account, debtModel.date, debtModel.duedate, debtModel.amount, debtModel.type, 0), id);
            if (f) {
                Toast.makeText(this, "Forgiven", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }

        } else if (item.getItemId() == R.id.edit_debit) {

            Intent i = new Intent(this, i_lent_record.class);
            i.putExtra("did", "" + id);
            i.putExtra("type", "" +debtModel.type);
            startActivity(i);

            Toast.makeText(this, "edit click", Toast.LENGTH_SHORT).show();


        } else if (item.getItemId() == R.id.delet_debt) {

            boolean f = database.deleteDebt(id);
            if (f) {
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }


        }
        return super.onOptionsItemSelected(item);

    }

}
