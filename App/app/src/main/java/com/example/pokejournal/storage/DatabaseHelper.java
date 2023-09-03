package com.example.pokejournal.storage;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DatabaseName = "Cadastro.db";

    public DatabaseHelper(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table tbUsers(username TEXT primary Key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists tbUsers");
    }
    public Boolean insertDados(String username, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("tbUsers", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }
    public Boolean checkDados (String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from tbUsers where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}