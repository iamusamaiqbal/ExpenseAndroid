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
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneywallet.models.DebtModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class i_lent_record extends AppCompatActivity {
    Spinner accountSpinner12;
    String type,id, enddate = "", startdate = "", datepicked;
    EditText name, amount, description;
    ImageView save, back;
    Button start, end;
    TextView debtType;
    SQLiteHandler database;

    Calendar calendar = Calendar.getInstance();
    int year, month, dayOfMonth;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilent_record);
        accountSpinner12 = findViewById(R.id.accountSpinner12);
        name = findViewById(R.id.debtName);
        amount = findViewById(R.id.debtAmount);
        description = findViewById(R.id.debtDescription);
        back = findViewById(R.id.debtBack);
        save = findViewById(R.id.debtSave);
        debtType = findViewById(R.id.debtType);
        start = findViewById(R.id.calendar_btn);
        end = findViewById(R.id.calendar_btn2);
        //account = findViewById(R.id.);

        database = new SQLiteHandler(this);

        Intent i = getIntent();
        type = i.getStringExtra("type");
        id = i.getStringExtra("did");

        String newStr = "I " + type.substring(0, 1).toUpperCase() + type.substring(1);

        debtType.setText(newStr);

        if(id!=null){
            DebtModel debtModel = database.getDebt(id);

            name.setText(debtModel.name);
            amount.setText(""+debtModel.amount);
            description.setText(debtModel.description);
            enddate=debtModel.duedate;
            startdate=debtModel.date;
            end.setText(enddate);
            start.setText(startdate);
        }

        start.setOnClickListener(v -> {
            datePicker();
            if (datepicked != "") {
                startdate = datepicked;
                datepicked="";
                start.setText(startdate);
            }
        });
        end.setOnClickListener(v -> {
            datePicker();
            if (datepicked != "") {
                enddate = datepicked;
                datepicked="";
                end.setText(enddate);
            }
        });

        back.setOnClickListener(v -> {
            onBackPressed();
        });

        save.setOnClickListener(v -> {
            int amt = (amount.getText().length() > 0) ? Integer.parseInt(amount.getText().toString()) : 0;

            if(id!=null){
                if (name.getText().length() > 4) {
                    if (description.getText().length() > 5) {
                        if (!amount.getText().toString().isEmpty()) {
                            if (amt > 0) {
                                if (startdate!="") {
                                    if (enddate!="") {

                                        boolean f = database.updateDebt(new DebtModel(name.getText().toString(), description.getText().toString(), "Cash", startdate, enddate, amt, type, 1),id);

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
            } else {
                if (name.getText().length() > 4) {
                    if (description.getText().length() > 5) {
                        if (!amount.getText().toString().isEmpty()) {
                            if (amt > 0) {
                                if (startdate!="") {
                                    if (enddate!="") {

                                        boolean f = database.addDebt(new DebtModel(name.getText().toString(), description.getText().toString(), "Cash", startdate, enddate, amt, type, 1));

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
            }
        });
    }

    public void datePicker() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DATE);
        datePickerDialog = new DatePickerDialog(this, this::onDateSet, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        datepicked = format.format(calendar.getTime());
    }

}