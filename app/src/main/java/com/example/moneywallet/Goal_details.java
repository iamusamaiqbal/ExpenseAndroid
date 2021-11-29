package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Goal_details extends AppCompatActivity {
    TextView goalname,goaldate;
    ImageView save;
    Button addbtn,status;
    EditText amount;

    int id;
    SQLiteHandler database;
    GoalModel goalModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_details);

        goalname =findViewById(R.id.goalDetailNameTV);
        goaldate = findViewById(R.id.goalDetailDateTV);
        save = findViewById(R.id.goalDetailSaveIV);
        addbtn = findViewById(R.id.goalDetailAddBtn);
        status = findViewById(R.id.goalDetailStatus);
        amount = findViewById(R.id.goalDetailAddAmountET);

        database = new SQLiteHandler(this);

        Intent i = getIntent();
        id = Integer.parseInt( i.getStringExtra("gid"));

        goalModel = database.getGoal(id);

        if(goalModel != null){

            goaldate.setText(goalModel.date);
            goalname.setText(goalModel.name);

        }

        addbtn.setOnClickListener(v -> {
            if(!amount.getText().toString().isEmpty()){
                int total = Integer.parseInt(amount.getText().toString()) + goalModel.alreadySaved;

                database.updateGoal(new GoalModel(goalModel.name,goalModel.note,goalModel.date,goalModel.amount,total,goalModel.status),id);

            } else {
                Toast.makeText(this, "Amount is empty", Toast.LENGTH_SHORT).show();
            }
        });


    }
}