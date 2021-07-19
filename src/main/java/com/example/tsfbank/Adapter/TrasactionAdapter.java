package com.example.tsfbank.Adapter;

import android.content.Context;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tsfbank.Data.Transaction;
import com.example.tsfbank.R;

import java.util.ArrayList;

public class TrasactionAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Transaction> TransactionList;
    private TextView fromName,toName,Amount,Time;

    public TrasactionAdapter(Context context, ArrayList<Transaction> transactionList) {
        this.context = context;
        TransactionList = transactionList;
    }

    @Override
    public int getCount() {
        return TransactionList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View List;
        List = LayoutInflater.from(context).inflate(R.layout.transaction_data_item,parent,false);

        toName = List.findViewById(R.id.ToName);
        fromName = List.findViewById(R.id.FromName);
        Amount  = List.findViewById(R.id.AmountText);
        Time = List.findViewById(R.id.timetext);

        toName.setText(TransactionList.get(position).getToUser());
        fromName.setText(TransactionList.get(position).getFromUser());
        Amount.setText(TransactionList.get(position).getAmount());
        Time.setText(TransactionList.get(position).getDate());


        return List;
    }
}
