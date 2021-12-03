package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class shopping_list extends AppCompatActivity {
    FloatingActionButton new_shopping_list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        new_shopping_list_btn=findViewById(R.id.new_shopping_list_btn);

        new_shopping_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(shopping_list.this,New_Shopping_List.class);
                startActivity(intent);
            }
        });
    }
}