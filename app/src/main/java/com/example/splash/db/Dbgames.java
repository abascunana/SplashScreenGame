package com.example.splash.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Dbgames extends DbHelper{
    //Manejp de base de datos
    Context mContext;
    public Dbgames(@Nullable Context context) {
        super(context);
        this.mContext=context;
    }


    public long insertaRecordLt(boolean win, int moves){
        long id = 0;
        try {
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("wins",win);
        values.put("moves",moves);
        id = db.insert(LTABLE, null, values);
        }
       catch (Exception e){
           Log.println(Log.ASSERT,"error","no se ha podido conectar a la tabla");
       }

        return id;
    }

    public long insertaRecordTf(boolean win, int moves){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(mContext);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values= new ContentValues();
            values.put("wins",win);
            values.put("moves",moves);
            id = db.insert(TWOTABLE, null, values);
        }
        catch (Exception e){
            Log.println(Log.ASSERT,"error","no se ha podido conectar a la tabla");
        }

        return id;
    }

    //TODO Devolver lista
    public List<String> getAllLabels(){
        String selectQuery = "SELECT * FROM " + LTABLE;
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        try {


            // Select All Query


            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(0));//adding 0nd column data
                    list.add(cursor.getString(1));//adding 1nd column data
                    list.add(cursor.getString(2));//adding 2nd column data
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

    @SuppressLint("Range")
    public String SelectRecord(String CommentName){
        String comment="";
        try {
            DbHelper dbHelper = new DbHelper(mContext);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            try {
                Cursor c = db.rawQuery("SELECT * FROM comments WHERE nombre = '"+CommentName.trim()+"'", null);
                c.moveToNext();
                comment = c.getString(c.getColumnIndex("comment"));
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        catch (Exception e){
            Log.println(Log.ASSERT,"error","no se ha podido conectar a a la tabla");
        }
        System.out.println(comment);
    return comment;

    }
}
