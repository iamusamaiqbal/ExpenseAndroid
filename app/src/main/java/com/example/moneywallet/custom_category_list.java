package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class custom_category_list extends AppCompatActivity {
    ListView custom_list_category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_category_list);
        custom_list_category=findViewById(R.id.custom_list_category);


        ArrayList<person> arrayList=new ArrayList<>();
        arrayList.add(new person(R.drawable.ic_dinner,"Food & Drinks"));
        arrayList.add(new person(R.drawable.ic_shoppinggg,"Shopping"));
        arrayList.add(new person(R.drawable.ic_house,"Housing"));
        arrayList.add(new person(R.drawable.ic_bus,"Transportation"));
        arrayList.add(new person(R.drawable.ic_carrrr,"Vehicle"));
        arrayList.add(new person(R.drawable.ic_entertainment,"Life & Entertainment"));
        arrayList.add(new person(R.drawable.ic_laptop_svgrepo_com,"Communication,PC"));

        arrayList.add(new person(R.drawable.ic_investment_svgrepo_com,"Financial Expenses"));
        arrayList.add(new person(R.drawable.ic_investment,"Investments"));
        arrayList.add(new person(R.drawable.ic_incom,"Income"));
        arrayList.add(new person(R.drawable.ic_other,"Other"));



        personAdapter personAdapter=new personAdapter(this,R.layout.custom_list_row,arrayList);
        custom_list_category.setAdapter(personAdapter);

    }
}