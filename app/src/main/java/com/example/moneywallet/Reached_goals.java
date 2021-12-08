package com.example.moneywallet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneywallet.models.GoalModel;

import java.util.ArrayList;
import java.util.List;

public class Reached_goals extends Fragment {

    ReachedAdapter reachedAdapter;
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
        View view = inflater.inflate(R.layout.fragment_reached_goals, container, false);

        rv = view.findViewById(R.id.reachedRV);
        database = new SQLiteHandler(getActivity());

        goalList = database.getAllGoal("reached");

        GoalModel[] goalArray = goalList.toArray(new GoalModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        reachedAdapter = new ReachedAdapter(getActivity(),goalArray);

        rv.setAdapter(reachedAdapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        goalList = database.getAllGoal("reached");

        GoalModel[] goalArray = goalList.toArray(new GoalModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        reachedAdapter = new ReachedAdapter(getActivity(),goalArray);

        rv.setAdapter(reachedAdapter);
    }
}