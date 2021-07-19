package com.example.tsfbank.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tsfbank.Data.UserData;
import com.example.tsfbank.R;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<UserData> al;
    private TextView userName,accNo,balance;

    public UserListAdapter(Context context,ArrayList<UserData> objects) {
        this.context = context;
        al = objects;
    }


    @Override
    public int getCount() {
        return al.size() ;
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

    Log.v("This","worked");

        View List;

            List = LayoutInflater.from(context).inflate(R.layout.user_list, parent, false);

            userName = List.findViewById(R.id.name);
            accNo = List.findViewById(R.id.accno);
            balance = List.findViewById(R.id.balance);
            userName.setText(al.get(position).getName());
            accNo.setText(al.get(position).getAcc_No());
            balance.setText(al.get(position).getBalance()+"  Rs");

        return List;

    }



}
