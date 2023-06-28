package com.example.btl.Account;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "2.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ACCOUNT = "accounts";
    public static final String COLUMN_ACCOUNT_ID = "id";
    public static final String COLUMN_ACCOUNT_EMAIL = "email";
    public static final String COLUMN_ACCOUNT_PASSWORD = "password";

    public AccountDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAccountTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAccountTable(db);
        onCreate(db);
    }

    private void createAccountTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_ACCOUNT + " (" +
                COLUMN_ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ACCOUNT_EMAIL + " TEXT, " +
                COLUMN_ACCOUNT_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);
    }

    private void dropAccountTable(SQLiteDatabase db) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_ACCOUNT;
        db.execSQL(dropTableQuery);
    }
}
