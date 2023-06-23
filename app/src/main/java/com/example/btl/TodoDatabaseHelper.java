package com.example.btl;

import android.content.Context;

public class TodoDatabaseHelper extends DatabaseHelper {
    private static final String DATABASE_NAME = "todo_app.db";
    private static final int DATABASE_VERSION = 1;

    public TodoDatabaseHelper(Context context) {
        super(context);
    }

    // You can add specific methods for handling todo-related database operations if needed
}
