package com.example.splash.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper  extends SQLiteOpenHelper {
    public static String DATABASENAME = "androidadvancesqlite";
    public final static String LTABLE = "lightsout";
    public final static String TWOTABLE = "twofoureight";
    //Creación de base de datos
    Context c;



    public DbHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
        c = context;
    }
    public List<String> SelectLt(){
        String selectQuery = "SELECT * FROM " + LTABLE;
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        try {


            // Select All Query


            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    list.add("id:"+cursor.getString(0));//adding id
                    list.add("win:"+cursor.getString(1));//adding 1nd column data
                    list.add("moves:"+cursor.getString(2));//adding 2nd column data
                } while (cursor.moveToNext());
            }
        }
        catch (Exception e){

        }

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }
    public List<String> SelectTf(){
        String selectQuery = "SELECT * FROM " + TWOTABLE;
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        try {
            // Select All Query
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    list.add("id:"+cursor.getString(0));//adding id
                    list.add("win:"+cursor.getString(1));//adding 1nd column data
                    list.add("moves:"+cursor.getString(2));//adding 2nd column data
                } while (cursor.moveToNext());
            }
        }
        catch (Exception e){

        }

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {


            db.execSQL("CREATE TABLE " + LTABLE+ "("+  "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "wins BOOLEAN NOT NULL, " +
                    "moves INTEGER NOT NULL )")  ;
            db.execSQL("CREATE TABLE " + TWOTABLE+ "("+  "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "wins BOOLEAN NOT NULL, " +
                    "  moves INTEGER NOT NULL )");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void deleteAll(){
        getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + LTABLE);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TWOTABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LTABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TWOTABLE);
        onCreate(db);
    }



}

