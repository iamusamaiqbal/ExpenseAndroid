package com.example.moneywallet;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class MyListAdapter extends ArrayAdapter<TransactionModel> {

    private final Activity context;
    TransactionModel[] mylist;

    public MyListAdapter(Activity context, TransactionModel[] maintitle) {
        super(context, R.layout.record_overview_tail,maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.mylist=maintitle;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.record_overview_tail, null,true);


        String dateInString = mylist[position].date;
        LocalDate dateTime = LocalDate.parse(dateInString);


        DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern( "E dd" );
        String output = dateTime.format( fLocalDate) ;

        TextView title = rowView.findViewById(R.id.textView2);
        TextView amount = rowView.findViewById(R.id.textView6);
        TextView subtitleText = rowView.findViewById(R.id.textView3);
        ImageView imageView = rowView.findViewById(R.id.imageView3);

        title.setText(""+mylist[position].cat);
        amount.setText(""+mylist[position].amount);
        subtitleText.setText(output);

        //System.out.println(s);

        if (mylist[position].cat.equals("Food & Drinks")) {
            imageView.setImageResource(R.drawable.ic_dinner);
        } else if (mylist[position].cat.equals("Shopping")) {
            imageView.setImageResource(R.drawable.ic_shoppinggg);
        } else if (mylist[position].cat.equals("Housing")) {
            imageView.setImageResource(R.drawable.ic_house);
        } else if (mylist[position].cat.equals("Transportation")) {
            imageView.setImageResource(R.drawable.ic_bus);
        } else if (mylist[position].cat.equals("Vehicle")) {
            imageView.setImageResource(R.drawable.ic_carrrr);
        } else if (mylist[position].cat.equals("Life & Entertainment")) {
            imageView.setImageResource(R.drawable.ic_entertainment);
        } else if (mylist[position].cat.equals("Communication")) {
            imageView.setImageResource(R.drawable.ic_laptop_svgrepo_com);
        } else if (mylist[position].cat.equals("Financial Expenses")) {
            imageView.setImageResource(R.drawable.ic_investment_svgrepo_com);
        } else if (mylist[position].cat.equals("Investments")) {
            imageView.setImageResource(R.drawable.ic_investment);
        } else if (mylist[position].cat.equals("Income")) {
            imageView.setImageResource(R.drawable.ic_incom);
        } else if (mylist[position].cat.equals("Other")) {
            imageView.setImageResource(R.drawable.ic_other);
        } else {
            imageView.setImageResource(R.drawable.setting_back);
        }
        return rowView;

    };
}
