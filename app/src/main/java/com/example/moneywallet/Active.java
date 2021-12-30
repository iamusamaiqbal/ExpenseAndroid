package com.example.moneywallet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneywallet.models.DebtModel;

import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class Active extends Fragment {
    RecyclerView rv,debtActiveRV_2;

    DebtActiveAdapter activeAdapter;
    SQLiteHandler database;
    List<DebtModel> debtlist,debtlistb;

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
        debtActiveRV_2=view.findViewById(R.id.debtActiveRV_2);

//        Currency currency = Currency.getInstance(new Locale("PKR"));
//        String currencyCode = currency.getCurrencyCode();
//
//        Log.e("Currency",currencyCode);

        database = new SQLiteHandler(getActivity());

        debtlist = database.getAllDebt("lent",String.valueOf(1));
        debtlistb = database.getAllDebt("borrow",String.valueOf(1));

        DebtModel[] debtArray = debtlist.toArray(new DebtModel[0]);
        DebtModel[] debtArray2 = debtlistb.toArray(new DebtModel[0]);


        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        debtActiveRV_2.setLayoutManager(new LinearLayoutManager(getActivity()));

        activeAdapter = new DebtActiveAdapter(debtArray,getActivity(),1);
        activeAdapter = new DebtActiveAdapter(debtArray2,getActivity(),1);

        rv.setAdapter(activeAdapter);
        debtActiveRV_2.setAdapter(activeAdapter);

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