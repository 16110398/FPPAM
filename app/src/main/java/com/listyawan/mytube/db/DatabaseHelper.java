package com.listyawan.mytube.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 09-Jan-19.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "favorite";
    private static final String COL1= "ID";
    private static final String COL2= "title";

    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL2 +" TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXIST "+ TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String item){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,item);

        Log.d(TAG, "addData : Adding " + item + " to "+ TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //jika data insert salah maka
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getItemID(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL1 +" FROM "+ TABLE_NAME +" WHERE "+ COL2 +" = '"+ title +"'";

        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public void updateTitle(String newTitle, int id, String oldTitle){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+ TABLE_NAME +" SET "+ COL2 + " = '"+ newTitle +"'WHERE "+ COL1 +" = '"+ id +"' AND "+ COL2 +" ='"
                + oldTitle +"'";
        db.execSQL(query);
    }

    public void deleteTitle(int id, String title){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME +" WHERE "+ COL1 +" = '"+ id +"' AND "+ COL2 +" = '"+ title +"'";
        db.execSQL(query);

    }
}

