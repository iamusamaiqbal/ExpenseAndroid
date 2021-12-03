package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class debt_add_record_list extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_add_record_list);

    }



    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.debt_menu,menu);
        ((MenuBuilder) menu).setOptionalIconsVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.setting_c) {


            Toast.makeText(this, "clicked setting", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.ratting_c) {
            Toast.makeText(this, "ratting click", Toast.LENGTH_SHORT).show();


        } else if (item.getItemId() == R.id.share_c) {
            Toast.makeText(this, "click share", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);

    }

    }