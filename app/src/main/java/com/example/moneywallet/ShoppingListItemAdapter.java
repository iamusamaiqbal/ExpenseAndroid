package com.example.moneywallet;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListItemAdapter extends RecyclerView.Adapter<ShoppingListItemAdapter.VewHolder> {
    ShoppingItemModel[] itemlist;
    Context context;
    ImageView image_menu;
    SQLiteHandler database = new SQLiteHandler(context);


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

        SQLiteHandler database = new SQLiteHandler(context);

        holder.name.setText(itemlist[position].name);
        holder.amount.setText("" + itemlist[position].amount);
        holder.isChecked = itemlist[position].isChecked;
        holder.id = itemlist[position].id;

        if (holder.isChecked == 1) {
            holder.checkBox.setChecked(true);
            holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (holder.checkBox.isChecked()) {
                database.updateItem(new ShoppingItemModel(itemlist[position].name, itemlist[position].amount, itemlist[position].sid, 1), itemlist[position].id);
                holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            } else {

                database.updateItem(new ShoppingItemModel(itemlist[position].name, itemlist[position].amount, itemlist[position].sid, 0), itemlist[position].id);
                holder.name.setPaintFlags(0);
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemlist.length;
    }

    public class VewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private static final String TAG="MyViewHolder";

        TextView name, amount;
        CheckBox checkBox;
        int isChecked;
        int id;

        public VewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.listItemName);
            amount = itemView.findViewById(R.id.listItemAmount);
            checkBox = itemView.findViewById(R.id.listItemCB);
            image_menu=itemView.findViewById(R.id.image_menu);
            image_menu.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,"onClick:"+getAbsoluteAdapterPosition());
            showPopupmenu(v);

        }
        private void showPopupmenu(View view){
            PopupMenu popupMenu=new PopupMenu(view .getContext(),view);
            popupMenu.inflate(R.menu.shoping_list_item_menu);

            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.delet_item:
                        boolean f = database.deleteItem(id);
                        if(f){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            });
            //displaying the popup
            popupMenu.show();

        }

    }

}
