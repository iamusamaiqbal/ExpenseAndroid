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

import com.example.moneywallet.models.TransactionModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    List<TransactionModel> transactionList;
    Context context;

    public TransactionAdapter(Context context, List<TransactionModel> list) {
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

        String dateInString = transactionList.get(position).date;
        LocalDate dateTime = LocalDate.parse(dateInString);


        DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern( "E dd" );
        String output = dateTime.format( fLocalDate) ;

        holder.amount.setText(""+ transactionList.get(position).amount);
        holder.title.setText(""+ transactionList.get(position).cat);
        holder.date.setText(output);
        holder.day.setText(output);

        if (transactionList.get(position).cat.equals("Food & Drinks")) {
            holder.imageView.setImageResource(R.drawable.ic_dinner);
        } else if (transactionList.get(position).cat.equals("Shopping")) {
            holder.imageView.setImageResource(R.drawable.ic_shoppinggg);
        } else if (transactionList.get(position).cat.equals("Housing")) {
            holder.imageView.setImageResource(R.drawable.ic_house);
        } else if (transactionList.get(position).cat.equals("Transportation")) {
            holder.imageView.setImageResource(R.drawable.ic_bus);
        } else if (transactionList.get(position).cat.equals("Vehicle")) {
            holder.imageView.setImageResource(R.drawable.ic_carrrr);
        } else if (transactionList.get(position).cat.equals("Life & Entertainment")) {
            holder.imageView.setImageResource(R.drawable.ic_entertainment);
        } else if (transactionList.get(position).cat.equals("Communication")) {
            holder.imageView.setImageResource(R.drawable.ic_laptop_svgrepo_com);
        } else if (transactionList.get(position).cat.equals("Financial Expenses")) {
            holder.imageView.setImageResource(R.drawable.ic_investment_svgrepo_com);
        } else if (transactionList.get(position).cat.equals("Investments")) {
            holder.imageView.setImageResource(R.drawable.ic_investment);
        } else if (transactionList.get(position).cat.equals("Income")) {
            holder.imageView.setImageResource(R.drawable.ic_incom);
        } else if (transactionList.get(position).cat.equals("Other")) {
            holder.imageView.setImageResource(R.drawable.ic_other);
        } else {
            holder.imageView.setImageResource(R.drawable.setting_back);
        }

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public void remove(int position) {
        transactionList.remove(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,amount,date,day;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.textView2);
            amount = view.findViewById(R.id.textView6);
            date = view.findViewById(R.id.textView3);
            day = view.findViewById(R.id.OverviewTileDay);
            imageView = view.findViewById(R.id.imageView3);
        }
    }
}
