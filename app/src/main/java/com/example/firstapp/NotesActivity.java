package com.example.firstapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {
    Database database;
    ListView listViewNotes;
    ArrayList<Notes> ArrayNotes;
    NoteAdapter adapter;
    public static final String TABLE_NAME = "NotesActivity";
    public static final String Database_Name="NotesActivity.sqlite";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        listViewNotes = (ListView) findViewById(R.id.listViewNotes);
        ArrayNotes = new ArrayList<>();
        adapter = new NoteAdapter(this,R.layout.activity_notes,ArrayNotes);
        listViewNotes.setAdapter(adapter);
        // database NotesActivity initialization
        database = new Database(this, Database_Name,null,1);

        // table creation
        database.QueryData("CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Name VARCHAR(200))");
        PopulateNoteData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_notes,menu);
        return super.onCreateOptionsMenu(menu);

    }
    private void PopulateNoteData(){
        ArrayNotes.clear();
        // iterate through the data in the created database
        Cursor NoteData = database.GetData("SELECT * FROM "+TABLE_NAME);
        while (NoteData.moveToNext()){
            String name = NoteData.getString(1);
            int id = NoteData.getInt(0);
            ArrayNotes.add(new Notes(id,name));
        }
        adapter.notifyDataSetChanged();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.MenuAdd){ // if item is the rightside plus
            AddDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    private void AddDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_notes);

        final EditText editText = dialog.findViewById(R.id.Addtext);
        Button Add = dialog.findViewById(R.id.Add);
        Button Delete=dialog.findViewById(R.id.Del);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if (name.equals("")){
                    editText.setError("This must be filled in !");
                }else{
                    database.QueryData("INSERT INTO "+TABLE_NAME
                    +" VALUES(null, '" +name + "')");
                    dialog.dismiss();// close the dialog;
                    Toast.makeText(NotesActivity.this,"Add!!",Toast.LENGTH_SHORT)
                    .show();
                    PopulateNoteData();
                }
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void DeleteDialog(final String name, final int id){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Are you sure to delete " + name +" ?");
        dialogDelete.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                database.QueryData("DELETE FROM "+TABLE_NAME +" WHERE id = '"+ id +"'");

                PopulateNoteData();
            }
        });
        dialogDelete.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialogDelete.show();
    }
}
