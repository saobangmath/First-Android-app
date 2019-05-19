package com.example.firstapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountManagement extends AppCompatActivity {
    private EditText pass ,cpass;
    private String pw, cpw;
    private Button change;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_management_activity);

        pass = (EditText)findViewById(R.id.newpass);
        cpass = (EditText)findViewById(R.id.cnewpass);
        pw = pass.getText().toString().trim();
        cpw=cpass.getText().toString().trim();
        change = (Button)findViewById(R.id.changeable);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changable();
            }
        });
    }
    private void changable() {
        if (!pw.equals(cpw)){
            Toast.makeText(this, "The password is not consistent!",Toast.LENGTH_SHORT)
            .show();
        }
        else{
            Toast.makeText(this, "The password is successfully updated!",Toast.LENGTH_SHORT)
            .show();
        }
    }
}
