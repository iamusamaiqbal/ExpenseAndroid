package com.example.moneywallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class personAdapter extends ArrayAdapter<person> {
    private Context mcontext;
    private int mresourse;


    public personAdapter(@NonNull Context context, int resource, @NonNull ArrayList<person> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mresourse=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);

        convertView=layoutInflater.inflate(mresourse,parent,false);
        ImageView imageView=convertView.findViewById(R.id.custom_list_image);
        TextView textView=convertView.findViewById(R.id.custom_text);

        imageView.setImageResource(getItem(position).image);
        textView.setText(getItem(position).name);

        return convertView ;

    }
}