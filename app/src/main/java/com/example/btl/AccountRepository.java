package com.example.btl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccountRepository {
    private DatabaseHelper dbHelper;

    public AccountRepository(Context context) {
        dbHelper = new TodoDatabaseHelper(context);
    }

    public boolean checkExistAccount(Account account) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = DatabaseHelper.COLUMN_ACCOUNT_EMAIL + " = ? AND " +
                DatabaseHelper.COLUMN_ACCOUNT_PASSWORD + " = ?";
        String[] selectionArgs = {account.getEmail(), account.getPassword()};

        Cursor cursor = db.query(DatabaseHelper.TABLE_ACCOUNT, null,
                selection, selectionArgs, null, null, null);

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ACCOUNT_EMAIL, account.getEmail());
        values.put(DatabaseHelper.COLUMN_ACCOUNT_PASSWORD, account.getPassword());

        db.insert(DatabaseHelper.TABLE_ACCOUNT, null, values);
        db.close();
    }
}
