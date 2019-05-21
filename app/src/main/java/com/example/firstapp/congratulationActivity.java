package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class congratulationActivity extends AppCompatActivity {
    private Button account;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congratulation_activity);

        account = (Button)findViewById(R.id.mainaccount);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(congratulationActivity.this, MainAccount.class);
                startActivity(intent);
            }
        });
    }
}
