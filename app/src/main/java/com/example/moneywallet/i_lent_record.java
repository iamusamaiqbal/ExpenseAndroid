package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moneywallet.models.DebtModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class i_lent_record extends AppCompatActivity {
    Spinner accountSpinner12;
    String type;
    EditText name, amount,date,duedate, description, account;
    ImageView save, back;


    SQLiteHandler database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilent_record);
        accountSpinner12 = findViewById(R.id.accountSpinner12);
        name = findViewById(R.id.debtName);
        amount = findViewById(R.id.debtAmount);
        description = findViewById(R.id.debtDescription);
        date = findViewById(R.id.date);
        duedate = findViewById(R.id.duedate);
        back = findViewById(R.id.debtBack);
        save = findViewById(R.id.debtSave);
        //account = findViewById(R.id.);




        database = new SQLiteHandler(this);

        Intent i = getIntent();
        type = i.getStringExtra("type");

        back.setOnClickListener(v -> {
            onBackPressed();
        });

        save.setOnClickListener(v -> {
            int amt = (amount.getText().length() > 0) ? Integer.parseInt(amount.getText().toString()) : 0;

            if (name.getText().length() > 4) {
                if (description.getText().length() > 5) {
                    if (!amount.getText().toString().isEmpty()) {
                        if (amt > 0) {
                            if (!date.getText().toString().isEmpty()) {
                                if (!duedate.getText().toString().isEmpty()) {

                                    boolean f = database.addDebt(new DebtModel(name.getText().toString(), description.getText().toString(), "Cash", date.getText().toString(), duedate.getText().toString(), amt, type, 1));

                                    if (f) {
                                        Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
                                        onBackPressed();
                                    } else {
                                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(this, "Select due date", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(this, "Select date", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(this, "Amount should be greater than zero", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Amount is empty", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Description is not enough", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Name should be greater than 4", Toast.LENGTH_LONG).show();
            }
        });

    }


}