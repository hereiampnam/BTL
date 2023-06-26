package com.example.btl.Todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TaskDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tasks";
    private static final String COLUMN_TASK_ID = "task_id";
    private static final String COLUMN_TASK_TITLE = "task_title";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TASK_DESCRIPTION = "task_description";
    private static final String COLUMN_IS_COMPLETE = "is_complete";
    private static final String COLUMN_FIRST_ALARM_TIME = "first_alarm_time";
    private static final String COLUMN_SECOND_ALARM_TIME = "second_alarm_time";
    private static final String COLUMN_LAST_ALARM = "last_alarm";
    private static final String COLUMN_EVENT = "event";

    public TaskDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TASK_TITLE + " TEXT," +
                COLUMN_DATE + " TEXT," +
                COLUMN_TASK_DESCRIPTION + " TEXT," +
                COLUMN_IS_COMPLETE + " INTEGER," +
                COLUMN_FIRST_ALARM_TIME + " TEXT," +
                COLUMN_SECOND_ALARM_TIME + " TEXT," +
                COLUMN_LAST_ALARM + " TEXT," +
                COLUMN_EVENT + " TEXT" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public long saveTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_TITLE, task.getTaskTitle());
        values.put(COLUMN_DATE, task.getDate());
        values.put(COLUMN_TASK_DESCRIPTION, task.getTaskDescription());
        values.put(COLUMN_IS_COMPLETE, task.isComplete() ? 1 : 0);
        values.put(COLUMN_FIRST_ALARM_TIME, task.getFirstAlarmTime());
        values.put(COLUMN_SECOND_ALARM_TIME, task.getSecondAlarmTime());
        values.put(COLUMN_LAST_ALARM, task.getLastAlarm());
        values.put(COLUMN_EVENT, task.getEvent());

        long rowId = db.insert(TABLE_NAME, null, values);

        db.close();

        return rowId;
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setTaskId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TASK_ID)));
            task.setTaskTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_TITLE)));
            task.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
            task.setTaskDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_DESCRIPTION)));
            task.setComplete(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_COMPLETE)) == 1);
            task.setFirstAlarmTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_ALARM_TIME)));
            task.setSecondAlarmTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SECOND_ALARM_TIME)));
            task.setLastAlarm(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_ALARM)));
            task.setEvent(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EVENT)));

            taskList.add(task);
        }

        cursor.close();
        db.close();

        return taskList;
    }

    public void deleteTask(int taskId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_TASK_ID + "=?", new String[]{String.valueOf(taskId)});
        db.close();
    }
}
