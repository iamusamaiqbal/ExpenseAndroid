package com.example.moneywallet;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        TextView name,amount,date,add,debtActiveAdd;
        CardView debt_model_card;

        Dialog mDialog;
        int id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.debtActiveName);
            amount = itemView.findViewById(R.id.debtActiveAmount);
            date = itemView.findViewById(R.id.debtActiveDate);
            add = itemView.findViewById(R.id.debtActiveAdd);
            debtActiveAdd=itemView.findViewById(R.id.debtActiveAdd);


            mDialog=new Dialog(context);


            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(context,debt_add_record_list.class);
                context.startActivity(intent);
            });


            add.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (debtActiveAdd.equals(v)) {
                mDialog.setContentView(R.layout.activity_debt_popup);

                TextView te = mDialog.findViewById(R.id.popup_debt_linear_4);
                te.setOnClickListener(v1 -> {
                    Intent intent=new Intent(context,Create_debt_record.class);
                    context.startActivity(intent);
                });

                TextView tv = mDialog.findViewById(R.id.popup_debt_linear_5);
                tv.setOnClickListener(v1 -> {
                    Intent intent=new Intent(context,Create_debt_record.class);
                    context.startActivity(intent);
                });

                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();



            }

//            debtActiveAdd.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mDialog.setContentView(R.layout.activity_debt_popup);
//                    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                }
//            });

        }
    }
}
