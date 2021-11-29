package com.example.moneywallet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class new_budget extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText budgetName, budgetAmount;
    ImageView saveBudget;
    ImageButton imageButton_cross;
    String currency, category, account;
    Spinner spinner_1, spinner3, spinner4, spinner5;

    SQLiteHandler database;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget);
        spinner_1 = findViewById(R.id.spinner_1);
        imageButton_cross=findViewById(R.id.imageButton_cross);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.accountSpinner12);
        budgetName = findViewById(R.id.editTextName);
        budgetAmount = findViewById(R.id.editTextNumberSigned);
        saveBudget = findViewById(R.id.saveBudget);

        imageButton_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(new_budget.this,Budget.class);
                startActivity(intent);
            }
        });


        database = new SQLiteHandler(this);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.number,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);
        spinner_1.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapteerr = ArrayAdapter.createFromResource(this, R.array.currency,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapteerr);
        spinner3.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapteerrr = ArrayAdapter.createFromResource(this, R.array.categories,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapteerrr);
        spinner4.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapteeerrr = ArrayAdapter.createFromResource(this, R.array.account,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapteeerrr);
        spinner5.setOnItemSelectedListener(this);

        currency = spinner3.getSelectedItem().toString();
        account = spinner5.getSelectedItem().toString();
        category = spinner4.getSelectedItem().toString();

        Date date = new Date();

        String dateInString = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
        LocalDate currentDate = LocalDate.parse(dateInString, formatter);
        LocalDate currentDatePlus7 = currentDate.plusDays(7);
        LocalDate currentDatePlus30 = currentDate.plusDays(30);
        LocalDate currentDatePlus365 = currentDate.plusDays(365);


        saveBudget.setOnClickListener(v -> {
            Log.e("yy","yyyyyyyyyyy");
            if (!budgetName.getText().toString().isEmpty()) {

                if (!budgetAmount.getText().toString().isEmpty()) {
                    database.addBudget(new BudgetModel(budgetName.getText().toString(), Integer.parseInt(budgetAmount.getText().toString()), 3, account, currency, currentDate.toString(), currentDatePlus7.toString()));
                    onBackPressed();
                } else {
                    Toast.makeText(this, "Amount cannot be Zero or less", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "Name is Empty", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}