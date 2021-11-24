package com.example.moneywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class statistic extends AppCompatActivity {
    TabLayout tab_stat;
    ViewPager viewpager_stat;
    statPageAdapter statPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        tab_stat=findViewById(R.id.tab_stat);
        viewpager_stat=findViewById(R.id.viewpage_stat);




        statPageAdapter= new statPageAdapter(getSupportFragmentManager(),tab_stat.getTabCount());
        viewpager_stat.setAdapter(statPageAdapter);

        tab_stat.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager_stat.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0||tab.getPosition()==1)
                    statPageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager_stat.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_stat));
    }
}