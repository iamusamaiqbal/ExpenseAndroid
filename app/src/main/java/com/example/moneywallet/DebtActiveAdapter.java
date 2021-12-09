package com.example.moneywallet;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneywallet.models.DebtModel;

public class DebtActiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    DebtModel[] debtlist;
    Context context;
    int flag;

    final int VIEW_TYPE_ONE = 1;
    final int VIEW_TYPE_TWO = 2;


    DebtActiveAdapter(DebtModel[] debtlist,Context context ,int flag){
        this.debtlist = debtlist;
        this.context = context;
        this.flag = flag;
    }

    @Override
    public int getItemViewType(int position) {
        if(debtlist[position].isActive==0){
            return VIEW_TYPE_ONE;
        } else {
            return VIEW_TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if(viewType==VIEW_TYPE_ONE){
            view = LayoutInflater.from(context).inflate(R.layout.debt_close_model,parent,false);
            return new ViewHolder2(view);
        } else{
            view = LayoutInflater.from(context).inflate(R.layout.debts_model,parent,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (debtlist[position].isActive == 0) {
            ((ViewHolder2) holder).bind(position);
        } else {
            ((ViewHolder) holder).bind(position);
        }

    }

    @Override
    public int getItemCount() {
        return debtlist.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,amount,date,add,debtActiveAdd,description;
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
            description = itemView.findViewById(R.id.debtActiveDes);


            mDialog=new Dialog(context);


            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(context,debt_add_record_list.class);
                intent.putExtra("did",""+id);
                context.startActivity(intent);
            });


            add.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (debtActiveAdd.equals(v)) {
                mDialog.setContentView(R.layout.activity_debt_popup);

                TextView te = mDialog.findViewById(R.id.popup_debt_linear_4);
                TextView tv = mDialog.findViewById(R.id.popup_debt_linear_5);

                te.setOnClickListener(v1 -> {
                    Intent intent=new Intent(context,Create_debt_record.class);
                    intent.putExtra("did",""+id);
                    context.startActivity(intent);
                });

                tv.setOnClickListener(v1 -> {
                    Intent intent=new Intent(context,Create_debt_record.class);
                    intent.putExtra("did",""+id);
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

        public void bind(int position) {
            date.setText(debtlist[position].date);
            amount.setText(""+debtlist[position].amount);
            name.setText(debtlist[position].name);
            id = debtlist[position].id;
            description.setText(debtlist[position].description);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,amount,date,add,debtActiveAdd,description;
        CardView debt_model_card;

        Dialog mDialog;
        int id;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.debtCloseName);
            amount = itemView.findViewById(R.id.debtCloseAmount);
            date = itemView.findViewById(R.id.debtCloseDate);
            description = itemView.findViewById(R.id.debtCloseDes);


            mDialog=new Dialog(context);


            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(context,debt_add_record_list.class);
                intent.putExtra("did",""+id);
                context.startActivity(intent);
            });

        }

        @Override
        public void onClick(View v) {
            if (debtActiveAdd.equals(v)) {
                mDialog.setContentView(R.layout.activity_debt_popup);

                TextView te = mDialog.findViewById(R.id.popup_debt_linear_4);
                TextView tv = mDialog.findViewById(R.id.popup_debt_linear_5);

                te.setOnClickListener(v1 -> {
                    Intent intent=new Intent(context,Create_debt_record.class);
                    intent.putExtra("did",""+id);
                    context.startActivity(intent);
                });

                tv.setOnClickListener(v1 -> {
                    Intent intent=new Intent(context,Create_debt_record.class);
                    intent.putExtra("did",""+id);
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

        public void bind(int position) {
            date.setText(debtlist[position].date);
            amount.setText(""+debtlist[position].amount);
            name.setText(debtlist[position].name);
            id = debtlist[position].id;
            description.setText(debtlist[position].description);
        }
    }
}
