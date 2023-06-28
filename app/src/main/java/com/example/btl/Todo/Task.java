package com.example.btl.Todo;

import android.content.Context;
import java.util.List;

public class Task {
    private int taskId;
    private String taskTitle;
    private String date;
    private String time;
    private String taskDescription;
    private boolean isComplete;

    private String event;

    public Task() {
        this.taskId = 0;
        this.taskTitle = "";
        this.date = "";
        this.time = "";
        this.taskDescription = "";
        this.isComplete = false;
        this.event = "";
    }

    // Getters and setters for the Task fields

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String status(boolean isComplete) {
        if (!isComplete) {
            return "DO IT";
        } else {
            return "DONE";
        }
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    // CRUD operations

    public long save(Context context) {
        TaskDatabaseHelper dbHelper = new TaskDatabaseHelper(context);
        return dbHelper.saveTask(this);
    }

    public static List<Task> getAll(Context context) {
        TaskDatabaseHelper dbHelper = new TaskDatabaseHelper(context);
        return dbHelper.getAllTasks();
    }
    public static Task getTaskById(Context context, int taskId) {
        TaskDatabaseHelper dbHelper = new TaskDatabaseHelper(context);
        return dbHelper.getTaskById(taskId);
    }


    public void update(Context context) {
        TaskDatabaseHelper dbHelper = new TaskDatabaseHelper(context);
        dbHelper.updateTask(this);
    }

    public void updateTaskStatus(Context context) {
        TaskDatabaseHelper dbHelper = new TaskDatabaseHelper(context);
        dbHelper.updateTaskStatus(this);
    }


}
