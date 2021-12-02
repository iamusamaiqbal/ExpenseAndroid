package com.example.moneywallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DebtActiveAdapter extends RecyclerView.Adapter<DebtActiveAdapter.ViewHolder> {
    DebtModel[] debtlist;
    Context context;

    DebtActiveAdapter(DebtModel[] debtlist,Context context){
        this.debtlist = debtlist;
        this.context = context;
    }
    @NonNull
    @Override
    public DebtActiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.debts_model,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DebtActiveAdapter.ViewHolder holder, int position) {

        holder.date.setText(debtlist[position].date);
        holder.amount.setText(""+debtlist[position].amount);
        holder.name.setText(debtlist[position].name);
        holder.id = debtlist[position].id;

    }

    @Override
    public int getItemCount() {
        return debtlist.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,amount,date;
        int id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.debtActiveName);
            amount = itemView.findViewById(R.id.debtActiveAmount);
            date = itemView.findViewById(R.id.debtActiveDate);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
