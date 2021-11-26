package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    TextView user_profile_setting,user_profile_setting_1,categories_setting,categories_setting_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        user_profile_setting=findViewById(R.id.user_profile_setting);
        user_profile_setting_1=findViewById(R.id.user_profile_setting_1);
        categories_setting=findViewById(R.id.categories_setting);
        categories_setting_1=findViewById(R.id.categories_setting_1);


        user_profile_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Setting.this,user_profile.class);
                startActivity(intent);
            }
        });
        user_profile_setting_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Setting.this,user_profile.class);
                startActivity(intent);
            }
        });

        categories_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Setting.this,catogries.class);
                startActivity(intent);
            }
        });
        categories_setting_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Setting.this,catogries.class);
            }
        });
    }
}