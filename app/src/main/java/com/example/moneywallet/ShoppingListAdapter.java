package com.example.moneywallet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    ShoppingListModel[] shoppingList;
    Context context;

    ShoppingListAdapter(ShoppingListModel[] shoppingList, Context context){
        this.context = context;
        this.shoppingList = shoppingList;
    }
    @NonNull
    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.shopping_list_model,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ViewHolder holder, int position) {

        holder.item.setText(""+shoppingList[position].totalitem);
        holder.name.setText(shoppingList[position].name);
        holder.amount.setText(""+shoppingList[position].amount);
        holder.id = shoppingList[position].id;

    }

    @Override
    public int getItemCount() {
        return shoppingList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,amount,item;
        int id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.shoppingListName);
            amount = itemView.findViewById(R.id.shoppingListAmount);
            item = itemView.findViewById(R.id.shoppingListItems);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,shopping_item_list.class);
            intent.putExtra("sid",""+id);
            context.startActivity(intent);
        }
    }
}
