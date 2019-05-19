package com.example.firstapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {
    Database database;
    ListView listViewNotes;
    ArrayList<Notes> ArrayNotes;
    NoteAdapter adapter;
    public static final String TABLE_NAME = "NotesActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        listViewNotes = (ListView) findViewById(R.id.listViewNotes);
        ArrayNotes = new ArrayList<>();
        listViewNotes.setAdapter(adapter);
        // database NotesActivity initialization
        database = new Database(this, "NotesActivity.sqlite",null,1);

        // table creation
        database.QueryData("CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Name VARCHAR(200))");
        // insert data
        //database.QueryData("INSERT INFO "+TABLE_NAME
          //      + " VALUES(null,'Create android app')");

        // iterate through the data in the created database
        Cursor NoteData = database.GetData("SELECT * FROM "+TABLE_NAME);
        while (NoteData.moveToNext()){
            String name = NoteData.getString(1);
            int id = NoteData.getInt(0);
            ArrayNotes.add(new Notes(id,name));
        }
        adapter.notifyDataSetChanged();
    }
}
