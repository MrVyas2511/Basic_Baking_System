package com.example.tsfbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tsfbank.Data.Transaction;

public class MainActivity extends AppCompatActivity {

    private Button userbtn;
    private Button TransactionHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         userbtn = findViewById(R.id.userbtn);

         userbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(MainActivity.this,User_Activity.class);
                 startActivity(i);
             }
         });

         TransactionHistory = findViewById(R.id.TransferBtn);

         TransactionHistory.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(MainActivity.this, TransactionHistory.class);
                 startActivity(i);
             }
         });



    }
}