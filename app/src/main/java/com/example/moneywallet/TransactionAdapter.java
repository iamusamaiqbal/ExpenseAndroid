package com.example.moneywallet;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    TransactionModel[] transactionList;
    Context context;

    public TransactionAdapter(Context context, TransactionModel[] list) {
        this.transactionList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.record_overview_tail,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String dateInString = transactionList[position].date;
        LocalDate dateTime = LocalDate.parse(dateInString);


        DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern( "E dd" );
        String output = dateTime.format( fLocalDate) ;

        holder.amount.setText(""+transactionList[position].amount);
        holder.title.setText(""+transactionList[position].id);
        holder.date.setText(output);
        holder.day.setText(output);

        if (transactionList[position].cid==1) {
            holder.imageView.setImageResource(R.drawable.shopping_list);
        } else if (transactionList[position].cid==6) {
            holder.imageView.setImageResource(R.drawable.sharring);
        } else if (transactionList[position].cid==8) {
            holder.imageView.setImageResource(R.drawable.loyelty_card);
        } else {
            holder.imageView.setImageResource(R.drawable.setting_back);
        }

    }

    @Override
    public int getItemCount() {
        return transactionList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,amount,date,day;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textView2);
            amount = view.findViewById(R.id.textView6);
            date = view.findViewById(R.id.textView3);
            day = view.findViewById(R.id.OverviewTileDay);
            imageView = view.findViewById(R.id.imageView3);
        }
    }
}
