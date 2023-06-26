package com.example.btl.Todo;


import android.content.Context;

import java.util.List;

public class Task {
//    private static final String TABLE_NAME = "tasks";
//    private static final String COLUMN_TASK_ID = "task_id";
//    private static final String COLUMN_TASK_TITLE = "task_title";
//    private static final String COLUMN_DATE = "date";
//    private static final String COLUMN_TASK_DESCRIPTION = "task_description";
//    private static final String COLUMN_IS_COMPLETE = "is_complete";
//    private static final String COLUMN_FIRST_ALARM_TIME = "first_alarm_time";
//    private static final String COLUMN_SECOND_ALARM_TIME = "second_alarm_time";
//    private static final String COLUMN_LAST_ALARM = "last_alarm";
//    private static final String COLUMN_EVENT = "event";

    private int taskId;
    private String taskTitle;
    private String date;
    private String month;
    private String taskDescription;
    private boolean isComplete;
    private String firstAlarmTime;
    private String secondAlarmTime;
    private String lastAlarm;
    private String event;

    public Task() {
        this.taskId = 0;
        this.taskTitle = "";
        this.date = "";
        this.taskDescription = "";
        this.isComplete = false;
        this.firstAlarmTime = "";
        this.secondAlarmTime = "";
        this.lastAlarm = "";
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

    public String getFirstAlarmTime() {
        return firstAlarmTime;
    }

    public void setFirstAlarmTime(String firstAlarmTime) {
        this.firstAlarmTime = firstAlarmTime;
    }

    public String getSecondAlarmTime() {
        return secondAlarmTime;
    }

    public void setSecondAlarmTime(String secondAlarmTime) {
        this.secondAlarmTime = secondAlarmTime;
    }

    public String getLastAlarm() {
        return lastAlarm;
    }

    public void setLastAlarm(String lastAlarm) {
        this.lastAlarm = lastAlarm;
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

        public void delete(Context context) {
            TaskDatabaseHelper dbHelper = new TaskDatabaseHelper(context);
            dbHelper.deleteTask(taskId);
        }
}


