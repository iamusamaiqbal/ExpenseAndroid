package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class BudgetDetailActivity extends AppCompatActivity {

    List<TransactionModel> transactionList ;
    ArrayList<Integer> colors;
    List<String> dates;
    List<String> categories;
    BudgetModel budget;
    int total = 0,id=0;

    SQLiteHandler database;

    List<PieEntry> entries = new ArrayList<>();

    final int[] MY_COLORS = {
            Color.rgb(192, 0, 0),
            Color.rgb(255, 0, 0),
            Color.rgb(255, 192, 0),
            Color.rgb(127, 127, 127),
            Color.rgb(146, 208, 80),
            Color.rgb(0, 176, 80),
            Color.rgb(79, 129, 189)};

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_titel);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE , PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.calculater)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        PieChart chart = findViewById(R.id.budgetDetailPieChart);
        BarChart barChart = findViewById(R.id.budgetDetailBarChart);
        transactionList = new ArrayList<>();
        colors = new ArrayList<>();
        dates = new ArrayList<>();
        categories = new ArrayList<>();
        database = new SQLiteHandler(this);


        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("bid"));

        budget = database.getBudget(id);
        Date date = new Date();

        String dateInString = date.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
        LocalDate currentDate = LocalDate.parse(dateInString, formatter);

        LocalDate currentDatePlus7 = currentDate.plusDays(7);
        LocalDate currentDatePlus30 = currentDate.plusDays(30);
        LocalDate currentDatePlus365 = currentDate.plusDays(365);

        database = new SQLiteHandler(this);

        transactionList = database.getAllTransaction();

        ArrayList NoOfEmp = new ArrayList();


        transactionList.forEach(transactionModel -> {

            LocalDate dateTime = LocalDate.parse(transactionModel.date);

            if ((dateTime.isEqual(LocalDate.parse(budget.start)) || dateTime.isAfter(LocalDate.parse(budget.start))) && (dateTime.isEqual(LocalDate.parse(budget.end)) || dateTime.isBefore(LocalDate.parse(budget.end)))) {
                if(!dates.contains(dateTime.toString())){
                    total=database.getSumByDate(SQLiteHandler.KEY_AMOUNT,dateTime.toString());
                    NoOfEmp.add(new BarEntry( transactionModel.id*400f, total*1));
                    dates.add(dateTime.toString());
                }
            }
        });

        //BarChart



//        NoOfEmp.add(new BarEntry(300f, 0));
//        NoOfEmp.add(new BarEntry(600f, 1));
//        NoOfEmp.add(new BarEntry(900f, 2));
//        NoOfEmp.add(new BarEntry(1200f, 3));
//        NoOfEmp.add(new BarEntry(1500f, 4));
//        NoOfEmp.add(new BarEntry(1800f, 5));
//        NoOfEmp.add(new BarEntry(2100f, 6));
//        NoOfEmp.add(new BarEntry(2400f, 7));
//        NoOfEmp.add(new BarEntry(2700f, 8));
//        NoOfEmp.add(new BarEntry(3000f, 9));

        String[] xAxisLables = new String[]{"1","2", "3", "4", "5","6","7"};

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Expenses");
        barChart.animateY(5000);
        BarData data2 = new BarData(bardataset);
        data2.setBarWidth(100f);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data2);



        transactionList.forEach(transactionModel -> {


            if (!categories.contains(transactionModel.cat)) {
                total = database.getSumByCat(SQLiteHandler.KEY_AMOUNT,transactionModel.cat);
                entries.add(new PieEntry(total*1f));
                categories.add(transactionModel.cat);
            }
        });

        for (int c : MY_COLORS) colors.add(c);

        PieDataSet set = new PieDataSet(entries, " Total : "+5000);
        PieData data = new PieData(set);
        set.setColors(colors);
        chart.animateXY(5000, 5000);
        chart.setDrawHoleEnabled(false);
        chart.setData(data);


    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.budget_detail_meu, menu);
        ((MenuBuilder) menu).setOptionalIconsVisible(true);



        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//
            if (item.getItemId() == R.id.edit) {
                Toast.makeText(this, "edit clicked", Toast.LENGTH_SHORT).show();

            } else if (item.getItemId() == R.id.delete) {
                Toast.makeText(this, "delete clicked", Toast.LENGTH_SHORT).show();

            }else if (item.getItemId()==R.id.close){
                Toast.makeText(this, "close clicked", Toast.LENGTH_SHORT).show();
            }
            return super.onOptionsItemSelected(item);


    }
}