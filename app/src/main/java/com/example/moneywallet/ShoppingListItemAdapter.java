package com.example.moneywallet;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListItemAdapter extends RecyclerView.Adapter<ShoppingListItemAdapter.VewHolder> {
    ShoppingItemModel[] itemlist;
    Context context;

    ShoppingListItemAdapter(ShoppingItemModel[] itemlist, Context context) {
        this.itemlist = itemlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ShoppingListItemAdapter.VewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.shopping_record_model, parent, false);

        return new VewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListItemAdapter.VewHolder holder, int position) {

        holder.name.setText(itemlist[position].name);
        holder.amount.setText(itemlist[position].amount);
        holder.isChecked = itemlist[position].isChecked;

    }

    @Override
    public int getItemCount() {
        return itemlist.length;
    }

    public class VewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, amount;
        CheckBox checkBox;
        int isChecked;

        public VewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.listItemName);
            amount = itemView.findViewById(R.id.listItemAmount);
            checkBox = itemView.findViewById(R.id.listItemCB);
            if (isChecked != 0){

                checkBox.isChecked();
                name.setPaintFlags(name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (checkBox.isChecked()) {
                    name.setPaintFlags(name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    Log.e("uuuu", "jjjjjjjjjjjjjjj");
                } else {

                }
                Log.e("uuuu", "jjkkkkkkkkkkkkkkkkkk");
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
