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

        title.setText(""+mylist[position].id);
        amount.setText(""+mylist[position].amount);
        subtitleText.setText(output);

        //System.out.println(s);

        if (mylist[position].cid==1) {
            imageView.setImageResource(R.drawable.shopping_list);
        } else if (mylist[position].cid==6) {
            imageView.setImageResource(R.drawable.sharring);
        } else if (mylist[position].cid==8) {
            imageView.setImageResource(R.drawable.loyelty_card);
        } else {
            imageView.setImageResource(R.drawable.setting_back);
        }
        return rowView;

    };
}
