package com.example.btl.Todo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private TodoDatabaseHelper databaseHelper;

    public TodoRepository(TodoDatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void addTodoItem(String itemInformation) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TodoDatabaseHelper.COLUMN_TODO_ITEM_INFORMATION, itemInformation);

        long newRowId = db.insert(TodoDatabaseHelper.TABLE_TODO, null, values);

        if (newRowId != -1) {
            // Todo item inserted successfully
            Toast.makeText(databaseHelper.getContext(), "Todo item added successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Error inserting todo item
            Toast.makeText(databaseHelper.getContext(), "Failed to add todo item", Toast.LENGTH_SHORT).show();
        }
    }

    public List<String> getAllTodoItems() {
        List<String> todoItems = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TodoDatabaseHelper.TABLE_TODO, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int itemInformationIndex = cursor.getColumnIndex(TodoDatabaseHelper.COLUMN_TODO_ITEM_INFORMATION);

            do {
                String itemInformation = cursor.getString(itemInformationIndex);
                todoItems.add(itemInformation);
            } while (cursor.moveToNext());
        }

        // Close the cursor to release resources
        cursor.close();

        return todoItems;
    }
}
