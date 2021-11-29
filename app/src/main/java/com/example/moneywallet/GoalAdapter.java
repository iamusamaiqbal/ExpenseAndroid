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

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder> {
    Context context;
    GoalModel[] goallist;

    GoalAdapter(Context context, GoalModel[] goallist){
        this.context= context;
        this.goallist = goallist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.active_goal_model,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.goaldate.setText(goallist[position].date);
        holder.goalamount.setText(""+goallist[position].amount);
        holder.goalname.setText(goallist[position].name);
        holder.goalAS.setText(""+goallist[position].alreadySaved);
        holder.id = goallist[position].id;

    }

    @Override
    public int getItemCount() {
        return goallist.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView goalname,goaldate,goalamount,goalAS;
        int id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            goalname = itemView.findViewById(R.id.goalRowNameTV);
            goalamount = itemView.findViewById(R.id.goalRowAmountTV);
            goalAS = itemView.findViewById(R.id.goalRowSavedTV);
            goaldate = itemView.findViewById(R.id.goalRowDateTV);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(),Goal_details.class);
            intent.putExtra("gid",""+id);
            v.getContext().startActivity(intent);

        }
    }
}
