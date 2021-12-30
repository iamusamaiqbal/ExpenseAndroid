package com.example.moneywallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneywallet.models.RecordModel;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    List<RecordModel> recordlist;
    Context context;

    RecordAdapter(Context context, List<RecordModel> recordlist){
        this.recordlist = recordlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.debts_records_model, null,true);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {

        holder.date.setText(recordlist.get(position).date);
        holder.amount.setText(""+ recordlist.get(position).amount);
        holder.name.setText(recordlist.get(position).action);
        holder.account.setText(recordlist.get(position).account);
        holder.id = recordlist.get(position).id;

    }

    @Override
    public int getItemCount() {
        return recordlist.size();
    }

    public void remove(int position) {
        recordlist.remove(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,date,amount,account;
        int id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.recordName);
            date = itemView.findViewById(R.id.recordDate);
            amount = itemView.findViewById(R.id.recordAmount);
            account = itemView.findViewById(R.id.recordAccount);

        }
    }
}
