package com.example.btl.Todo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;

import java.util.List;

public class TodoMain extends AppCompatActivity {
    private TodoRepository todoRepository;
    private TodoListAdapter todoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);

        TodoDatabaseHelper databaseHelper = new TodoDatabaseHelper(this);
        todoRepository = new TodoRepository(databaseHelper);

        List<String> todoItems = todoRepository.getAllTodoItems();
        todoListAdapter = new TodoListAdapter(todoItems);

        ListView todoListView = findViewById(R.id.todoListView);
        todoListView.setAdapter(todoListAdapter);

        Button addTodoButton = findViewById(R.id.addTodoButton);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText todoEditText = findViewById(R.id.todoEditText);
                String itemInformation = todoEditText.getText().toString().trim();

                if (!itemInformation.isEmpty()) {
                    todoRepository.addTodoItem(itemInformation);
                    todoEditText.setText("");
                    refreshTodoList();
                } else {
                    Toast.makeText(TodoMain.this, "Please enter a todo item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void refreshTodoList() {
        List<String> todoItems = todoRepository.getAllTodoItems();
        todoListAdapter = new TodoListAdapter(todoItems);
        ListView todoListView = findViewById(R.id.todoListView);
        todoListView.setAdapter(todoListAdapter);
    }
}
