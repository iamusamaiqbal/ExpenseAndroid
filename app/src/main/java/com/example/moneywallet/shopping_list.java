package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class shopping_list extends AppCompatActivity {
    RecyclerView rv;
    ShoppingListAdapter shoppingListAdapter;
    SQLiteHandler database;
    List <ShoppingListModel> shoppingList ;
    FloatingActionButton new_shopping_list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        new_shopping_list_btn=findViewById(R.id.new_shopping_list_btn);

        rv = findViewById(R.id.shoppingListRV);
        database = new SQLiteHandler(this);

        shoppingList = database.getAllShoppingList();

        ShoppingListModel[] shoppingListArray = shoppingList.toArray(new ShoppingListModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(this));

        shoppingListAdapter = new ShoppingListAdapter(shoppingListArray,this);

        rv.setAdapter(shoppingListAdapter);


        new_shopping_list_btn.setOnClickListener(v -> {
            Intent intent=new Intent(shopping_list.this,New_Shopping_List.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        shoppingList = database.getAllShoppingList();

        ShoppingListModel[] shoppingListArray = shoppingList.toArray(new ShoppingListModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(this));

        shoppingListAdapter = new ShoppingListAdapter(shoppingListArray,this);

        rv.setAdapter(shoppingListAdapter);
    }
}