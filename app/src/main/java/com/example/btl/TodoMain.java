package com.example.btl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TodoMain extends AppCompatActivity {
    private TodoDatabaseHelper todoDatabaseHelper;
    private List<String> todoItems;
    private TodoListAdapter todoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);

        todoDatabaseHelper = new TodoDatabaseHelper(this);
        SQLiteDatabase db = todoDatabaseHelper.getWritableDatabase();

        // Call the method to create the "todos" table if it doesn't exist
        todoDatabaseHelper.createTodoTable(db);

        todoItems = new ArrayList<>();
        todoListAdapter = new TodoListAdapter();

        ListView todoListView = findViewById(R.id.todoListView);
        todoListView.setAdapter(todoListAdapter);

        // Call the method to load todo items
        loadTodoItems();

        Button addTodoButton = findViewById(R.id.addTodoButton);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText todoEditText = findViewById(R.id.todoEditText);
                String itemInformation = todoEditText.getText().toString().trim();

                if (!itemInformation.isEmpty()) {
                    addTodoItem(itemInformation);
                    todoEditText.setText(""); // Clear the input field
                } else {
                    Toast.makeText(TodoMain.this, "Please enter a todo item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addTodoItem(String itemInformation) {
        SQLiteDatabase db = todoDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TodoDatabaseHelper.COLUMN_TODO_ITEM_INFORMATION, itemInformation);

        long newRowId = db.insert(TodoDatabaseHelper.TABLE_TODO, null, values);

        if (newRowId != -1) {
            // Todo item inserted successfully
            Toast.makeText(this, "Todo item added successfully", Toast.LENGTH_SHORT).show();
            loadTodoItems(); // Refresh the todo items list
        } else {
            // Error inserting todo item
            Toast.makeText(this, "Failed to add todo item", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadTodoItems() {
        SQLiteDatabase db = todoDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TodoDatabaseHelper.TABLE_TODO, null, null, null, null, null, null);

        todoItems.clear();

        if (cursor.moveToFirst()) {
            int itemInformationIndex = cursor.getColumnIndex(TodoDatabaseHelper.COLUMN_TODO_ITEM_INFORMATION);

            do {
                String itemInformation = cursor.getString(itemInformationIndex);
                todoItems.add(itemInformation);
            } while (cursor.moveToNext());
        }

        // Close the cursor to release resources
        cursor.close();

        // Notify the adapter that the data has changed
        todoListAdapter.notifyDataSetChanged();
    }

    private class TodoListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return todoItems.size();
        }

        @Override
        public String getItem(int position) {
            return todoItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            String itemInformation = getItem(position);
            TextView itemTextView = convertView.findViewById(android.R.id.text1);
            itemTextView.setText(itemInformation);

            return convertView;
        }
    }
}
