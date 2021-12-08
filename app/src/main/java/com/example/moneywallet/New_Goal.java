package com.example.moneywallet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moneywallet.models.GoalModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class New_Goal extends AppCompatActivity {
    EditText name, amount,alreadySaved,note;
    String enddate="";
    Button cv;
    ImageView saveGoal;
    SQLiteHandler database;
    Calendar calendar = Calendar.getInstance();
    int year,month,dayOfMonth,id;
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
//        Date date = new Date();


        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("gid"));

//        String dateInString = date.toString();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
//        LocalDate currentDate = LocalDate.parse(dateInString, formatter);

        if(id>0){
            GoalModel goalModel = database.getGoal(id);
            if(goalModel != null){
                name.setText(goalModel.name);
                amount.setText(""+goalModel.amount);
                alreadySaved.setText(""+goalModel.alreadySaved);
                note.setText(goalModel.note);
                enddate=goalModel.date;
            }

        }

        cv.setOnClickListener(v -> datePicker());

        saveGoal.setOnClickListener(v -> {
            int amt = (amount.getText().length()>0)? Integer.parseInt(amount.getText().toString()): 0;
            if(id>0){
                if(name.getText().length()>4){

                    if (!amount.getText().toString().isEmpty()){
                        if( enddate!=""){
                            if(amt>0){

                                boolean f = database.updateGoal(new GoalModel(name.getText().toString(),note.getText().toString(),enddate,amt,Integer.parseInt(alreadySaved.getText().toString()),"active"),id);
                                if(f){
                                    Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
                                }
                                onBackPressed();

                            } else {
                                Toast.makeText(this, "Amount should be greater than zero", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(this, "Please select ending date", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Amount is empty", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this, "Name should be greater than 4", Toast.LENGTH_LONG).show();
                }

            } else{
                if(name.getText().length()>4){

                    if (!amount.getText().toString().isEmpty()){
                        if(!enddate.isEmpty()){
                            if(amt>0){

                                boolean f = database.addGoal(new GoalModel(name.getText().toString(),note.getText().toString(),enddate,amt,Integer.parseInt(alreadySaved.getText().toString()),"active"));
                                if(f){
                                    Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
                                }
                                onBackPressed();

                            } else {
                                Toast.makeText(this, "Amount should be greater than zero", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(this, "Please select ending date", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Amount is empty", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this, "Name should be greater than 4", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void datePicker() {
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        dayOfMonth=calendar.get(Calendar.DATE);
        datePickerDialog = new DatePickerDialog(this,this::onDateSet, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        enddate = format.format(calendar.getTime());

        Log.e("Date",""+enddate);
    }

}