package com.example.btl.Todo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;

import java.util.Calendar;



public class TaskUpdate extends AppCompatActivity {
    private EditText getTaskTitle;
    private EditText getTaskDescription;
    private EditText getTaskEvent;
    private Button btnUpdateTask;

    private Button buttonTaskDate;

    private Button buttonTaskTime;
    private int taskId;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_update);

        // Get the task ID from the intent
        taskId = getIntent().getIntExtra("taskId", -1);
        // Fetch the task from the database based on the task ID
        task = Task.getTaskById(this, taskId);
        // Initialize UI elements
        getTaskTitle = findViewById(R.id.updateTaskTitle);
        getTaskDescription = findViewById(R.id.updateTaskDescription);
        buttonTaskDate = findViewById(R.id.taskDate);
        buttonTaskTime = findViewById(R.id.taskTime);
        getTaskEvent = findViewById(R.id.taskEvent);
        btnUpdateTask = findViewById(R.id.updateTask);
        // Set initial values for the UI elements
        getTaskTitle.setText(task.getTaskTitle());
        getTaskDescription.setText(task.getTaskDescription());
        getTaskEvent.setText(task.getEvent());

        // Update task button click listener
        btnUpdateTask.setOnClickListener(v ->
            updateTask());
    }

    private void updateTask() {

        task.setTaskId(taskId);
        // Update task with new values from the UI elements
        task.setTaskTitle(getTaskTitle.getText().toString());
        task.setTaskDescription(getTaskDescription.getText().toString());
        task.setEvent(getTaskEvent.getText().toString());
        task.setDate(buttonTaskDate.getText().toString());
        task.setTime(buttonTaskTime.getText().toString());
        // Update the task in the database
        task.update(this);
        Intent intent = new Intent( TaskUpdate.this,TaskList.class);
        startActivity(intent);

    }
    public void pickDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(TaskUpdate.this, (view1, selectedYear, selectedMonth, selectedDayOfMonth) -> {
            // Handle the selected date
            String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDayOfMonth;
            buttonTaskDate.setText(selectedDate);
        }, year, month, day);
        datePickerDialog.show();
    }

    public void pickTime(View view) {
        // Get current time
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(TaskUpdate.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                buttonTaskTime.setText(selectedTime);
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
}
