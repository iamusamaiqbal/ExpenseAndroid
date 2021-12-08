package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class debit extends AppCompatActivity {
    TabLayout tab_debit;
    ViewPager viewpager_debit;
    DebitPgeAdapter debitPageAdapter;
    FloatingActionButton new_lent_btn,new_lent_btn_2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);
        tab_debit=findViewById(R.id.tab_debit);
        viewpager_debit=findViewById(R.id.viewpage_debit);
        new_lent_btn=findViewById(R.id.new_lent_btn);
        new_lent_btn_2=findViewById(R.id.new_lent_btn_2);

        new_lent_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(debit.this,i_lent_record.class);
                intent.putExtra("type","borrow");
                startActivity(intent);
            }
        });


        new_lent_btn.setOnClickListener(v -> {
            Intent intent=new Intent(debit.this,i_lent_record.class);
            intent.putExtra("type","lent");
            startActivity(intent);
        });


        debitPageAdapter= new DebitPgeAdapter(getSupportFragmentManager(),tab_debit.getTabCount());
        viewpager_debit.setAdapter(debitPageAdapter);

        tab_debit.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager_debit.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0||tab.getPosition()==1)
                    debitPageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager_debit.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_debit));
    }

}