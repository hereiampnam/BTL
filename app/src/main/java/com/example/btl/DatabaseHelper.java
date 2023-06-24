package com.example.btl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo_app.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ACCOUNT = "accounts";
    public static final String COLUMN_ACCOUNT_ID = "id";
    public static final String COLUMN_ACCOUNT_EMAIL = "email";
    public static final String COLUMN_ACCOUNT_PASSWORD = "password";

    public static final String TABLE_TODO = "todos";
    public static final String COLUMN_TODO_ID = "id";
    public static final String COLUMN_TODO_ITEM_INFORMATION = "item_information";
    public static final String COLUMN_TODO_SHORT_DESCRIPTION = "short_description";
    public static final String COLUMN_TODO_TIME_MANAGEMENT = "time_management";
    public static final String COLUMN_TODO_PRIORITY = "priority";
    public static final String COLUMN_TODO_PERSON_RESPONSIBLE = "person_responsible";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAccountTable(db);
        createTodoTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAccountTable(db);
        dropTodoTable(db);
        onCreate(db);
    }

    private void createAccountTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_ACCOUNT + " (" +
                COLUMN_ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ACCOUNT_EMAIL + " TEXT, " +
                COLUMN_ACCOUNT_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);
    }

    private void dropAccountTable(SQLiteDatabase db) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_ACCOUNT;
        db.execSQL(dropTableQuery);
    }

    private void createTodoTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_TODO + " (" +
                COLUMN_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TODO_ITEM_INFORMATION + " TEXT, " +
                COLUMN_TODO_SHORT_DESCRIPTION + " TEXT, " +
                COLUMN_TODO_TIME_MANAGEMENT + " TEXT, " +
                COLUMN_TODO_PRIORITY + " TEXT, " +
                COLUMN_TODO_PERSON_RESPONSIBLE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    private void dropTodoTable(SQLiteDatabase db) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_TODO;
        db.execSQL(dropTableQuery);
    }
}
