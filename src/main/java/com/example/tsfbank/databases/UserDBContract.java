package com.example.tsfbank.databases;

import android.provider.BaseColumns;

public class UserDBContract {
    public UserDBContract() {
    }
    public static final class UserEntry implements BaseColumns {

        //table name
        public final static  String TABLE_NAME = "users";

        //table column
        public final static String _ID = BaseColumns._ID;
        public final static String Name = "Name";
        public final static String ACC_NO = "AccountNumber";
        public final static String IFSC_NO = "IFSCNO";
        public final static String EMAIL = "Email";
        public final static String CITY = "City";
        public final static String BALANCE = "Balance";
        public final static String MO_NUMBER = "Mobile";

    }
}
