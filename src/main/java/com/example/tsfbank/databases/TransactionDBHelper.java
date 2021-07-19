package com.example.tsfbank.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class TransactionDBHelper extends SQLiteOpenHelper {



    private static final  String DATABASE_NAME = "transaction.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = TransactionDBContract.transactionEntry.TABLE_NAME;

    public TransactionDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TRANSACTION_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("
                + TransactionDBContract.transactionEntry.FROM_NAME + " TEXT, "
                + TransactionDBContract.transactionEntry.TO_NAME + " TEXT, "
                + TransactionDBContract.transactionEntry.AMOUNT + " TEXT, "
                + TransactionDBContract.transactionEntry.TIME + " TEXT, "
                + TransactionDBContract.transactionEntry.STATUS+ " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertTransaction(String fromUser, String toUser, String Amount, String Time,Integer status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransactionDBContract.transactionEntry.FROM_NAME,fromUser);
        contentValues.put(TransactionDBContract.transactionEntry.TO_NAME,toUser);
        contentValues.put(TransactionDBContract.transactionEntry.AMOUNT,Amount);
        contentValues.put(TransactionDBContract.transactionEntry.TIME,Time);
        contentValues.put(TransactionDBContract.transactionEntry.STATUS,status);

        db.insert(TABLE_NAME, null, contentValues);

        return true;
    }
}
