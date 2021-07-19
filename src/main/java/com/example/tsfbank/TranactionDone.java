package com.example.tsfbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TranactionDone extends AppCompatActivity {


    String fromUserAccountName,toUserAccountName,transferAmount;
    TextView toUser,fromUser,Amount;
    Button home,history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranaction_done);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromUserAccountName = bundle.getString("fromUser");
            toUserAccountName = bundle.getString("toUser");
            transferAmount = bundle.getString("Amount");
        }

        toUser = findViewById(R.id.doneToUser);
        fromUser = findViewById(R.id.doneFromUser);
        Amount = findViewById(R.id.DoneAmount);

        toUser.setText(toUserAccountName);
        fromUser.setText(fromUserAccountName);
        Amount.setText(transferAmount+"  Rs.");
        home = findViewById(R.id.gotohome);
        history = findViewById(R.id.gotoTransfer);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TranactionDone.this,MainActivity.class));
                finish();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TranactionDone.this,TransactionHistory.class));
                finish();
            }
        });
    }
}