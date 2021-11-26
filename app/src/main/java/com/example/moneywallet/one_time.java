package com.example.moneywallet;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class one_time extends Fragment {
    FloatingActionButton new_one_time_floating_btn;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_one_time, container, false);
        new_one_time_floating_btn=v.findViewById(R.id.new_one_time_floating_btn);

        new_one_time_floating_btn.setOnClickListener(v1 -> {
            Intent intent=new Intent(getActivity(),new_budget.class);
            startActivity(intent);
        });
        // Inflate the layout for this fragment
        return v;
    }
}