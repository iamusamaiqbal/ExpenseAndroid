package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moneywallet.models.ShoppingListModel;

public class New_Shopping_List extends AppCompatActivity {

    ImageView back;
    EditText name;
    Button save;
    String id;
    SQLiteHandler database;
    ShoppingListModel shoppingModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shopping_list);

        back = findViewById(R.id.newShoppingListBack);
        name = findViewById(R.id.newShoppingListName);
        save = findViewById(R.id.newShoppingListSave);
        database = new SQLiteHandler(this);

        Intent i = getIntent();
        id = i.getStringExtra("sid");

        back.setOnClickListener(v -> {
            onBackPressed();
        });

        if(id!=null){
            shoppingModel = database.getShopping(Integer.parseInt(id));
            name.setText(shoppingModel.name);
        }

        save.setOnClickListener(v -> {
            if(id!=null){
                if(name.getText().toString().length()>4){
                    boolean f = database.updateShopping(new ShoppingListModel(shoppingModel.name,shoppingModel.totalitem,shoppingModel.amount),Integer.parseInt(id));

                    if(f){
                        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(this, "Something went wrong! ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Name should be 4", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(name.getText().toString().length()>4){
                    boolean f = database.addShopping(new ShoppingListModel(name.getText().toString(),0,0));

                    if(f){
                        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(this, "Something went wrong! ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Name should be 4", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}