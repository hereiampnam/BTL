package com.example.btl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo_app.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_TODO = "todos";
    public static final String COLUMN_TODO_ID = "id";
    public static final String COLUMN_TODO_CONTENT = "content";
    public static final String COLUMN_TODO_PRIORITY = "priority";
    public static final String COLUMN_TODO_TIME = "time";

    public TodoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTodoTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTodoTable(db);
        onCreate(db);
    }

    private void createTodoTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_TODO + " (" +
                COLUMN_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TODO_CONTENT + " TEXT, " +
                COLUMN_TODO_PRIORITY + " TEXT, " +
                COLUMN_TODO_TIME + " TEXT)";
        db.execSQL(createTableQuery);
    }

    private void dropTodoTable(SQLiteDatabase db) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_TODO;
        db.execSQL(dropTableQuery);
    }
}
