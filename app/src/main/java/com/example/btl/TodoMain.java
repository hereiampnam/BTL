package com.example.btl;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class TodoMain extends AppCompatActivity {

    private EditText todoEditText;
    private Button addTodoButton;
    private ListView todoListView;
    private ArrayAdapter<String> todoAdapter;
    private List<String> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);

        todoEditText = findViewById(R.id.todoEditText);
        addTodoButton = findViewById(R.id.addTodoButton);
        todoListView = findViewById(R.id.todoListView);

        todoList = new ArrayList<>();
        todoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList);
        todoListView.setAdapter(todoAdapter);

        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = todoEditText.getText().toString().trim();
                if (!todoItem.isEmpty()) {
                    todoList.add(todoItem);
                    todoAdapter.notifyDataSetChanged();
                    todoEditText.setText("");
                    Toast.makeText(TodoMain.this, "Todo added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TodoMain.this, "Please enter a todo item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
