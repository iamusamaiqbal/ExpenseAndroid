package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Goals extends AppCompatActivity {
    TabLayout tab_goal;
    ViewPager viewpager_goal;
    goalPageAdapter goalPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        tab_goal=findViewById(R.id.tab_goals);
        viewpager_goal=findViewById(R.id.viewpager_goals);

        goalPageAdapter= new goalPageAdapter(getSupportFragmentManager(),tab_goal.getTabCount());
        viewpager_goal.setAdapter(goalPageAdapter);

        tab_goal.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager_goal.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0||tab.getPosition()==1)
                    goalPageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager_goal.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_goal));

    }
}