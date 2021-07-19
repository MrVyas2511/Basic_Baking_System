package com.example.tsfbank.databases;

import android.provider.BaseColumns;

public class TransactionDBContract  {
    public TransactionDBContract() { }

    public static final class transactionEntry implements BaseColumns {
        public final static String TABLE_NAME = "Transaction_table";

        public final static String _ID = BaseColumns._ID;
        public final static String FROM_NAME = "from_name";
        public final static String TO_NAME = "to_name";
        public final static String AMOUNT = "amount";
        public final static String TIME = "time";
        public final static String STATUS = "Status";

    }
}
