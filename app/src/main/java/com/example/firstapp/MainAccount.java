package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class MainAccount extends AppCompatActivity {
    private String p,cp;
    private Button learning, tutoring, account, calendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_account);

        learning = (Button)findViewById(R.id.Learning);
        tutoring=(Button) findViewById(R.id.Tutoring);
        account = (Button)findViewById(R.id.Account) ;
        calendar=(Button)findViewById(R.id.calendar);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(MainAccount.this, AccountManagement.class);
                startActivity(change);
            }


        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(MainAccount.this, CalenderHelper.class);
                startActivity(change);
            }
        });
    }
}
