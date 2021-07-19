package com.example.tsfbank;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tsfbank.Adapter.UserListAdapter;
import com.example.tsfbank.Data.UserData;
import com.example.tsfbank.databases.UserDBContract;
import com.example.tsfbank.databases.UserDBHelper;

import java.util.ArrayList;

public class User_Activity extends AppCompatActivity {

    ArrayList<UserData> userArray;
    UserListAdapter adapter;
    ListView listView;
    private UserDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);
        dbHelper  = new UserDBHelper(this);
        userArray = new ArrayList<UserData>();

        databaseinfo();
        listView = findViewById(R.id.userlist);
        adapter = new UserListAdapter(User_Activity.this, userArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(User_Activity.this,UserProfile.class);
                intent.putExtra("City",userArray.get(position).getCity());
                intent.putExtra("ACCOUNT_NO", userArray.get(position).getAcc_No());
                intent.putExtra("NAME", userArray.get(position).getName());
                intent.putExtra("EMAIL", userArray.get(position).getEmail());
                intent.putExtra("IFSC_CODE", userArray.get(position).getIfsc());
                intent.putExtra("PHONE_NO", userArray.get(position).getM_no());
                intent.putExtra("BALANCE", userArray.get(position).getBalance());
                startActivity(intent);
               // Toast.makeText(User_Activity.this, userArray.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

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

            userArray.add(new UserData(name, accNo, Ifsc, email, city, balance, mobile));
            Log.v("count", String.valueOf(userArray.size()));

        }

    }
}

