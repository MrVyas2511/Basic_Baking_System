package com.example.tsfbank;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.tsfbank.Adapter.TrasactionAdapter;
import com.example.tsfbank.Data.Transaction;
import com.example.tsfbank.databases.TransactionDBContract;
import com.example.tsfbank.databases.TransactionDBHelper;
import com.example.tsfbank.databases.UserDBContract;

import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {

    ArrayList<Transaction> transactionArray;
    private TransactionDBHelper dbHelper;
    ListView listView;
    TrasactionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        dbHelper = new TransactionDBHelper(this);
        transactionArray = new ArrayList<Transaction>();

        databaseinfo();

        listView = findViewById(R.id.transactionitem);
        adapter = new TrasactionAdapter(this,transactionArray);
        listView.setAdapter(adapter);



    }

    private void databaseinfo() {

            transactionArray.clear();

        SQLiteDatabase mDb = dbHelper.getReadableDatabase();
        String[] projection = {
                TransactionDBContract.transactionEntry.FROM_NAME,
                TransactionDBContract.transactionEntry.TO_NAME,
                TransactionDBContract.transactionEntry.AMOUNT,
                TransactionDBContract.transactionEntry.TIME,
                TransactionDBContract.transactionEntry.STATUS
        };
        Cursor cursor = mDb.query(TransactionDBContract.transactionEntry.TABLE_NAME,projection,null,null,null,null,null);


        int fromNameColumn = cursor.getColumnIndex(TransactionDBContract.transactionEntry.FROM_NAME);
        int toNameColumn = cursor.getColumnIndex(TransactionDBContract.transactionEntry.TO_NAME);
        int AmountColumn = cursor.getColumnIndex(TransactionDBContract.transactionEntry.AMOUNT);
        int timeColumn = cursor.getColumnIndex(TransactionDBContract.transactionEntry.TIME);
        int statusColumn = cursor.getColumnIndex(TransactionDBContract.transactionEntry.STATUS);

        while (cursor.moveToNext()) {

            String fromName = cursor.getString(fromNameColumn);
            String toName = cursor.getString(toNameColumn);
            String Amount = cursor.getString(AmountColumn);
            String Time = cursor.getString(timeColumn);
            Integer status = cursor.getInt(statusColumn);

            transactionArray.add(new Transaction(fromName,toName,Amount,Time,status));
            Log.v("Transactioncount", String.valueOf(transactionArray.size()));
        }
    }

}