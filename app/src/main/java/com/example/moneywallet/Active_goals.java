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

import java.util.ArrayList;
import java.util.List;

public class Active_goals extends Fragment {

    GoalAdapter goalAdapter;
    FloatingActionButton floatingActionButton;
    RecyclerView rv;
    SQLiteHandler database;
    List<GoalModel> goalList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_goals, container, false);
        floatingActionButton = view.findViewById(R.id.new_goal);
        rv = view.findViewById(R.id.ActiveRV);
        database = new SQLiteHandler(getActivity());

        floatingActionButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(),New_Goal.class);
            startActivity(i);
        });

        goalList = database.getAllGoal();

        GoalModel[] goalArray = goalList.toArray(new GoalModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        goalAdapter = new GoalAdapter(getActivity(),goalArray);

        rv.setAdapter(goalAdapter);

        return view;
    }
}