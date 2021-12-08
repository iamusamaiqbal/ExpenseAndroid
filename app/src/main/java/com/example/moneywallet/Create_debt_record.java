package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneywallet.models.RecordModel;

public class Create_debt_record extends AppCompatActivity {

    TextView amount;
    ImageView save,back;
    SQLiteHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_debt_record);

        amount = findViewById(R.id.newDebtAmount);
        save = findViewById(R.id.newDebtSave);
        back = findViewById(R.id.newDebtBack);

        database = new SQLiteHandler(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("did");

        save.setOnClickListener(v -> {
            int am = Integer.parseInt(amount.getText().toString());
            if( am > 0){
                boolean f = database.addRecord(new RecordModel("DEC","Cash",Integer.parseInt(id),am));
                onBackPressed();
            }
        });

    }
}