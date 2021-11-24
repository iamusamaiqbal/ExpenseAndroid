package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class budgetPageAdapter extends FragmentPagerAdapter {
    int tabb;
    public budgetPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabb=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:return new periodic();
            case 1:return new one_time();
            default:return null;
        }

    }

    @Override
    public int getCount() {
        return tabb;
    }
}
