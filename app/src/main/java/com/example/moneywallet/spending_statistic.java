package com.example.moneywallet;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;


public class spending_statistic extends Fragment {

    SQLiteHandler database;

    List<PieEntry> entries = new ArrayList<>();
    List<String> categories;
    List<TransactionModel> transactionList ;
    ArrayList<Integer> colors;
    MyListAdapter adapter;
    ListView listView;

    int total = 0,sum=0;

    final int[] MY_COLORS = {
            Color.rgb(192, 0, 0),
            Color.rgb(255, 0, 0),
            Color.rgb(255, 192, 0),
            Color.rgb(127, 127, 127),
            Color.rgb(146, 208, 80),
            Color.rgb(0, 176, 80),
            Color.rgb(79, 129, 189)};



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spending_statistic, container, false);



        PieChart chart = view.findViewById(R.id.SpendingPieChart);
        listView = view.findViewById(R.id.SpendingListView);
        database = new SQLiteHandler(getActivity());
        transactionList = new ArrayList<>();
        colors = new ArrayList<>();
        categories = new ArrayList<>();


        transactionList = database.getAllTransaction();

        TransactionModel[] transactionArray = transactionList.toArray(new TransactionModel[0]);

        adapter = new MyListAdapter(getActivity(), transactionArray);
        listView.setAdapter(adapter);
        sum = database.getSum(SQLiteHandler.KEY_AMOUNT);

        transactionList.forEach(transactionModel -> {


            if (!categories.contains(transactionModel.cat)) {
                total = database.getSumByCat(SQLiteHandler.KEY_AMOUNT,transactionModel.cat);
                entries.add(new PieEntry(total*1f,""+transactionModel.cat));
                categories.add(transactionModel.cat);
            }
        });

        for (int c : MY_COLORS) colors.add(c);

        PieDataSet set = new PieDataSet(entries, " Total : "+sum);
        PieData data = new PieData(set);
        set.setColors(colors);
        chart.animateXY(5000, 5000);
        chart.setDrawHoleEnabled(false);
        chart.setDrawHoleEnabled(true);
        chart.setData(data);


        return view;
    }
}