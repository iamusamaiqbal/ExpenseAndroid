package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneywallet.models.DebtModel;
import com.example.moneywallet.models.ShoppingListModel;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

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

    EditText name,value;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_item_list);
        floting_item_shopping_1=findViewById(R.id.floting_item_shopping_1);
        debtActiveAddtv=findViewById(R.id.debtActiveAdd);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_shopping_item_list);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.texttt);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.calculater)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);







        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("sid"));

        rv = findViewById(R.id.itemListRV);

        database = new SQLiteHandler(this);

        itemList = database.getAllItem(String.valueOf(id));
        ShoppingListModel shoppingList = database.getShopping(id);

        ShoppingItemModel[] itemArray = itemList.toArray(new ShoppingItemModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ShoppingListItemAdapter(itemArray,this);

        rv.setAdapter(itemAdapter);

        floting_item_shopping_1.setOnClickListener(v -> {
            mDialog=new Dialog(this);
            mDialog.setContentView(R.layout.add_item_shooping_card_popup);

            name = mDialog.findViewById(R.id.newItemName);
            value = mDialog.findViewById(R.id.newItemPrice);
            save = mDialog.findViewById(R.id.newItemAdd);

            save.setOnClickListener(v1 -> {
                if(name.getText().toString().length()>4){
                    int price = Integer.parseInt(value.getText().toString());
                    if(price>0){
                        boolean f = database.addItem(new ShoppingItemModel(name.getText().toString(), price,id,0));

                        if(f){

                            database.updateShopping(new ShoppingListModel(shoppingList.name, shoppingList.totalitem+1,shoppingList.amount+price),id);
                            Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();
                            onBackPressed();

                        } else {
                            Toast.makeText(this,"Something went wrong!",Toast.LENGTH_LONG).show();

                        }

                    } else {
                        Toast.makeText(this,"Price should not be Zero",Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this,"Name should be 4",Toast.LENGTH_LONG).show();
                }
            });

            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mDialog.show();

        });
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shopping_list_menu, menu);
        ((MenuBuilder) menu).setOptionalIconsVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.edit_item) {
            Intent i = new Intent(this,New_Shopping_List.class);
            i.putExtra("sid",""+id);
            startActivity(i);

        } else if (item.getItemId() == R.id.delet_item) {
            boolean f = database.deleteShopping(id);
            if(f){
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);

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








