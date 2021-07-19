package com.example.tsfbank.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserDBHelper extends SQLiteOpenHelper {


    private static final  String DATABASE_NAME = "Users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = UserDBContract.UserEntry.TABLE_NAME;

    public UserDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + UserDBContract.UserEntry.TABLE_NAME +"("
                + UserDBContract.UserEntry.ACC_NO + " TEXT, "
                + UserDBContract.UserEntry.Name + " TEXT, "
                + UserDBContract.UserEntry.IFSC_NO +" TEXT, "
                + UserDBContract.UserEntry.EMAIL + " TEXT, "
                + UserDBContract.UserEntry.BALANCE +" TEXT, "
                + UserDBContract.UserEntry.CITY +" TEXT, "
                + UserDBContract.UserEntry.MO_NUMBER+" TEXT );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);


        db.execSQL("insert into " + TABLE_NAME + " values('123456789','Tanishq Saini', '0000', 'tanishq@gmail.com','18000','ahemdabad','7895641238')");
        db.execSQL("insert into " + TABLE_NAME + " values('22222222','Gagan Tripathi', '1111','gagan@gmail.com','50505','surat','8995641238')");
        db.execSQL("insert into " + TABLE_NAME + " values('33333333','Surya Pratap', '3333','surya@gmail.com','100000','rajkot','7595645896')");
        db.execSQL("insert into " + TABLE_NAME + " values('44444444','Vikram Garasiya', '4444','vikram@gmail.com','25600','surat','9995640038')");
        db.execSQL("insert into " + TABLE_NAME + " values('55555555','Shivani Kumari', '5555','shivani@gmail.com','6200','delhi','9095648962')");
        db.execSQL("insert into " + TABLE_NAME + " values('66666666','Piyush Joshi','6666', 'piyush@gmail.com','500000','mahesana','8855640238')");
        db.execSQL("insert into " + TABLE_NAME + " values('77777777','ram soni', '1234', 'ram@gmail.com','15000','ahemdabad','9862356158')");
        db.execSQL("insert into " + TABLE_NAME + " values('88888888','akshar bhatt', '2222','akshar@gmail.com','19060','kutchh','7895674369')");
        db.execSQL("insert into " + TABLE_NAME + " values('99999999','rahul raj', '3333','rahul@gmail.com','18000','rajkot','8659452385')");
        db.execSQL("insert into " + TABLE_NAME + " values('11111111','shivangi rathod', '4444','shivangi@gmail.com','19500','navsari','9564872365')");
        db.execSQL("insert into " + TABLE_NAME + " values('12121212','kamal dev', '5555','kamal@gmail.com','150000','delhi','9153895794')");
        db.execSQL("insert into " + TABLE_NAME + " values('13425695','yash patel','6666', 'yash@gmail.com','60030','bihar','8859635975')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void updateAmount(String accountNo, int amount) {
        Log.d ("TAG", "update Amount");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + UserDBContract.UserEntry.TABLE_NAME + " set " + UserDBContract.UserEntry.BALANCE  + " = " + ""+amount + " where " +
                UserDBContract.UserEntry.ACC_NO + " = " + accountNo);
    }
}
