package com.example.moneywallet;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneywallet.models.GoalModel;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

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

//        new Thread(() -> {
//            while (true) {
//                //do stuff....
//                Random r = new Random();
//                if (r.nextInt(100) == 42) {
//                    break;
//                }
//            }
//
//            getActivity().runOnUiThread(() -> Log.e("Mian "," Thread is working"));
//        }).start();

        floatingActionButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(),New_Goal.class);
            i.putExtra("gid",""+0);
            startActivity(i);
        });

        goalList = database.getAllGoal("active");

//        GoalModel[] goalArray = goalList.toArray(new GoalModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        goalAdapter = new GoalAdapter(getActivity(),goalList);

        rv.setAdapter(goalAdapter);

//        rv.setListener(new SwipeLeftRightCallback.Listener() {
//            @Override
//            public void onSwipedLeft(int position) {
//
//                int id = goalList.get(position).id;
//
//                boolean f = database.deleteTransaction(id);
//
//                Log.e("eee","jjjjjjjjjjj"+position);
//                goalAdapter.remove(position);
//                goalAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onSwipedRight(int position) {
//            }
//        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        goalList = database.getAllGoal("active");

//        GoalModel[] goalArray = goalList.toArray(new GoalModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        goalAdapter = new GoalAdapter(getActivity(),goalList);

        rv.setAdapter(goalAdapter);
    }
}