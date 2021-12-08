package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
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

import com.example.moneywallet.models.RecordModel;

import java.util.List;

public class debt_add_record_list extends AppCompatActivity {


    RecyclerView debtRV;
    SQLiteHandler database;
    List<RecordModel> recordList;
    ImageButton debtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_add_record_list);
        debtBack=findViewById(R.id.debtBack);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.textView43);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE , PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.calculater)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);





        debtBack = findViewById(R.id.debtBack);
        debtRV = findViewById(R.id.debtRV);
        database = new SQLiteHandler(this);

        Intent i = getIntent();
        String  id = i.getStringExtra("did");

        debtBack.setOnClickListener(v -> {
            onBackPressed();
        });

        recordList = database.getAllRecord(id);




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


            Toast.makeText(this, "clicked setting", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.edit_debit) {
            Toast.makeText(this, "edit click", Toast.LENGTH_SHORT).show();


        } else if (item.getItemId() == R.id.delet_debt) {
            Toast.makeText(this, "delete share", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);

    }

    }
