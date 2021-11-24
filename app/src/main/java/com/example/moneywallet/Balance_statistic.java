package com.example.moneywallet;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class Balance_statistic extends Fragment {

    SQLiteHandler database;

    List<TransactionModel> transactionList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_balance_statistic, container, false);

        //LineChart

        LineChart lineChart = v.findViewById(R.id.BalanceLineChart);
        database = new SQLiteHandler(getActivity());
        transactionList = database.getAllTransaction();

        ArrayList<Entry> yExpense = new ArrayList<>();
        ArrayList<Entry> yIncome = new ArrayList<>();

        yExpense.add(new Entry(0f,0));
        yIncome.add(new Entry(0f,0));


        int p =0;

        for(int i=0; i<transactionList.size();i++){

            int t = p - transactionList.get(i).amount;

            yExpense.add(new Entry(transactionList.get(i).id*100f,t*1));

            p=transactionList.get(i).amount;
        }

//        transactionList.forEach(amount -> {
//
//            if(amount.isExpense == 1){
//                yExpense.add(new Entry(amount.id*100f,amount.amount*-1));
//            } else {
//                yExpense.add(new Entry(amount.id*100f,amount.amount*1));
//            }
//        });


        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        LineDataSet lineDataSet1 = new LineDataSet(yExpense, "Expense");
        lineDataSet1.setValueTextSize(10);
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet1.setColor(Color.BLUE);
        lineDataSet1.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet1.setDrawCircles(false);
//        lineDataSet1.setCubicIntensity(0.15f);
//        lineDataSet1.setCircleColor(Color.BLUE);
        lineDataSet1.setLineWidth(1);
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet1.setDrawFilled(true);
        lineDataSet1.setFillColor(Color.BLUE);



        LineDataSet lineDataSet2 = new LineDataSet(yIncome, "Income");
        lineDataSet2.setValueTextSize(10);
        lineDataSet2.setDrawCircleHole(true);
        lineDataSet2.setColor(Color.BLUE);
        lineDataSet2.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setCubicIntensity(0.15f);
        lineDataSet2.setCircleColor(Color.BLUE);
        lineDataSet2.setLineWidth(1);
        lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet2.setDrawFilled(true);
        lineDataSet2.setFillColor(Color.BLUE);

        final ArrayList<String> labels = new ArrayList<String>();
        labels.add("12AM");
        labels.add("06AM");
        labels.add("12PM");
        labels.add("06PM");
        labels.add("12AM");


        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet1);

        LineData data12 = new LineData(dataSets);

        lineChart.setData(data12);





        return v;
    }
}