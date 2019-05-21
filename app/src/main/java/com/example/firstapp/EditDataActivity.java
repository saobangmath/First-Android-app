package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {
        private static final String TAG = "EditDataActiviy";
        private Button SaveBtn, DelBtn, View,Clear;
        private EditText editText;
        DatabaseHelper Helper;

        private String selectedName;
        private int selectedID;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.edit_data_layout);
            SaveBtn = (Button)findViewById(R.id.Save);
            DelBtn = (Button)findViewById(R.id.Del);
            View =(Button)findViewById(R.id.View);
            Clear = (Button)findViewById(R.id.Clear);
            editText = (EditText)findViewById(R.id.edittext);
            Helper = new DatabaseHelper(this);
            Intent receivedIntent = getIntent();
            selectedID = receivedIntent.getIntExtra("id",-1);
            selectedName = receivedIntent.getStringExtra("name");
            SaveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item = editText.getText().toString();
                    if (!item.equals("")){
                        Helper.UpdateName(item, selectedID,selectedName);
                        toastMessage(selectedName +"has update to " + "student with student id = "+selectedID);
                    }
                    else{
                        toastMessage("You must enter a name");
                    }
                }


            });

            DelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.deleteName(selectedID,selectedName);
                    editText.setText("");
                    toastMessage("received from database!");
                }
            });
            View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EditDataActivity.this, ListDataActivity.class);
                    startActivity(intent);
                }
            });
            Clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {

                }
            });
        }
    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
