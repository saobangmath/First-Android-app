package com.example.firstapp;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity  extends AppCompatActivity {

    private static final String TAG="SignUpActivity";
    public DatabaseHelper myDB;
    private Button register;
    private EditText et_name, et_email,et_phone,et_pass,et_cpass ;
    private String name, email,phone,pass,cpass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        myDB = new DatabaseHelper(this);
        register = (Button)findViewById(R.id.register);
        et_name=(EditText)findViewById(R.id.name);
        et_email=(EditText)findViewById(R.id.email);
        et_phone=(EditText)findViewById(R.id.phone);
        et_pass =(EditText)findViewById(R.id.pass);
        et_cpass= (EditText)findViewById(R.id.cpass);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });
    }
    private void Signup() {
        initialize();
        boolean valid = true;
        if (pass.isEmpty()){
            et_pass.setError("The password must not be left!");
            valid = false;
        }
        if (cpass.isEmpty()){
            et_cpass.setError("The password must be verified!");
            valid = false;
        }
        if (name.isEmpty()){
            et_name.setError("The name must not be left!");
            valid = false;
        }
        if (phone.isEmpty()){
            et_phone.setError("The phone number must be filled in!");
            valid = false;
        }
        if (email.isEmpty()) {
            et_email.setError("The email must not be left");
            valid = false;
        }
        if (!pass.equals(cpass) ) {
            Toast.makeText(this, "Password must be consistent!", Toast.LENGTH_SHORT)
                    .show();
            valid = false;
        }
        if (valid){
            Toast.makeText(this,"The account is set up correctly!",Toast.LENGTH_SHORT)
            .show();
            Intent change = new Intent(SignUpActivity.this, congratulationActivity.class);
            startActivity(change);
        }
        else{
            Toast.makeText(this,"Your account could not set up properly!",Toast.LENGTH_SHORT);
            reset();
        }

    }

    private boolean validate() {

        boolean isinserted = myDB.addData(name, email, phone, pass, cpass);
        return (isinserted);

    }


    private void initialize() {
        name = et_name.getText().toString().trim();
        email = et_email.getText().toString().trim();
        phone = et_phone.getText().toString().trim();
        pass = et_pass.getText().toString().trim();
        cpass = et_cpass.getText().toString().trim();
    }
    private void reset(){
        et_name.setText("");
        et_pass.setText("");
        et_email.setText("");
        et_cpass.setText("");
        et_phone.setText("");
    }
}
