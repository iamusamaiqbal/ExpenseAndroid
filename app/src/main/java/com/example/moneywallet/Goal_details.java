package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneywallet.models.GoalModel;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class Goal_details extends AppCompatActivity {
    TextView goalname, goaldate, completed;
    ImageView edit,back;
    Button addbtn, status;
    EditText amount;

    CircularProgressBar circularProgressBar;
    int id;
    SQLiteHandler database;
    GoalModel goalModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_details);

        goalname = findViewById(R.id.goalDetailNameTV);
        goaldate = findViewById(R.id.goalDetailDateTV);
        edit = findViewById(R.id.goalDetailEditIV);
        addbtn = findViewById(R.id.goalDetailAddBtn);
        status = findViewById(R.id.goalDetailStatus);
        amount = findViewById(R.id.goalDetailAddAmountET);
        completed = findViewById(R.id.goalDetailCompleted);
        circularProgressBar = findViewById(R.id.circularProgressBar);
        back = findViewById(R.id.goalDetailBackBtn);

        database = new SQLiteHandler(this);

        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("gid"));

        goalModel = database.getGoal(id);

        if (goalModel != null) {

            goaldate.setText(goalModel.date);
            goalname.setText(goalModel.name);

            float total = (goalModel.alreadySaved * 100) / goalModel.amount;
            circularProgressBar.setProgressWithAnimation(total * 1f, Long.parseLong(String.valueOf(3000)));
            completed.setText("" + total + " %");

        }

        back.setOnClickListener(v -> {
            onBackPressed();
        });

        edit.setOnClickListener(v -> {
            Intent intent = new Intent(this,New_Goal.class);
            intent.putExtra("gid",""+id);
            startActivity(intent);
        });

        addbtn.setOnClickListener(v -> {
            if (!amount.getText().toString().isEmpty()) {
                int total = Integer.parseInt(amount.getText().toString()) + goalModel.alreadySaved;

                boolean f = database.updateGoal(new GoalModel(goalModel.name, goalModel.note, goalModel.date, goalModel.amount, total, goalModel.status), id);

                if(f){
                    circularProgressBar.setProgressWithAnimation(total * 1f, Long.parseLong(String.valueOf(3000)));
                    completed.setText("" + total + " %");
                }

            } else {
                Toast.makeText(this, "Amount is empty", Toast.LENGTH_SHORT).show();
            }
        });

        status.setOnClickListener(v -> {
            boolean f = database.updateGoal(new GoalModel(goalModel.name, goalModel.note, goalModel.date, goalModel.amount, goalModel.alreadySaved, "reached"), id);

            if (f){
                addbtn.setVisibility(View.INVISIBLE);
                status.setVisibility(View.INVISIBLE);
                amount.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.INVISIBLE);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        goalModel = database.getGoal(id);

        if (goalModel != null) {

            goaldate.setText(goalModel.date);
            goalname.setText(goalModel.name);

            float total = (goalModel.alreadySaved * 100) / goalModel.amount;
            circularProgressBar.setProgressWithAnimation(total * 1f, Long.parseLong(String.valueOf(3000)));
            completed.setText("" + total + " %");

        }
    }
}