package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


class DebitPgeAdapter extends FragmentPagerAdapter {
    int tabb;
    public DebitPgeAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabb=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:return new Active();
            case 1:return new close();
            default:return null;
        }

    }

    @Override
    public int getCount() {
        return tabb;
    }
}
