package com.example.moneywallet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneywallet.models.TransactionModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class new_record extends AppCompatActivity implements View.OnClickListener {
    Button incomeBtn, expenseBtn, transferBtn, one, two, three, four, five, six, seven, eight, nine, zero;
    Button divide, multiply, add, minus, equal,clear;
    Button catt_cal;
    ImageButton saveRecord,delete,goBack;
    TextView textView;
    Date date;

    SQLiteHandler database;

    boolean isIncome, isExpense, isTransfer, isAdd, isSubtract, isDivide,isMultiply;
    long firstValue=0,secondValue=0,result=0;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

        catt_cal=findViewById(R.id.catt_cal);
        goBack = findViewById(R.id.imageButton2);
        delete = findViewById(R.id.imageButton3);
        clear = findViewById(R.id.clear_btn12);
        incomeBtn = findViewById(R.id.button11);
        expenseBtn = findViewById(R.id.button12);
        transferBtn = findViewById(R.id.button13);
        one = findViewById(R.id.one_btn);
        two = findViewById(R.id.two_btn);
        three = findViewById(R.id.three_btn);
        four = findViewById(R.id.foure_btn);
        five = findViewById(R.id.five_btn);
        six = findViewById(R.id.six_btn);
        seven = findViewById(R.id.seven_btn);
        eight = findViewById(R.id.eight_btn);
        nine = findViewById(R.id.nine_btn);
        zero = findViewById(R.id.zero_btn);
        add = findViewById(R.id.plus_btn);
        minus = findViewById(R.id.button9);
        divide = findViewById(R.id.devide_btn);
        multiply = findViewById(R.id.multipul_btn);
        equal = findViewById(R.id.equal_btn);
        saveRecord = findViewById(R.id.imageButton4);
        textView = findViewById(R.id.textView);

        textView.setShowSoftInputOnFocus(false);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        add.setOnClickListener(this);
        minus.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        equal.setOnClickListener(this);
        expenseBtn.setOnClickListener(this);
        incomeBtn.setOnClickListener(this);
        transferBtn.setOnClickListener(this);


        database = new SQLiteHandler(this);

        date = new Date();

        saveRecord.setOnClickListener(v -> {
            if(!textView.getText().toString().isEmpty()){
                if(result!=0){
                    saveTransaction((int) Long.parseLong(String.valueOf(result)));
                } else{
                    saveTransaction((int) Long.parseLong(textView.getText().toString()));
                }
            } else {
                Log.e("Input","Text field is empty");
            }

            int total = database.getSum(SQLiteHandler.KEY_AMOUNT);
            Log.e("Total",""+total);
        });

        goBack.setOnClickListener(v -> {
            onBackPressed();
        });

        clear.setOnClickListener(v -> textView.setText(""));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textView.getText().toString();
                s = s.substring(0, s.length() - 1);
                textView.setText(s);
            }
        });

        catt_cal.setOnClickListener(v -> {
            Intent intent =new Intent(new_record.this,custom_category_list.class);
            startActivity(intent);
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.one_btn:
                textView.setText(textView.getText()+"1");
                break;
            case R.id.two_btn:
                textView.setText(textView.getText()+"2");
                break;
            case R.id.three_btn:
                textView.setText(textView.getText()+"3");
                break;
            case R.id.foure_btn:
                textView.setText(textView.getText()+"4");
                break;
            case R.id.five_btn:
                textView.setText(textView.getText()+"5");
                break;
            case R.id.six_btn:
                textView.setText(textView.getText()+"6");
                break;
            case R.id.seven_btn:
                textView.setText(textView.getText()+"7");
                break;
            case R.id.eight_btn:
                textView.setText(textView.getText()+"8");
                break;
            case R.id.nine_btn:
                textView.setText(textView.getText()+"9");
                break;
            case R.id.zero_btn:
                textView.setText(textView.getText()+"0");
                break;
            case R.id.plus_btn:
                firstValue = Integer.parseInt(textView.getText().toString());

                textView.setText("");
                isAdd = true;
                isDivide = false;
                isMultiply = false;
                isSubtract = false;
                break;
            case R.id.button9:
                firstValue = Integer.parseInt(textView.getText().toString());

                textView.setText("");
                isAdd = false;
                isDivide = false;
                isMultiply = false;
                isSubtract = true;
                break;
            case R.id.multipul_btn:
                firstValue = Integer.parseInt(textView.getText().toString());

                textView.setText("");
                isAdd = false;
                isDivide = false;
                isMultiply = true;
                isSubtract = false;
                break;
            case R.id.devide_btn:
                firstValue = Integer.parseInt(textView.getText().toString());

                textView.setText("");
                isAdd = false;
                isDivide = true;
                isMultiply = false;
                isSubtract = false;
                break;
            case R.id.button12:
                isExpense = true;
                isIncome = false;
                isTransfer = false;
                break;
            case R.id.button11:
                isExpense = false;
                isIncome = true;
                isTransfer = false;
                break;
            case R.id.button13:
                isExpense = false;
                isIncome = false;
                isTransfer = true;
                break;
            case R.id.equal_btn:

                if(firstValue != 0){

                    secondValue = Integer.parseInt(textView.getText().toString());

                    if(isAdd){
                        result = firstValue+secondValue;
                        textView.setText(""+result);

                    } else if(isSubtract){
                        result = firstValue-secondValue;
                        textView.setText(""+result);

                    } else if(isDivide){
                        result = firstValue/secondValue;
                        textView.setText(""+result);

                    } else if (isMultiply){
                        result = firstValue*secondValue;
                        textView.setText(""+result);

                    } else {
                        Log.e("Default","Invalid Operator");

                    }
                } else {

                    Toast.makeText(getApplicationContext(), "Enter values", Toast.LENGTH_SHORT).show();
                }

                Log.e("First",""+firstValue);
                Log.e("Second",""+secondValue);
                break;

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void saveTransaction(int res){
        String dateInString = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
        LocalDate dateTime = LocalDate.parse(dateInString, formatter);

        if(isIncome){

            TransactionModel transaction = new TransactionModel(Constants.transactionCategory, res, 0, 0, 1, dateTime.toString());
            database.addTransaction(transaction);

            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
            firstValue=secondValue=result=0;
            textView.setText("");

            onBackPressed();

        } else if(isTransfer){

            TransactionModel transaction = new TransactionModel(Constants.transactionCategory, res, 1, 0, 0, dateTime.toString());
            database.addTransaction(transaction);

            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
            firstValue=secondValue=result=0;
            textView.setText("");

            onBackPressed();

        } else if(isExpense){

            TransactionModel transaction = new TransactionModel(Constants.transactionCategory, res, 0, 1, 0, dateTime.toString());
            database.addTransaction(transaction);

            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
            firstValue=secondValue=result=0;
            textView.setText("");

            onBackPressed();

        } else {

            firstValue=secondValue=0;
            textView.setText("");
            Toast.makeText(getApplicationContext(), "Please select type", Toast.LENGTH_SHORT).show();

        }
    }
}