package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class add_item_shopping_list extends AppCompatActivity {
    FloatingActionsMenu shopping_add_item_floating_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_shopping_list);
        shopping_add_item_floating_btn=findViewById(R.id.shopping_add_item_floating_btn);

    }
}