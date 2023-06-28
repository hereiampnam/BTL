package com.example.btl.Todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TaskDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "2.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tasks";
    private static final String COLUMN_TASK_ID = "task_id";
    private static final String COLUMN_TASK_TITLE = "task_title";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_TASK_DESCRIPTION = "task_description";
    private static final String COLUMN_IS_COMPLETE = "is_complete";

    private static final String COLUMN_EVENT = "event";

    public TaskDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TASK_TITLE + " TEXT," +
                COLUMN_TASK_DESCRIPTION + " TEXT," +
                COLUMN_IS_COMPLETE + " INTEGER," +
                COLUMN_DATE + " TEXT," +
                COLUMN_TIME + " TEXT," +
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
        values.put(COLUMN_TIME, task.getTime());
        values.put(COLUMN_TASK_DESCRIPTION, task.getTaskDescription());
        values.put(COLUMN_IS_COMPLETE, task.isComplete() ? 1 : 0);
        values.put(COLUMN_EVENT, task.getEvent());

        long rowId = db.insert(TABLE_NAME, null, values);

        db.close();

        return rowId;
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        onCreate(db);
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setTaskId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TASK_ID)));
            task.setTaskTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_TITLE)));
            task.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
            task.setTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
            task.setTaskDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_DESCRIPTION)));
            task.setComplete(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_COMPLETE)) == 0);
            task.setEvent(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EVENT)));
            taskList.add(task);
        }

        cursor.close();
        db.close();

        return taskList;
    }
    public Task getTaskById(int taskId) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {COLUMN_TASK_ID, COLUMN_TASK_TITLE, COLUMN_DATE,COLUMN_TIME, COLUMN_TASK_DESCRIPTION,
                COLUMN_IS_COMPLETE,  COLUMN_EVENT};


        String selection = COLUMN_TASK_ID + "=?";
        String[] selectionArgs = {String.valueOf(taskId)};

        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Task task = null;
        if (cursor.moveToFirst()) {
            task = new Task();
            task.setTaskId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TASK_ID)));
            task.setTaskTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_TITLE)));
            task.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
            task.setTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
            task.setTaskDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_DESCRIPTION)));
            task.setComplete(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_COMPLETE)) == 1);

            task.setEvent(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EVENT)));
        }

        cursor.close();
        db.close();

        return task;
    }

    public void updateTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_TITLE, task.getTaskTitle());
        values.put(COLUMN_DATE, task.getDate());
        values.put(COLUMN_TIME, task.getTime());
        values.put(COLUMN_TASK_DESCRIPTION, task.getTaskDescription());
        values.put(COLUMN_IS_COMPLETE, task.isComplete() ? 1 : 0);

        values.put(COLUMN_EVENT, task.getEvent());

        db.update(TABLE_NAME, values, COLUMN_TASK_ID + "=?", new String[]{String.valueOf(task.getTaskId())});

        db.close();
    }

    public void updateTaskStatus(Task task) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_COMPLETE, task.isComplete() ? 1 : 0);

        db.update(TABLE_NAME, values, COLUMN_TASK_ID + "=?", new String[]{String.valueOf(task.getTaskId())});

        db.close();
    }

    public void deleteTask(int taskId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_TASK_ID + "=?", new String[]{String.valueOf(taskId)});
        db.close();
    }
}
