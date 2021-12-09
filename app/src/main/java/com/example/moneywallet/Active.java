package com.example.moneywallet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneywallet.models.DebtModel;

import java.util.List;

public class Active extends Fragment {
    RecyclerView rv;
    DebtActiveAdapter activeAdapter;
    SQLiteHandler database;
    List<DebtModel> debtlist;

    public Active() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active, container, false);

        rv = view.findViewById(R.id.debtActiveRV);

        database = new SQLiteHandler(getActivity());

        debtlist = database.getAllDebt("lent",String.valueOf(1));

        DebtModel[] debtArray = debtlist.toArray(new DebtModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        activeAdapter = new DebtActiveAdapter(debtArray,getActivity(),1);

        rv.setAdapter(activeAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        debtlist = database.getAllDebt("lent",String.valueOf(1));

        DebtModel[] debtArray = debtlist.toArray(new DebtModel[0]);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        activeAdapter = new DebtActiveAdapter(debtArray,getActivity(),1);

        rv.setAdapter(activeAdapter);
    }
}