package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

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

    SQLiteHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shopping_list);

        back = findViewById(R.id.newShoppingListBack);
        name = findViewById(R.id.newShoppingListName);
        save = findViewById(R.id.newShoppingListSave);
        database = new SQLiteHandler(this);

        back.setOnClickListener(v -> {
            onBackPressed();
        });

        save.setOnClickListener(v -> {
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
        });
    }
}