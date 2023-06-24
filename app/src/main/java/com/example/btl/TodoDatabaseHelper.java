package com.example.btl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo_database";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_TODO = "todos";
    public static final String COLUMN_TODO_ITEM_INFORMATION = "item_information";

    private static final String CREATE_TABLE_TODO = "CREATE TABLE IF NOT EXISTS " + TABLE_TODO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_TODO_ITEM_INFORMATION + " TEXT)";

    public TodoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    public void createTodoTable(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
    }
}
