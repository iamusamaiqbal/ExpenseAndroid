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

import com.example.moneywallet.models.BudgetModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class new_budget extends AppCompatActivity {
    EditText budgetName, budgetAmount;
    ImageView saveBudget;
    ImageButton imageButton_cross;
    String currency, category, account, time;
    Spinner spinner_1, spinner3, spinner4, spinner5;
    String id;

    SQLiteHandler database;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget);
        spinner_1 = findViewById(R.id.spinner_1);
        imageButton_cross = findViewById(R.id.imageButton_cross);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.accountSpinner12);
        budgetName = findViewById(R.id.editTextName);
        budgetAmount = findViewById(R.id.editTextNumberSigned);
        saveBudget = findViewById(R.id.saveBudget);

        imageButton_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new_budget.this, Budget.class);
                startActivity(intent);
            }
        });


        database = new SQLiteHandler(this);

        Intent i = getIntent();
        id = i.getStringExtra("bid");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.number,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);
        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                time = text;
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });


        ArrayAdapter<CharSequence> adapteerr = ArrayAdapter.createFromResource(this, R.array.currency,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapteerr);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                currency = text;
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });


        ArrayAdapter<CharSequence> adapteerrr = ArrayAdapter.createFromResource(this, R.array.categories,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapteerrr);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                category = text;
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });

        ArrayAdapter<CharSequence> adapteeerrr = ArrayAdapter.createFromResource(this, R.array.account,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapteeerrr);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                account = text;
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });

        time = spinner_1.getSelectedItem().toString();
        currency = spinner3.getSelectedItem().toString();
        account = spinner5.getSelectedItem().toString();
        category = spinner4.getSelectedItem().toString();

        Date date = new Date();

        String dateInString = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
        LocalDate currentDate = LocalDate.parse(dateInString, formatter);
        LocalDate currentDatePlus7 = currentDate.plusDays(7);
        LocalDate currentDatePlusMonth = currentDate.plusMonths(1);
        LocalDate currentDatePlusYear = currentDate.plusYears(1);

        if (id != null) {
            BudgetModel budgetModel = database.getBudget(id);

            budgetName.setText(budgetModel.name);
            budgetAmount.setText("" + budgetModel.amount);

            LocalDate s = LocalDate.parse(budgetModel.start);
            LocalDate e = LocalDate.parse(budgetModel.end);

            int diff = (int) java.time.temporal.ChronoUnit.DAYS.between(s, e);

            switch (diff) {
                case 0:
                    spinner_1.setSelection(1);
                    break;
                case 7:
                    spinner_1.setSelection(2);
                    break;
                case 30:
                case 31:
                    spinner_1.setSelection(3);
                    break;
                case 365:
                case 366:
                    spinner_1.setSelection(4);
                    break;
                default:
                    Log.e("No Match", " non of these case matches");
            }

            spinner4.setSelection(adapteerrr.getPosition(budgetModel.category));

//            switch (Arrays.asList(s).indexOf(budgetModel.))
//            mSpnBaths.setSelection(Arrays.asList(baths).indexOf());
        }


        saveBudget.setOnClickListener(v -> {

            if(id!=null){
                if (validate()) {
                    if (time.equals("Week")) {
                        database.updateBudget(new BudgetModel(budgetName.getText().toString(), Integer.parseInt(budgetAmount.getText().toString()), category, account, currency, currentDate.toString(), currentDatePlus7.toString()),id);
                        onBackPressed();

                    } else if (time.equals("Month")) {
                        database.updateBudget(new BudgetModel(budgetName.getText().toString(), Integer.parseInt(budgetAmount.getText().toString()), category, account, currency, currentDate.toString(), currentDatePlusMonth.toString()),id);
                        onBackPressed();

                    } else if (time.equals("Year")) {
                        database.updateBudget(new BudgetModel(budgetName.getText().toString(), Integer.parseInt(budgetAmount.getText().toString()), category, account, currency, currentDate.toString(), currentDatePlusYear.toString()),id);
                        onBackPressed();
                    } else {
                        database.updateBudget(new BudgetModel(budgetName.getText().toString(), Integer.parseInt(budgetAmount.getText().toString()), category, account, currency, currentDate.toString(), currentDate.toString()),id);
                        onBackPressed();
                    }
                }
            } else {
                if (validate()) {
                    if (time.equals("Week")) {
                        database.addBudget(new BudgetModel(budgetName.getText().toString(), Integer.parseInt(budgetAmount.getText().toString()), category, account, currency, currentDate.toString(), currentDatePlus7.toString()));
                        onBackPressed();

                    } else if (time.equals("Month")) {
                        database.addBudget(new BudgetModel(budgetName.getText().toString(), Integer.parseInt(budgetAmount.getText().toString()), category, account, currency, currentDate.toString(), currentDatePlusMonth.toString()));
                        onBackPressed();

                    } else if (time.equals("Year")) {
                        database.addBudget(new BudgetModel(budgetName.getText().toString(), Integer.parseInt(budgetAmount.getText().toString()), category, account, currency, currentDate.toString(), currentDatePlusYear.toString()));
                        onBackPressed();
                    } else {
                        database.addBudget(new BudgetModel(budgetName.getText().toString(), Integer.parseInt(budgetAmount.getText().toString()), category, account, currency, currentDate.toString(), currentDate.toString()));
                        onBackPressed();
                    }
                }
            }
        });


    }

    boolean validate() {

        if (!budgetName.getText().toString().isEmpty()) {
            int amt = (!budgetAmount.getText().toString().isEmpty()) ? Integer.parseInt(budgetAmount.getText().toString()) : 0;
            if (amt > 0) {
                return true;
            } else {
                Toast.makeText(this, "Amount cannot be Zero or less", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Name is Empty", Toast.LENGTH_LONG).show();
            return false;
        }
    }

}