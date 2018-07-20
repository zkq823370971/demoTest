package com.example.a.demotest.bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class test {
    private MyDatabaseHelper dbHelper;
    public Cursor  crea(Context context){
        dbHelper = new MyDatabaseHelper(context, "BookStore.db", null, 4);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        return  cursor;
    }

}
