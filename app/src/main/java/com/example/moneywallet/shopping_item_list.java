package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class shopping_item_list extends AppCompatActivity {
    RecyclerView rv;
    SQLiteHandler database;
    List<ShoppingItemModel> itemList;
    ShoppingListItemAdapter itemAdapter;
    int id;
    FloatingActionButton floting_item_shopping_1;
    Dialog mDialog;
    Context context;
    TextView debtActiveAddtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_item_list);
        floting_item_shopping_1=findViewById(R.id.floting_item_shopping_1);
        debtActiveAddtv=findViewById(R.id.debtActiveAdd);

        floting_item_shopping_1.setOnClickListener(v -> {
            mDialog=new Dialog(this);
                mDialog.setContentView(R.layout.add_item_shooping_card_popup);


                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

        });

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








