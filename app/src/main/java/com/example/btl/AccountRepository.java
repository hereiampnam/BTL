package com.example.btl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccountRepository {
    private AccountDatabaseHelper dbHelper;

    public AccountRepository(Context context) {
        dbHelper = new AccountDatabaseHelper(context);
    }

    public void addAccount(String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AccountDatabaseHelper.COLUMN_ACCOUNT_EMAIL, email);
        values.put(AccountDatabaseHelper.COLUMN_ACCOUNT_PASSWORD, password);
        db.insert(AccountDatabaseHelper.TABLE_ACCOUNT, null, values);
        db.close();
    }

    public boolean checkExistAccount(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {AccountDatabaseHelper.COLUMN_ACCOUNT_EMAIL};
        String selection = AccountDatabaseHelper.COLUMN_ACCOUNT_EMAIL + "=?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(AccountDatabaseHelper.TABLE_ACCOUNT, columns, selection, selectionArgs, null, null, null);
        boolean exists = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return exists;
    }
}
