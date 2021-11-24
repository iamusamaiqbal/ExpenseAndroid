package com.example.moneywallet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class accounts extends Fragment {
    String[] mobileArray = {"dadsd", "dsdadada", "sdsddsdsdasd"};
    MyListAdapter adapter;
    ListView listView;
    Button showMore, pieChartShowMore;

    int sum;

    SQLiteHandler database;

    List<TransactionModel> transactionList = new ArrayList<>();
    List<Integer> cat = new ArrayList<>();


    List<PieEntry> entries = new ArrayList<>();
    final int[] MY_COLORS = {
            Color.rgb(192, 0, 0),
            Color.rgb(255, 0, 0),
            Color.rgb(255, 192, 0),
            Color.rgb(127, 127, 127),
            Color.rgb(146, 208, 80),
            Color.rgb(0, 176, 80),
            Color.rgb(79, 129, 189)};

    public accounts() {
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
        View v = inflater.inflate(R.layout.fragment_accounts, container, false);

        PieChart chart = v.findViewById(R.id.pieChart);
        LineChart lineChart = v.findViewById(R.id.lineChart);

        listView = v.findViewById(R.id.list_view_account);
        showMore = v.findViewById(R.id.show_more);
        pieChartShowMore = v.findViewById(R.id.PieChartShowMore);

        database = new SQLiteHandler(getActivity());
        sum = database.getSum(SQLiteHandler.KEY_AMOUNT);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : MY_COLORS) colors.add(c);

        transactionList = database.getAllTransaction();

        TransactionModel[] transactionArray = transactionList.toArray(new TransactionModel[0]);

        adapter = new MyListAdapter(getActivity(), transactionArray);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener((parent, view, position, id) -> {
                    Log.e("List", "" + position);
                }
        );

        showMore.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(),TransactionActivity.class);
            startActivity(intent);
        });

        pieChartShowMore.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(),statistic.class);
            getContext().startActivity(intent);
        });

        transactionList.forEach(transactionModel -> {
            if (!cat.contains(transactionModel.cid)){
                cat.add(transactionModel.cid);
            }
        });


        cat.forEach(cat -> {
            int total = database.getSumByCat(SQLiteHandler.KEY_AMOUNT,cat);
            float result = total*100/sum;

            entries.add(new PieEntry(result*1f));
        });

        //PieChart

        PieDataSet set = new PieDataSet(entries, " Total : "+sum);
        PieData data = new PieData(set);
        set.setColors(colors);
        chart.setDrawHoleEnabled(false);
        chart.setData(data);



        //LineChart

        ArrayList<Entry> yExpense = new ArrayList<>();
        ArrayList<Entry> yIncome = new ArrayList<>();

        yExpense.add(new Entry(0f,0));
        yIncome.add(new Entry(0f,0));

        transactionList.forEach(amount -> {
            if(amount.isExpense == 1){
                yExpense.add(new Entry(amount.id*100f,amount.amount*1));
            } else {
                yIncome.add(new Entry(amount.id*100f,amount.amount*1));
            }
        });

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


        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        LineDataSet lineDataSet1 = new LineDataSet(yExpense, "Expense");
        lineDataSet1.setValueTextSize(10);
        lineDataSet1.setDrawCircleHole(true);
        lineDataSet1.setColor(Color.RED);
        lineDataSet1.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setCubicIntensity(0.15f);
        lineDataSet1.setCircleColor(Color.MAGENTA);
        lineDataSet1.setLineWidth(1);
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);



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

        final ArrayList<String> labels = new ArrayList<String>();
        labels.add("12AM");
        labels.add("06AM");
        labels.add("12PM");
        labels.add("06PM");
        labels.add("12AM");

        lineDataSet1.setDrawFilled(true);
        lineDataSet1.setFillColor(Color.RED);

        lineDataSet2.setDrawFilled(true);
        lineDataSet2.setFillColor(Color.BLUE);
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet1);

        LineData data12 = new LineData(dataSets);


        lineChart.setData(data12);


        return v;
    }
}