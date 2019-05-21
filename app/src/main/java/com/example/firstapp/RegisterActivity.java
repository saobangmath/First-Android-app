package com.example.firstapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name, et_email, et_phone, et_pass, et_cpass;
    private String name, email, phone, pass, cpass;
    Button logbut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_name = (EditText) findViewById(R.id.name);
        et_email=(EditText) findViewById(R.id.email);
        et_phone=(EditText)findViewById(R.id.phone);
        et_pass =(EditText)findViewById(R.id.pass);
        et_cpass=(EditText)findViewById(R.id.cpass);
        logbut = (Button) findViewById(R.id.register);
        logbut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void login() {
        initialize();
        if (!validate()){
            Toast.makeText(this, "FAIL!!!", Toast.LENGTH_SHORT).show();
        }
        else{
            onSignupSuccess();
        }
    }
    public void onSignupSuccess(){
        // do after the valid input
        Toast.makeText(this, "YEPP", Toast.LENGTH_SHORT).show();
        Intent changetomain = new Intent(RegisterActivity.this, MainAccount.class) ;
        startActivity(changetomain);
    }

    public boolean validate(){
        boolean valid = true;
        if (name.isEmpty() || name.length()>32){
            et_name.setError("Please select valid name ");
            valid = false;
        }
        if (email.isEmpty()){
            et_email.setError("Please select valid email");
            valid = false;
        }
        if (phone.isEmpty()){
            et_phone.setError("Please select valid email");
            valid = false;
        }
        if (pass.isEmpty()){
            et_pass.setError("Please select valid email");
            valid = false;
        }
        else{ // basic requirement is satisfied then..

//
//            }
        }
        if (cpass.isEmpty() || !cpass.equals(pass)){
            et_cpass.setError("The password must be similar!");
            valid = false;
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register:


        }
    }

    private void initialize(){
        name = et_name.getText().toString().trim();
        email = et_email.getText().toString().trim();
        phone = et_phone.getText().toString().trim();
        pass = et_pass.getText().toString().trim();
        cpass = et_cpass.getText().toString().trim();
    }
}
