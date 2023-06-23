package com.example.btl;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private Context context;
    private DatabaseHelper dbHelper;

    public AccountRepository(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public boolean checkExistAccount(Account account) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                DatabaseHelper.COLUMN_EMAIL
        };
        String selection = DatabaseHelper.COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {account.getEmail()};
        Cursor cursor = db.query(DatabaseHelper.TABLE_ACCOUNT, projection, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EMAIL, account.getEmail());
        values.put(DatabaseHelper.COLUMN_PASSWORD, account.getPassword());
        db.insert(DatabaseHelper.TABLE_ACCOUNT, null, values);
        Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myapp.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_ACCOUNT = "account";
        private static final String COLUMN_EMAIL = "email";
        private static final String COLUMN_PASSWORD = "password";

        private static final String CREATE_TABLE_ACCOUNT =
                "CREATE TABLE " + TABLE_ACCOUNT + "(" +
                        COLUMN_EMAIL + " TEXT PRIMARY KEY," +
                        COLUMN_PASSWORD + " TEXT" +
                        ")";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_ACCOUNT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
            onCreate(db);
        }
    }
}
