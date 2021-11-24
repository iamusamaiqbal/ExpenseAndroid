package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class goalPageAdapter extends FragmentPagerAdapter {
    int tabb;
    public goalPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabb=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:return new Active_goals();
            case 1:return new Paused_goals();
            case 2:return new Reached_goals();
            default:return null;
        }

    }

    @Override
    public int getCount() {
        return tabb;
    }
}