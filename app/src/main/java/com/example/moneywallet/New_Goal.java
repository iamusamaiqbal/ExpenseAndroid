package com.example.moneywallet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class New_Goal extends AppCompatActivity {
    EditText name, amount,alreadySaved,note;
    Button cv;
    ImageView saveGoal;
    SQLiteHandler database;
    Calendar calendar = Calendar.getInstance();
    int year,month,dayOfMonth;
    DatePickerDialog datePickerDialog;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);
        name = findViewById(R.id.goalName);
        amount = findViewById(R.id.goalAmount);
        alreadySaved = findViewById(R.id.goalAlreadySaved);
        note = findViewById(R.id.goalNote);
        saveGoal = findViewById(R.id.saveGoal);
        cv= findViewById(R.id.calendar_btn);

        database = new SQLiteHandler(this);
        Date date = new Date();

        String dateInString = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
        LocalDate currentDate = LocalDate.parse(dateInString, formatter);

        cv.setOnClickListener(v -> datePicker());

        saveGoal.setOnClickListener(v -> {
            int amt = (amount.getText().length()>0)? Integer.parseInt(amount.getText().toString()): 0;
            if(name.getText().length()>4){

                if (!amount.getText().toString().isEmpty()){
                    if(amt>0){

                        database.addGoal(new GoalModel(name.getText().toString(),note.getText().toString(),currentDate.toString(),amt,Integer.parseInt(alreadySaved.getText().toString()),"active"));
                        onBackPressed();

                    } else {
                        Toast.makeText(this, "Amount should be greater than zero", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Amount is empty", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "Name should be greater than 4", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void datePicker() {
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this,this::onDateSet, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //date.setText((month+1) + "-" + dayOfMonth + "-" + year);
    }
}