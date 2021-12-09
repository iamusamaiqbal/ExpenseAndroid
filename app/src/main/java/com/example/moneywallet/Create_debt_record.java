package com.example.moneywallet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneywallet.models.RecordModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Create_debt_record extends AppCompatActivity {

    TextView amount;
    ImageView save,back;
    SQLiteHandler database;
    Date date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_debt_record);

        amount = findViewById(R.id.newDebtAmount);
        save = findViewById(R.id.newDebtSave);
        back = findViewById(R.id.newDebtBack);

        database = new SQLiteHandler(this);
        date = new Date();

        Intent intent = getIntent();
        String id = intent.getStringExtra("did");

        String dateInString = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
        LocalDate currentDate = LocalDate.parse(dateInString, formatter);

        DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern( "E dd" );
        String output = currentDate.format( fLocalDate) ;

        save.setOnClickListener(v -> {
            int am = Integer.parseInt(amount.getText().toString());
            if( am > 0){
                boolean f = database.addRecord(new RecordModel("DEC","Cash",Integer.parseInt(id),am,output));
                onBackPressed();
            }
        });

    }
}