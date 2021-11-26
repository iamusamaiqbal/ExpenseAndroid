package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.tabs.TabLayout;

public class Budget extends AppCompatActivity {
    TabLayout tab_budget;
    ViewPager viewpager_budget;

    budgetPageAdapter budgetPageAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        tab_budget=findViewById(R.id.tab_budget);
        viewpager_budget=findViewById(R.id.viewpager_budget);



        budgetPageAdapter= new budgetPageAdapter(getSupportFragmentManager(),tab_budget.getTabCount());
        viewpager_budget.setAdapter(budgetPageAdapter);

        tab_budget.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager_budget.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0||tab.getPosition()==1)
                    budgetPageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager_budget.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_budget));
    }
}