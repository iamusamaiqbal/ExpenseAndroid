package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class statPageAdapter extends FragmentPagerAdapter {
    int tabb;
    public statPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabb=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:return new Balance_statistic();
            case 1:return new outlook_statistic();
            case 2:return new cash_flow_statistic();
            case 3:return new spending_statistic();
            case 4:return new cridit_statistiic();
            case 5:return new report_statistic();
            default:return null;
        }

    }

    @Override
    public int getCount() {
        return tabb;
    }
}



