package com.example.moneywallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReachedAdapter extends RecyclerView.Adapter<ReachedAdapter.ViewHolder> {

    Context context;
    GoalModel[] goallist;

    ReachedAdapter(Context context, GoalModel[] goallist){
        this.context= context;
        this.goallist = goallist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.goal_reached_model,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.date.setText("Date : "+goallist[position].date);
        holder.name.setText(goallist[position].name);
        holder.save.setText("Saved : "+goallist[position].alreadySaved);

    }

    @Override
    public int getItemCount() {
        return goallist.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,save,date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.goalReachedNameTV);
            save = itemView.findViewById(R.id.goalReachedSavedTV);
            date = itemView.findViewById(R.id.goalReachedDateTV);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
