package com.andrezin.univeritycollege.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConnectionFactory extends SQLiteOpenHelper {

    private static final String NAME = "user.db";
    private static final int VERSION = 1;


    public static final String TABLE_NAME = "aluno";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nome";
    public static final String COLUMN_PASSWORD = "senha";



    public ConnectionFactory(@Nullable Context context){
        super(context, NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR(50), " +
                COLUMN_PASSWORD + " VARCHAR(50));";

        db.execSQL(createTableSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS usuario";
        db.execSQL(sql);
        onCreate(db);
    }
}
