package com.example.btl.Todo;

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

public class TaskAdd extends AppCompatActivity {
    private EditText editTextTaskTitle;
    private Button buttonSave;
    private EditText editTextTaskDescription;
    private Button buttonTaskDate;
    private Button buttonTaskTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);
        editTextTaskDescription =findViewById(R.id.addTaskDescription);
        editTextTaskTitle = findViewById(R.id.addTaskTitle);
        buttonSave = findViewById(R.id.addTask);
        buttonTaskDate = findViewById(R.id.taskDate);
        buttonTaskTime = findViewById(R.id.taskTime);

        buttonSave.setOnClickListener(v -> {
            String taskTitle = editTextTaskTitle.getText().toString();
            String taskDate = buttonTaskDate.getText().toString();
            String taskTime = buttonTaskTime.getText().toString();
            String taskDescription = editTextTaskDescription.getText().toString();

            // Validate task data
            if (taskTitle.isEmpty()) {
                Toast.makeText(TaskAdd.this, "Please enter a task title", Toast.LENGTH_SHORT).show();
                return;
            }
            if (taskDescription.isEmpty()) {
                Toast.makeText(TaskAdd.this, "Please enter a task description", Toast.LENGTH_SHORT).show();
                return;
            }

            if (taskDate.isEmpty()) {
                Toast.makeText(TaskAdd.this, "Please select a task date", Toast.LENGTH_SHORT).show();
                return;
            }

            if (taskTime.isEmpty()) {
                Toast.makeText(TaskAdd.this, "Please select a task time", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a new task object
            Task task = new Task();
            task.setTaskTitle(taskTitle);
//            task.setTaskDescription("");
            task.setTaskDescription(taskDescription);
            task.setDate(taskDate);
            task.setComplete(false);
            task.setFirstAlarmTime("");
            task.setSecondAlarmTime("");
            task.setLastAlarm("");
            task.setEvent("");

            // Save the task to your database
            long rowId = task.save(TaskAdd.this);

            if (rowId != -1) {

                Toast.makeText(TaskAdd.this, "Task added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( TaskAdd.this,TaskList.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(TaskAdd.this, "Failed to add the task", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void pickDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(TaskAdd.this, (view1, selectedYear, selectedMonth, selectedDayOfMonth) -> {
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

        TimePickerDialog timePickerDialog = new TimePickerDialog(TaskAdd.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                String selectedTime = selectedHour + ":" + selectedMinute;
                buttonTaskTime.setText(selectedTime);
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
}
