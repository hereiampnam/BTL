package com.example.btl.Todo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;

import java.util.List;




public class TaskList extends AppCompatActivity {
    private List<Task> taskList;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ImageView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);

        Button btnAdd = findViewById(R.id.btnAdd);
        calendar = findViewById(R.id.calendar);

        // Fetch tasks from the database
        taskList = Task.getAll(this);
        if (taskList.isEmpty()) {
            Log.d("TaskList", "Task list is empty");
        } else {
            Log.d("TaskList", "Task list size: " + taskList.size());
        }

        // Initialize the adapter with the task list
        adapter = new TaskAdapter(this, taskList);
        recyclerView = findViewById(R.id.View);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(TaskList.this, TaskAdd.class);
            startActivity(intent);
        });
        calendar.setOnClickListener(v -> {
            Intent calendarIntent = new Intent(Intent.ACTION_VIEW);
            calendarIntent.setData(Uri.parse("content://com.android.calendar/time"));
            startActivity(calendarIntent);
        });
    }
}
