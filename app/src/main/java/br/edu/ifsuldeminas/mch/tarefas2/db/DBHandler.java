package br.edu.ifsuldeminas.mch.tarefas2.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tarefas.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CATEGORIES =
            " CREATE TABLE IF NOT EXISTS categories ( " +
            " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            " name TEXT NOT NULL " +
            " ); ";

    private static final String TABLE_TASKS =
            " CREATE TABLE IF NOT EXISTS tasks ( " +
            " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            " description TEXT NOT NULL, " +
            " active VARCHAR(1) NOT NULL, " +
            " category_id INTEGER, " +
            " FOREIGN KEY (category_id) " +
            " REFERENCES categories(id) " +
            " ); ";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CATEGORIES);
        sqLiteDatabase.execSQL(TABLE_TASKS);

        String[] categories_inserir ={""};

        for (int i = 0; i<categories_inserir.length; i++){
            ContentValues categories2 = new ContentValues();
            categories2.put("categories2", categories_inserir[i]);
            sqLiteDatabase.insert("categories_spinner", null, categories2);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // não vamos tratar alterações
    }

}

