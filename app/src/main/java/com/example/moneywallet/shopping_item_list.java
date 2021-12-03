package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class shopping_item_list extends AppCompatActivity {
    RecyclerView rv;
    SQLiteHandler database;
    List<ShoppingItemModel> itemList;
    ShoppingListItemAdapter itemAdapter;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_item_list);

        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("sid"));

        rv = findViewById(R.id.itemListRV);

        database = new SQLiteHandler(this);

        itemList = database.getAllItem(String.valueOf(id));

        ShoppingItemModel[] itemArray = itemList.toArray(new ShoppingItemModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ShoppingListItemAdapter(itemArray,this);

        rv.setAdapter(itemAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList = database.getAllItem(String.valueOf(id));

        ShoppingItemModel[] itemArray = itemList.toArray(new ShoppingItemModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ShoppingListItemAdapter(itemArray,this);

        rv.setAdapter(itemAdapter);
    }
}








