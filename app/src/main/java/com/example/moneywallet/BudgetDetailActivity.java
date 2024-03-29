package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneywallet.models.BudgetModel;
import com.example.moneywallet.models.TransactionModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class BudgetDetailActivity extends AppCompatActivity {

    List<TransactionModel> transactionList;
    ArrayList<Integer> colors;
    List<String> dates;
    List<String> categories;
    BudgetModel budget;
    int total = 0, sum = 0;
    TextView period,budgetTotal,name,expanse;

    String id;
    SQLiteHandler database;


    java.util.HashMap<String, Integer> HashMap = new HashMap<String, Integer>();

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

        PieChart chart = findViewById(R.id.budgetDetailPieChart);
        BarChart barChart = findViewById(R.id.budgetDetailBarChart);
        period = findViewById(R.id.budgetPeriod);
        name = findViewById(R.id.budgetName);
        budgetTotal = findViewById(R.id.budgetTotal);
        expanse = findViewById(R.id.budgetExpanse);
        transactionList = new ArrayList<>();
        colors = new ArrayList<>();
        dates = new ArrayList<>();
        categories = new ArrayList<>();
        database = new SQLiteHandler(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_titel);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE , PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.calculater)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        Intent i = getIntent();
        id = i.getStringExtra("bid");

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


        BudgetModel budgetModel = database.getBudget(id);

        LocalDate s = LocalDate.parse(budgetModel.start);
        LocalDate e = LocalDate.parse(budgetModel.end);

        int diff = (int) java.time.temporal.ChronoUnit.DAYS.between(s, e);

        switch (diff) {
            case 0:
                mTitle.setText("One Time");
                break;
            case 7:
                mTitle.setText("This Week");
                break;
            case 30:
            case 31:
                mTitle.setText("This Month");
                break;
            case 365:
            case 366:
                mTitle.setText("This Year");
                break;
            default:
                Log.e("No Match", " non of these case matches");
        }

        name.setText(budgetModel.name);
        budgetTotal.setText(""+budgetModel.amount);
        period.setVisibility(View.INVISIBLE);

        transactionList.forEach(transactionModel -> {

            LocalDate dateTime = LocalDate.parse(transactionModel.date);

            if ((dateTime.isEqual(LocalDate.parse(budget.start)) || dateTime.isAfter(LocalDate.parse(budget.start))) && (dateTime.isEqual(LocalDate.parse(budget.end)) || dateTime.isBefore(LocalDate.parse(budget.end)))) {
                if (!dates.contains(dateTime.toString())) {
                    total += database.getSumByDate(SQLiteHandler.KEY_AMOUNT, dateTime.toString());
                    dates.add(dateTime.toString());
                }
            }
        });

        if(total>0){
            expanse.setText("Expanses : "+total);
        } else {
            expanse.setText("Expanses : 0");
        }

        //========================================== BarChart =====================================================


        ArrayList NoOfEmp = new ArrayList();


        transactionList.forEach(transactionModel -> {

            LocalDate dateTime = LocalDate.parse(transactionModel.date);

            if ((dateTime.isEqual(LocalDate.parse(budget.start)) || dateTime.isAfter(LocalDate.parse(budget.start))) && (dateTime.isEqual(LocalDate.parse(budget.end)) || dateTime.isBefore(LocalDate.parse(budget.end)))) {
                if (!dates.contains(dateTime.toString())) {
                    total = database.getSumByDate(SQLiteHandler.KEY_AMOUNT, dateTime.toString());
                    NoOfEmp.add(new BarEntry( transactionModel.id * 100f, total * 1));
                    dates.add(dateTime.toString());
                }
            }
        });

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Expenses");
        barChart.animateY(5000);
        BarData data2 = new BarData(bardataset);
        data2.setBarWidth(25f);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter((value, axis) -> {
            String label = "";
            if (value == 100) {
                LocalDate day = currentDate.minusDays(6);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                label = day.format(fLocalDate);
            } else if (value == 200) {
                LocalDate day = currentDate.minusDays(5);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                label = day.format(fLocalDate);
            } else if (value == 300) {
                LocalDate day = currentDate.minusDays(4);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                label = day.format(fLocalDate);
            } else if (value == 400) {
                LocalDate day = currentDate.minusDays(3);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                label = day.format(fLocalDate);
            } else if (value == 500) {
                LocalDate day = currentDate.minusDays(2);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                label = day.format(fLocalDate);
            } else if (value == 600) {
                LocalDate day = currentDate.minusDays(1);
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                label = day.format(fLocalDate);
            } else if (value == 700) {
                DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("E");
                label = currentDate.format(fLocalDate);
            }
            return label;
        });

        barChart.setData(data2);


        //=========================================== Pie Chart ============================================

        sum = 0;
        dates.clear();
        categories.clear();
        transactionList.forEach(transactionModel -> {

            LocalDate dateTime = LocalDate.parse(transactionModel.date);

            if ((dateTime.isEqual(LocalDate.parse(budget.start)) || dateTime.isAfter(LocalDate.parse(budget.start))) && (dateTime.isEqual(LocalDate.parse(budget.end)) || dateTime.isBefore(LocalDate.parse(budget.end))) && transactionModel.isExpense == 1) {
                if (!categories.contains(transactionModel.cat)) {
                    HashMap.put(transactionModel.cat, transactionModel.amount);
                    categories.add(transactionModel.cat);
                } else {
                    int amt = HashMap.get(transactionModel.cat) + transactionModel.amount;
                    HashMap.replace(transactionModel.cat, amt);
                }
            }
        });

        entries.clear();
        HashMap.entrySet().forEach(stringIntegerEntry -> {
            String cat = stringIntegerEntry.getKey();
            int value = stringIntegerEntry.getValue();

            sum += value;

            entries.add(new PieEntry(value * 1f, "" + cat));
        });

        for (int c : MY_COLORS) colors.add(c);

        PieDataSet set = new PieDataSet(entries, " Total : " + sum);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.edit) {

            Intent i = new Intent(this,new_budget.class);
            i.putExtra("bid",""+id);
            startActivity(i);

            Toast.makeText(this, "clicked edit", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.delete) {

            boolean f = database.deleteBudget(id);
            if(f){
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }


        } else if (item.getItemId() == R.id.close) {
            onBackPressed();
            Toast.makeText(this, "close", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }
}