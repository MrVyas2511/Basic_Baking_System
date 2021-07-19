package com.example.tsfbank;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tsfbank.Adapter.SentoUserAdapter;
import com.example.tsfbank.Adapter.UserListAdapter;
import com.example.tsfbank.Data.UserData;
import com.example.tsfbank.databases.TransactionDBContract;
import com.example.tsfbank.databases.TransactionDBHelper;
import com.example.tsfbank.databases.UserDBContract;
import com.example.tsfbank.databases.UserDBHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SendToUser extends AppCompatActivity {
    ArrayList<UserData> userArray;
    SentoUserAdapter adapter;
    ListView listView;
    private UserDBHelper dbHelper;
    String fromUserAccountName;
    String fromUserAccountBalance;
    String toUserAccountBalance;
    String transferAmount;
    String toUserAccountName;
    String fromUserAccountNo;
    String toUserAccountNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_user);

        dbHelper  = new UserDBHelper(this);
        userArray = new ArrayList<UserData>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromUserAccountName = bundle.getString("FROM_USER_NAME");
            fromUserAccountNo = bundle.getString("FROM_USER_ACCOUNT_NO");
            fromUserAccountBalance = bundle.getString("FROM_USER_ACCOUNT_BALANCE");
            transferAmount = bundle.getString("TRANSFER_AMOUNT");
        }

        databaseinfo();

        listView = findViewById(R.id.send_to_list);
        adapter = new SentoUserAdapter(SendToUser.this, userArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                toUserAccountNo = userArray.get(position).getAcc_No();
                toUserAccountName = userArray.get(position).getName();
                toUserAccountBalance = userArray.get(position).getBalance();

                AlertDialog.Builder builder_exitButton = new AlertDialog.Builder(SendToUser.this);
                builder_exitButton.setTitle("Do you want to do transaction?").setCancelable(false)
                        .setPositiveButton ("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface dialogInterface, int i) {

                                calculateAmount();

                                LocalDateTime myDateObj = LocalDateTime.now();
                                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                String formattedDate = myDateObj.format(myFormatObj);
                                new TransactionDBHelper(SendToUser.this).insertTransaction(fromUserAccountName, toUserAccountName, transferAmount, formattedDate,1);

                                Toast.makeText(SendToUser.this, "Transaction Successful!!", Toast.LENGTH_LONG).show();

                                Intent intent  = new Intent(SendToUser.this, TranactionDone.class);
                                intent.putExtra("toUser",toUserAccountName);
                                intent.putExtra("fromUser",fromUserAccountName);
                                intent.putExtra("Amount",transferAmount);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("No", null);
                AlertDialog alertExit = builder_exitButton.create();
                alertExit.show();


            }
        });

    }


    private void calculateAmount() {
        Integer currentAmount = Integer.parseInt(fromUserAccountBalance);
       Integer transferAmountInt = Integer.parseInt(transferAmount);

        Integer remainingAmount = currentAmount - transferAmountInt;
        Integer increasedAmount = transferAmountInt + Integer.parseInt(toUserAccountBalance);
        // Update amount in the dataBase
        new UserDBHelper(this).updateAmount(fromUserAccountNo, remainingAmount);
        new UserDBHelper(this).updateAmount(toUserAccountNo, increasedAmount);
    }

    private void databaseinfo() {

        userArray.clear();

        SQLiteDatabase mDb = dbHelper.getReadableDatabase();

        String[] projection  = {
                UserDBContract.UserEntry.ACC_NO,
                UserDBContract.UserEntry.Name,
                UserDBContract.UserEntry.IFSC_NO,
                UserDBContract.UserEntry.EMAIL,
                UserDBContract.UserEntry.BALANCE,
                UserDBContract.UserEntry.CITY ,
                UserDBContract.UserEntry.MO_NUMBER};

        Cursor cursor = mDb.query(UserDBContract.UserEntry.TABLE_NAME,projection,null,null,null,null,null);

        int moCoulumnIndex = cursor.getColumnIndex(UserDBContract.UserEntry.MO_NUMBER);
        int cityColumnIndex = cursor.getColumnIndex(UserDBContract.UserEntry.CITY);
        int EmailColumnIndex = cursor.getColumnIndex(UserDBContract.UserEntry.EMAIL);
        int ifscColumnIndex = cursor.getColumnIndex(UserDBContract.UserEntry.IFSC_NO);
        int nameColumnIndex = cursor.getColumnIndex(UserDBContract.UserEntry.Name);
        int accnoColumnIndex = cursor.getColumnIndex(UserDBContract.UserEntry.ACC_NO);
        int balanceColumnIndex = cursor.getColumnIndex(UserDBContract.UserEntry.BALANCE);


        while (cursor.moveToNext()){

            String name = cursor.getString(nameColumnIndex);
            String accNo = cursor.getString(accnoColumnIndex);
            String balance = cursor.getString(balanceColumnIndex);
            String Ifsc = cursor.getString(ifscColumnIndex);
            String city = cursor.getString(cityColumnIndex);
            String email = cursor.getString(EmailColumnIndex);
            String mobile = cursor.getString(moCoulumnIndex);

            if(!fromUserAccountNo.equals(accNo)) {
                userArray.add(new UserData(name, accNo, Ifsc, email, city, balance, mobile));
                Log.v("count1", String.valueOf(userArray.size()));
            }

        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder_exitButton = new AlertDialog.Builder(SendToUser.this);
        builder_exitButton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton ("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialogInterface, int i) {
                        // Transactions Cancelled
                        TransactionDBHelper dbHelper = new TransactionDBHelper(SendToUser.this);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        //Date
                        LocalDateTime myDateObj = LocalDateTime.now();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formattedDate = myDateObj.format(myFormatObj);

                        values.put(TransactionDBContract.transactionEntry.FROM_NAME, fromUserAccountName);
                        values.put(TransactionDBContract.transactionEntry.TO_NAME, "Transaction Cancelled!");
                        values.put(TransactionDBContract.transactionEntry.STATUS, 0);
                        values.put(TransactionDBContract.transactionEntry.AMOUNT, transferAmount);
                        values.put(TransactionDBContract.transactionEntry.TIME,formattedDate);

                        db.insert(TransactionDBContract.transactionEntry.TABLE_NAME, null, values);

                        Toast.makeText(SendToUser.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SendToUser.this, MainActivity.class));
                        finish();
                    }
                }).setNegativeButton("No", null);
        AlertDialog alertExit = builder_exitButton.create();
        alertExit.show();
    }
}

