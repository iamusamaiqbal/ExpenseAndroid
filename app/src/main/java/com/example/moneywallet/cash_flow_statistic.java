package com.example.moneywallet;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class cash_flow_statistic extends Fragment {

    SQLiteHandler database;
    List<TransactionModel> transactionList ;
    LineChart lineChart;
    BarChart barChart;
    Button cash,expense,income;


    public cash_flow_statistic() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cash_flow_statistic, container, false);

        cash = view.findViewById(R.id.CashFlowBtn);
        income = view.findViewById(R.id.CashFlowIncomeBtn);
        expense = view.findViewById(R.id.CashFlowExpenseBtn);

        database = new SQLiteHandler(getActivity());
        transactionList = new ArrayList<>();
        barChart = view.findViewById(R.id.CashFlowBarChart);
        lineChart = view.findViewById(R.id.CashFlowPeriodLineChart);
        ArrayList NoOfEmp = new ArrayList();

        transactionList = database.getAllTransaction();

//        transactionList.forEach(transactionModel -> {
//
//            LocalDate dateTime = LocalDate.parse(transactionModel.date);
//
//            if ((dateTime.isEqual(LocalDate.now()) || dateTime.isAfter(LocalDate.parse(budget.start))) && (dateTime.isEqual(LocalDate.parse(budget.end)) || dateTime.isBefore(LocalDate.parse(budget.end)))) {
//                if(!dates.contains(dateTime.toString())){
//                    total=database.getSumByDate(SQLiteHandler.KEY_AMOUNT,dateTime.toString());
//                    transactions.add(new BarEntry( transactionModel.id*400f, total*1));
//                    dates.add(dateTime.toString());
//                }
//            }
//        });

        NoOfEmp.add(new BarEntry(300f, 1));
        NoOfEmp.add(new BarEntry(600f, 2));
        NoOfEmp.add(new BarEntry(900f, 3));
        NoOfEmp.add(new BarEntry(1200f, 4));
        NoOfEmp.add(new BarEntry(1500f, 5));
        NoOfEmp.add(new BarEntry(1800f, 6));
        NoOfEmp.add(new BarEntry(2100f, 7));
//        NoOfEmp.add(new BarEntry(2400f, 7));
//        NoOfEmp.add(new BarEntry(2700f, 8));
//        NoOfEmp.add(new BarEntry(3000f, 9));

        cashFlowBars(NoOfEmp);

        //Line Chart
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        ArrayList<Entry> yCash = new ArrayList<>();
        ArrayList<Entry> yIncome = new ArrayList<>();

        cashFlowLine(yCash,dataSets);


        cash.setOnClickListener(v -> {

            yCash.clear();
            lineChart.clear();
            dataSets.clear();

            yCash.add(new Entry(0f,0));
            int p =0;

            for(int i=0; i<transactionList.size();i++){

                int t = p - transactionList.get(i).amount;

                yCash.add(new Entry(transactionList.get(i).id*100f,t*1f));

                p=transactionList.get(i).amount;
            }


            LineDataSet lineDataSet1 = new LineDataSet(yCash, "Expense");
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

            dataSets.add(lineDataSet1);

            LineData data12 = new LineData(dataSets);

            final ArrayList<String> xAxisLabel = new ArrayList<>();
            xAxisLabel.add("Mon");
            xAxisLabel.add("Tue");
            xAxisLabel.add("Wed");
            xAxisLabel.add("Thu");
            xAxisLabel.add("Fri");
            xAxisLabel.add("Sat");
            xAxisLabel.add("Sun");


            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setDrawGridLines(false);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    String label = "";
                    if(value == 100)
                        label = xAxisLabel.get(0);
                    else if(value == 200)
                        label = xAxisLabel.get(1);
                    else if(value == 300)
                        label = xAxisLabel.get(2);
                    else if(value == 400)
                        label = xAxisLabel.get(3);
                    else if(value == 500)
                        label = xAxisLabel.get(4);
                    else if(value == 600)
                        label = xAxisLabel.get(5);
                    else if(value == 700)
                        label = xAxisLabel.get(6);
                    else if(value == 800)
                        label = xAxisLabel.get(7);
                    return label;
                }
            });

            lineChart.setData(data12);

        });

        expense.setOnClickListener(v -> {

            yCash.clear();
            dataSets.clear();
            lineChart.clear();

            yCash.add(new Entry(0f,0));


            transactionList.forEach(transactionModel -> {
                if(transactionModel.isExpense == 1){
                    yCash.add(new Entry(transactionModel.id*100f,transactionModel.amount*1f));
                }
            });

            LineDataSet lineDataSet1 = new LineDataSet(yCash, "Expense");
            lineDataSet1.setValueTextSize(10);
            lineDataSet1.setDrawCircleHole(true);
            lineDataSet1.setColor(Color.RED);
            lineDataSet1.setMode(LineDataSet.Mode.LINEAR);
            lineDataSet1.setDrawCircles(true);
            lineDataSet1.setCubicIntensity(0.15f);
            lineDataSet1.setCircleColor(Color.RED);
            lineDataSet1.setLineWidth(1);
            lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            lineDataSet1.setDrawFilled(true);
            lineDataSet1.setFillColor(Color.RED);

            dataSets.add(lineDataSet1);
            LineData data12 = new LineData(dataSets);

            final ArrayList<String> xAxisLabel = new ArrayList<>();
            xAxisLabel.add("Mon");
            xAxisLabel.add("Tue");
            xAxisLabel.add("Wed");
            xAxisLabel.add("Thu");
            xAxisLabel.add("Fri");
            xAxisLabel.add("Sat");
            xAxisLabel.add("Sun");


            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setDrawGridLines(false);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    String label = "";
                    if(value == 100)
                        label = xAxisLabel.get(0);
                    else if(value == 200)
                        label = xAxisLabel.get(1);
                    else if(value == 300)
                        label = xAxisLabel.get(2);
                    else if(value == 400)
                        label = xAxisLabel.get(3);
                    else if(value == 500)
                        label = xAxisLabel.get(4);
                    else if(value == 600)
                        label = xAxisLabel.get(5);
                    else if(value == 700)
                        label = xAxisLabel.get(6);
                    else if(value == 800)
                        label = xAxisLabel.get(7);
                    return label;
                }
            });


            lineChart.setData(data12);

        });

        income.setOnClickListener(v -> {

            yCash.clear();
            dataSets.clear();
            lineChart.clear();

            yCash.add(new Entry(0f,0));
            transactionList.forEach(transactionModel -> {
                if(transactionModel.isIncome == 1){
                    yCash.add(new Entry(transactionModel.id*100f,transactionModel.amount*1f));
                }
            });

            LineDataSet lineDataSet2 = new LineDataSet(yCash, "Income");
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

            dataSets.add(lineDataSet2);
            LineData data12 = new LineData(dataSets);

            final ArrayList<String> xAxisLabel = new ArrayList<>();
            xAxisLabel.add("Mon");
            xAxisLabel.add("Tue");
            xAxisLabel.add("Wed");
            xAxisLabel.add("Thu");
            xAxisLabel.add("Fri");
            xAxisLabel.add("Sat");
            xAxisLabel.add("Sun");


            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setDrawGridLines(false);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    String label = "";
                    if(value == 100)
                        label = xAxisLabel.get(0);
                    else if(value == 200)
                        label = xAxisLabel.get(1);
                    else if(value == 300)
                        label = xAxisLabel.get(2);
                    else if(value == 400)
                        label = xAxisLabel.get(3);
                    else if(value == 500)
                        label = xAxisLabel.get(4);
                    else if(value == 600)
                        label = xAxisLabel.get(5);
                    else if(value == 700)
                        label = xAxisLabel.get(6);
                    else if(value == 800)
                        label = xAxisLabel.get(7);
                    return label;
                }
            });


            lineChart.setData(data12);
        });


        return view;
    }

    public void cashFlowBars(ArrayList NoOfEmp){


        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");
        xAxisLabel.add("Sun");



        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Expenses");
        barChart.animateY(5000);
        BarData data2 = new BarData(bardataset);
        data2.setBarWidth(100f);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data2);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String label = "";
                if(value == 300)
                    label = xAxisLabel.get(0);
                else if(value == 600)
                    label = xAxisLabel.get(1);
                else if(value == 900)
                    label = xAxisLabel.get(2);
                else if(value == 1200)
                    label = xAxisLabel.get(3);
                else if(value == 1500)
                    label = xAxisLabel.get(4);
                else if(value == 1800)
                    label = xAxisLabel.get(5);
                else if(value == 2100)
                    label = xAxisLabel.get(6);
                return label;
            }
        });
    }
    public void cashFlowLine(ArrayList<Entry> yCash,ArrayList<ILineDataSet> dataSets){
        yCash.add(new Entry(0f,0));
        int p =0;

        for(int i=0; i<transactionList.size();i++){

            int t = p - transactionList.get(i).amount;

            yCash.add(new Entry(transactionList.get(i).id*100f,t*1f));

            p=transactionList.get(i).amount;
        }


        LineDataSet lineDataSet1 = new LineDataSet(yCash, "Expense");
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

        dataSets.add(lineDataSet1);

        LineData data12 = new LineData(dataSets);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");
        xAxisLabel.add("Sun");


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String label = "";
                if(value == 100)
                    label = xAxisLabel.get(0);
                else if(value == 200)
                    label = xAxisLabel.get(1);
                else if(value == 300)
                    label = xAxisLabel.get(2);
                else if(value == 400)
                    label = xAxisLabel.get(3);
                else if(value == 500)
                    label = xAxisLabel.get(4);
                else if(value == 600)
                    label = xAxisLabel.get(5);
                else if(value == 700)
                    label = xAxisLabel.get(6);
                else if(value == 800)
                    label = xAxisLabel.get(7);
                return label;
            }
        });

        lineChart.setData(data12);
    }
}