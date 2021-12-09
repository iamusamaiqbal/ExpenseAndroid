package com.example.moneywallet;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneywallet.models.BudgetModel;

public class PeriodicAdapter extends RecyclerView.Adapter<PeriodicAdapter.ViewHolder> {

    BudgetModel[] budgetList;
    Context context;

    PeriodicAdapter(Context context, BudgetModel[] budgetList){
        this.budgetList = budgetList;
        this.context = context;
    }


    @NonNull
    @Override
    public PeriodicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.budget_view,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeriodicAdapter.ViewHolder holder, int position) {

        holder.budgetTotal.setText(""+budgetList[position].amount);
        holder.period.setText("Last : "+budgetList[position].end);
        holder.name.setText(""+budgetList[position].name);
        holder.id = budgetList[position].id;

    }

    @Override
    public int getItemCount() {
        return budgetList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView period,name,budgetTotal,expanse;
        int id;

        public ViewHolder(View view) {
            super(view);
            period = view.findViewById(R.id.budgetPeriod);
            name = view.findViewById(R.id.budgetName);
            budgetTotal = view.findViewById(R.id.budgetTotal);
            expanse = view.findViewById(R.id.budgetExpanse);
//            day = view.findViewById(R.id.OverviewTileDay);
//            imageView = view.findViewById(R.id.imageView3);

            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            Log.e("Errrrr","rrrrr");
            Intent intent = new Intent(v.getContext(),BudgetDetailActivity.class);
            intent.putExtra("bid",""+id);
            v.getContext().startActivity(intent);
        }
    }
}
