package com.example.moneywallet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneywallet.models.GoalModel;

import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder> {
    Context context;
    List<GoalModel> goallist;

    GoalAdapter(Context context, List<GoalModel> goallist){
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

        holder.goaldate.setText(goallist.get(position).date);
        holder.goalamount.setText(""+ goallist.get(position).amount);
        holder.goalname.setText(goallist.get(position).name);
        holder.goalAS.setText(""+ goallist.get(position).alreadySaved);
        holder.id = goallist.get(position).id;

    }

    @Override
    public int getItemCount() {
        return goallist.size();
    }

    public void remove(int position) {
        goallist.remove(position);
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
