package com.example.corepixmobileapp.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.corepixmobileapp.Constants.Constants;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FileList.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FILE_TABLE = "CREATE TABLE " +
                Constants.FileEntry.TABLE_NAME + " (" +
                Constants.FileEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.FileEntry.COLUMN_NAME + " TEXT NOT NULL," +
                Constants.FileEntry.COLUMN_DATA + " TEXT NOT NULL" +");";

        db.execSQL(SQL_CREATE_FILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.FileEntry.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.FileEntry.COLUMN_NAME,name);
        contentValues.put(Constants.FileEntry.COLUMN_DATA,data);
        long result = db.insert(Constants.FileEntry.TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c = sd.rawQuery("select * from "+Constants.FileEntry.TABLE_NAME,null);
        return c;
    }
}
