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
import android.widget.ProgressBar;

import com.example.moneywallet.models.TransactionModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class cash_flow_statistic extends Fragment {

    SQLiteHandler database;
    List<TransactionModel> transactionList;
    LineChart lineChart;
    BarChart barChart;
    Button cash, expense, income;
    ProgressBar incomeProgress,expenseProgress;
    Date date;
    LocalDate dateTime;
    String StringDate;

    int totalIncome,totalExpense;


    public cash_flow_statistic() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cash_flow_statistic, container, false);

        cash = view.findViewById(R.id.CashFlowBtn);
        income = view.findViewById(R.id.CashFlowIncomeBtn);
        expense = view.findViewById(R.id.CashFlowExpenseBtn);
        incomeProgress=view.findViewById(R.id.CashFlowIncomeBar);
        expenseProgress=view.findViewById(R.id.CashFlowExpenseBar);

        database = new SQLiteHandler(getActivity());
        transactionList = new ArrayList<>();
        barChart = view.findViewById(R.id.CashFlowBarChart);
        lineChart = view.findViewById(R.id.CashFlowPeriodLineChart);
        date = new Date();
        ArrayList months = new ArrayList();


        String dateInString = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
        dateTime = LocalDate.parse(dateInString, formatter);

        transactionList = database.getAllTransaction();

        /*========================================= total ===========================================*/

        transactionList.forEach(transactionModel -> {
            if(transactionModel.isExpense ==1){
                totalExpense+=transactionModel.amount;
            } else if (transactionModel.isIncome==1){
                totalIncome+=transactionModel.amount;
            } else {
                Log.e("ERROR","Transfer");
            }
        });

        incomeProgress.setMax(1000);
        expenseProgress.setMax(1000);
        incomeProgress.setProgress(totalIncome);
        expenseProgress.setProgress(totalExpense);


        /*========================================= Bar Chart ========================================*/

        int mi = 0;
        int me = 0;

        int mi1 = 0;
        int me1 = 0;

        int mi2 = 0;
        int me2 = 0;

        for (int i = 0; i < transactionList.size(); i++) {

            LocalDate day = LocalDate.parse(transactionList.get(i).date);
            Month mth = day.getMonth();

            if (mth.equals(dateTime.getMonth())) {
                if (transactionList.get(i).isExpense == 1) {
                    me += transactionList.get(i).amount;
                } else {
                    mi += transactionList.get(i).amount;
                }
            } else if (mth.minus(1).equals(dateTime.getMonth())) {
                if (transactionList.get(i).isExpense == 1) {
                    me1 += transactionList.get(i).amount;
                } else {
                    mi1 += transactionList.get(i).amount;
                }
            } else if (mth.minus(2).equals(dateTime.getMonth())) {
                if (transactionList.get(i).isExpense == 1) {
                    me2 += transactionList.get(i).amount;
                } else {
                    mi2 += transactionList.get(i).amount;
                }
            } else {
                Log.i("Old","Transaction is older");
            }

        }

        months.add(new BarEntry(200f, new float[]{(float) 7, (float) 88}));
        months.add(new BarEntry(400f, new float[]{(float) 45, (float) 78}));
        months.add(new BarEntry(600f, new float[]{(float) mi2, (float) me2}));
        months.add(new BarEntry(800f, new float[]{(float) mi1, (float) me1}));
        months.add(new BarEntry(1000f, new float[]{(float) mi, (float) me}));


        XAxis xAxisb = barChart.getXAxis();
        xAxisb.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxisb.setDrawGridLines(false);
        xAxisb.setValueFormatter((value, axis) -> {
            String label = "";
            if (value == 200) {
                LocalDate day = dateTime.minusMonths(4);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 400) {
                LocalDate day = dateTime.minusMonths(3);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 600) {
                LocalDate day = dateTime.minusMonths(2);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 800) {
                LocalDate day = dateTime.minusMonths(1);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 1000) {
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
                StringDate = dateTime.format(fLocalDate);
                label = StringDate;
            }
//            } else if (value == 1200) {
//                LocalDate day = dateTime.minusMonths(6);
//                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
//                StringDate = day.format(fLocalDate);
//                label = StringDate;
//            } else if (value == 1400) {
//                LocalDate day = dateTime.minusMonths(5);
//                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
//                StringDate = day.format(fLocalDate);
//                label = StringDate;
//
//            } else if (value == 1600) {
//                LocalDate day = dateTime.minusMonths(4);
//                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
//                StringDate = day.format(fLocalDate);
//                label = StringDate;
//
//            } else if (value == 1800) {
//                LocalDate day = dateTime.minusMonths(3);
//                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
//                StringDate = day.format(fLocalDate);
//                label = StringDate;
//
//            } else if (value == 2000) {
//                LocalDate day = dateTime.minusMonths(2);
//                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
//                StringDate = day.format(fLocalDate);
//                label = StringDate;
//
//            } else if (value == 2200) {
//                LocalDate day = dateTime.minusMonths(1);
//                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
//                StringDate = day.format(fLocalDate);
//                label = StringDate;
//
//            } else if (value == 2400) {
//                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("MMM");
//                StringDate = dateTime.format(fLocalDate);
//                label = StringDate;
//
//            }
            return label;
        });


        BarDataSet bardataset = new BarDataSet(months,"Cash Flow");
        bardataset.setStackLabels(new String[]{"Income","Expenses"});
        bardataset.setColors(Color.BLUE,Color.RED);

        barChart.animateY(3000);
        BarData data2 = new BarData(bardataset);
        data2.setBarWidth(100f);
        barChart.setData(data2);


        /*      ======================================== Line Chart =====================================*/

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        ArrayList<Entry> yCash = new ArrayList<>();
        ArrayList<Entry> yIncome = new ArrayList<>();
        ArrayList<Entry> yExpense = new ArrayList<>();

        cashFlowLine(yCash, dataSets);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter((value, axis) -> {
            String label = "";
            if (value == 0) {
                LocalDate day = dateTime.minusDays(6);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 100) {
                LocalDate day = dateTime.minusDays(5);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 200) {
                LocalDate day = dateTime.minusDays(4);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 300) {
                LocalDate day = dateTime.minusDays(3);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 400) {
                LocalDate day = dateTime.minusDays(2);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 500) {
                LocalDate day = dateTime.minusDays(1);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                StringDate = day.format(fLocalDate);
                label = StringDate;
            } else if (value == 600) {
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                StringDate = dateTime.format(fLocalDate);
                label = StringDate;

            }
            return label;
        });

        cash.setOnClickListener(v -> {
            cashFlowLine(yCash, dataSets);

        });

        expense.setOnClickListener(v -> {

            yCash.clear();
            dataSets.clear();
            lineChart.clear();

            yCash.add(new Entry(0f, 0));


            int je = 0;
            for (int i = 6; i > -1; i--) {

                LocalDate day = dateTime.minusDays(i);
                int totalExp = database.getSumByDateExpense(SQLiteHandler.KEY_AMOUNT, day.toString());

                if (totalExp != 0) {

                    yExpense.add(new Entry(je * 100f, totalExp * 1));

                }

                Log.e("ERRRRRRRRRRRRRRr", "" + totalExp);
                je += 1;
            }

            LineDataSet lineDataSet1 = new LineDataSet(yExpense, "Expense");
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

            lineChart.setData(data12);

        });

        income.setOnClickListener(v -> {

            yCash.clear();
            dataSets.clear();
            lineChart.clear();

            int ji = 0;
            for (int i = 6; i > -1; i--) {

                LocalDate day = dateTime.minusDays(i);

                int totalInc = database.getSumByDateIncome(SQLiteHandler.KEY_AMOUNT, day.toString());

                if (totalInc != 0) {
                    yIncome.add(new Entry(ji * 100f, totalInc * 1));
                }

                Log.e("ERRRRRRRRRRRRRRr", "" + totalInc);
                ji += 1;
            }

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

            dataSets.add(lineDataSet2);
            LineData data12 = new LineData(dataSets);


            lineChart.setData(data12);
        });


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void cashFlowLine(ArrayList<Entry> yCash, ArrayList<ILineDataSet> dataSets) {
        yCash.clear();
        lineChart.clear();
        dataSets.clear();

        String dateInString = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
        dateTime = LocalDate.parse(dateInString, formatter);

        int j = 0;
        for (int i = 6; i > -1; i--) {

            LocalDate day = dateTime.minusDays(i);
            int totalExp = database.getSumByDateExpense(SQLiteHandler.KEY_AMOUNT, day.toString());
            int totalInc = database.getSumByDateIncome(SQLiteHandler.KEY_AMOUNT, day.toString());

            if (totalExp != 0 && totalInc != 0) {
                if (totalExp > totalInc) {
                    yCash.add(new Entry(j * 100f, (totalExp - totalInc) * -1));
                } else {
                    yCash.add(new Entry(j * 100f, (totalExp - totalInc) * 1));
                }
            }
            Log.e("ERRRRRRRRRRRRRRr", "" + totalExp + " " + totalInc);
            j += 1;
        }


        LineDataSet lineDataSet1 = new LineDataSet(yCash, "Cash Flow");
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

        lineChart.animateY(3000);
        lineChart.setData(data12);
    }
}