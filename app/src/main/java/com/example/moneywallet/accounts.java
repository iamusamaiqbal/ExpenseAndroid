package com.example.moneywallet;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moneywallet.models.TransactionModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class accounts extends Fragment {
    MyListAdapter adapter;
    ListView listView;
    Button showMore, pieChartShowMore;
    Date date;
    ImageView crd_menu_id;
    LocalDate dateTime;
    View v;
    TextView cash_price;

    int expsum, incsum;
    String StringDate;

    SQLiteHandler database;

    List<TransactionModel> transactionList = new ArrayList<>();
    List<String> cat = new ArrayList<>();


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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_accounts, container, false);

        listView = v.findViewById(R.id.list_view_account);
        crd_menu_id=v.findViewById(R.id.crd_menu_id);

        showMore = v.findViewById(R.id.show_more);

        pieChartShowMore = v.findViewById(R.id.PieChartShowMore);
        cash_price = v.findViewById(R.id.cash_price);
        date = new Date();



        String dateInString = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
        dateTime = LocalDate.parse(dateInString, formatter);

        content(v);

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();
        content(v);

        transactionList = database.getAllTransaction();

        TransactionModel[] transactionArray = transactionList.toArray(new TransactionModel[0]);

        adapter = new MyListAdapter(getActivity(), transactionArray);
        listView.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void content(View v) {

        PieChart chart = v.findViewById(R.id.pieChart);
        LineChart lineChart = v.findViewById(R.id.lineChart);
        database = new SQLiteHandler(getActivity());
//        sum = database.getSum(SQLiteHandler.KEY_AMOUNT);

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
            Intent intent = new Intent(getActivity(), statistic.class);
            getContext().startActivity(intent);


        });

        expsum=incsum=0;

        transactionList.forEach(transactionModel -> {
            if (transactionModel.isExpense == 1) {
                if (!cat.contains(transactionModel.cat)) {
                    cat.add(transactionModel.cat);
                }
                expsum += transactionModel.amount;
            } else {
                incsum += transactionModel.amount;
            }
        });

        if (incsum != 0 && expsum != 0) {
            if (expsum > incsum) {
                cash_price.setText(""+((expsum - incsum) * 1));
                cash_price.setTextColor(Color.RED);
            } else {
                cash_price.setText(""+((expsum - incsum) * 1));
                cash_price.setTextColor(Color.BLUE);
            }
        }


        entries.clear();
        cat.forEach(cat -> {
            int total = database.getSumByCat(SQLiteHandler.KEY_AMOUNT, cat);
            float result = total * 100 / expsum;

            entries.add(new PieEntry(result * 1f, "" + cat));
        });

        //PieChart

        PieDataSet set = new PieDataSet(entries, " Total : " + expsum);
        PieData data = new PieData(set);



        set.setColors(
                colors
        );
        chart.setDrawHoleEnabled(false);
        chart.setData(data);


        //LineChart

        ArrayList<Entry> yExpense = new ArrayList<>();
        ArrayList<Entry> yIncome = new ArrayList<>();

        yExpense.add(new Entry(100f, 0f));
        yIncome.add(new Entry(100f, 0f));

        int j = 0;
        for (int i = 6; i > -1; i--) {

            LocalDate day = dateTime.minusDays(i);
            int totalExp = database.getSumByDateExpense(SQLiteHandler.KEY_AMOUNT, day.toString());

            int totalInc = database.getSumByDateIncome(SQLiteHandler.KEY_AMOUNT, day.toString());

            if (totalExp != 0) {

                yExpense.add(new Entry(j * 100f, totalExp * 1));

            }
            if (totalInc != 0) {
                yIncome.add(new Entry(j * 100f, totalInc * 1));
            }

            Log.e("ERRRRRRRRRRRRRRr", "" + totalExp + " " + totalInc);
            j += 1;
        }

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
    }

}