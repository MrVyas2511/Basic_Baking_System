package com.example.tsfbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {
    private TextView name, email, accountNo, balance, ifscCode, phoneNo,city;
    private Button transferMoney;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        Intent intent = getIntent();
        Bundle extras =  intent.getExtras();
        Log.v("intent",extras.getString("City"));


        name = findViewById(R.id.Name);
        email = findViewById(R.id.email);
        accountNo = findViewById(R.id.acc_No);
        balance = findViewById(R.id.balance);
        ifscCode = findViewById(R.id.ifsc);
        phoneNo = findViewById(R.id.moNo);
        city = findViewById(R.id.city);
       if (extras != null) {

            name.setText(extras.getString("NAME"));
            accountNo.setText(extras.getString("ACCOUNT_NO"));
            email.setText(extras.getString("EMAIL"));
            phoneNo.setText(extras.getString("PHONE_NO"));
            ifscCode.setText(extras.getString("IFSC_CODE"));
            balance.setText(extras.getString("BALANCE"));
            city.setText(extras.getString("City"));
         }

       transferMoney = findViewById(R.id.Transfer);

       transferMoney.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {



               enterAmount();
           }
       });
    }


    private void enterAmount() {

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(UserProfile.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_box, null);
        mBuilder.setTitle("").setView(mView).setCancelable(false);

        final EditText mAmount = (EditText) mView.findViewById(R.id.enter_money);

        mBuilder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              transactionCancel();
            }
        });

        dialog = mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking whether amount entered is correct or not
                int currentBalance = Integer.parseInt(String.valueOf(balance.getText()));

                if (mAmount.getText().toString().isEmpty()) {
                    mAmount.setError("Amount can't be empty");
                } else if (Integer.parseInt(mAmount.getText().toString()) > currentBalance){
                    mAmount.setError("Your account don't have enough balance");
                }
                else{
                   Intent intent = new Intent(UserProfile.this, SendToUser.class);
                    intent.putExtra("FROM_USER_ACCOUNT_NO", accountNo.getText());    // PRIMARY_KEY
                    intent.putExtra("FROM_USER_NAME", name.getText());
                    intent.putExtra("FROM_USER_ACCOUNT_BALANCE", balance.getText());
                    String amount = String.valueOf(mAmount.getText());
                    intent.putExtra("TRANSFER_AMOUNT",amount);

                    startActivity(intent);
                    finish();

                }
            }
        });
    }
    private void transactionCancel() {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(UserProfile.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UserProfile.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                enterAmount();
            }
        });
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();
    }
}