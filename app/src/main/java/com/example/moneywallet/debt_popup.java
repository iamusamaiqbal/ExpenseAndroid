package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class debt_popup extends AppCompatActivity {
    TextView popup_debt_text_2,popup_debt_text_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_popup);
        popup_debt_text_2=findViewById(R.id.popup_debt_text_2);
        popup_debt_text_3=findViewById(R.id.popup_debt_text_3);



        popup_debt_text_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ERRRR","ffffffffff");
                Intent intent=new Intent(debt_popup.this,Create_debt_record.class);
                startActivity(intent);
            }
        });

        popup_debt_text_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(debt_popup.this,Create_debt_record.class);
                startActivity(intent);
            }
        });
    }
}