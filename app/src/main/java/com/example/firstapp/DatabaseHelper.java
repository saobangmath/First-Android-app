package com.example.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG="DatabaseHelper";
    private static final String TABLE_NAME = "user_details";
    private static final String COL0 = "ID";
    private static final String COL1 = "name";
    private static final String COL2 = "email";
    private static final String COL3 = "phone";
    private static final String COL4 = "pass";
    private static final String COL5 = "cpass";

    public DatabaseHelper(Context context){
        super (context,TABLE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " +TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT)";
        db.execSQL(createTable);
    }
    public Cursor getItemByPass(String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+"'" +
                "WHERE "+COL4 +"= '" + pass +"'";
        Cursor data =  db.rawQuery(query,null);
        return data;
    }
    public boolean addData(String name, String email, String phone, String pass, String cpass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor check = getItemByPass(pass);
        if (check == null){
            return false;
        }else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL1, name);
            contentValues.put(COL2, email);
            contentValues.put(COL3, phone);
            contentValues.put(COL4, pass);
            contentValues.put(COL5, cpass);
            long res = db.insert(TABLE_NAME, null, contentValues);
            return (res != -1);
        }
    }
    public Cursor getData(){
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+"'" +
                "WHERE "+COL1 +"= '" +name +"'";
        Cursor data =  db.rawQuery(query,null);
        return data;
    }
    public void UpdateName(String newName, int id,String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE "+TABLE_NAME+" SET "+ COL1+
                "' = '"+newName + "' WHERE " +COL0 +" = '"+id+"'"+
                " AND "+COL1 +" = '"+oldName + "'";

        Log.d(TAG,"updateName: query: "+ query);
        Log.d(TAG, "updateName: Setting name to "+newName);
        db.execSQL(query);
    }
    public void deleteName(int id,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+TABLE_NAME + " WHERE "
                + COL1 + "' = '" + id + "'" +
                " AND "+COL2 + "= '" +name +"'";
        Log.d(TAG,"deleteName: query: "+ query);
        Log.d(TAG, "deleteName: Deleting "+name +" from database.");
        db.execSQL(query);
    }
}
