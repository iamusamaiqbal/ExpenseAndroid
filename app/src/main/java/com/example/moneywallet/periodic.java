package com.example.moneywallet;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class periodic extends Fragment {
    FloatingActionButton new_record_periodic;
    RecyclerView recyclerView;
    PeriodicAdapter periodicAdapter;
    List<BudgetModel> budgetList;
    SQLiteHandler database;

    public periodic() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_periodic, container, false);
        recyclerView = v.findViewById(R.id.PeriodicRV);

        database = new SQLiteHandler(getActivity());

        new_record_periodic=v.findViewById(R.id.new_record_periodic);


        new_record_periodic.setOnClickListener(v1 -> {
            Intent intent=new Intent(getActivity(),new_budget.class);
            startActivity(intent);
        });

        budgetList = database.getAllBudget();


        BudgetModel[] budgetArray = budgetList.toArray(new BudgetModel[0]);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        periodicAdapter = new PeriodicAdapter(getActivity(),budgetArray);

        recyclerView.setAdapter(periodicAdapter);



        return v;
    }
}