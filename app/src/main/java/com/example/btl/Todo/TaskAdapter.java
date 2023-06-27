package com.example.btl.Todo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.MainActivity;
import com.example.btl.R;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View task = layoutInflater.inflate(R.layout.todo_task, parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(task);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.bind(task);
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dateTextView;
        private Button statusBtn;
        private Button optionBtn;
        private Button deleteBtn;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            statusBtn = itemView.findViewById(R.id.statusBTN);
            optionBtn = itemView.findViewById(R.id.optionBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }

        public void bind(@NonNull Task task) {
            titleTextView.setText(task.getTaskTitle());
            descriptionTextView.setText(task.getTaskDescription());
            dateTextView.setText(task.getDate());
            statusBtn.setText(task.status(task.isComplete()));
            statusBtn.setOnClickListener(v -> {
                task.setComplete(!task.isComplete());
                statusBtn.setText(task.status(task.isComplete()));
                task.updateTaskStatus(context);
            });
            optionBtn.setOnClickListener(v -> {
                Intent intent = new Intent(context, TaskUpdate.class);
                intent.putExtra("taskId", task.getTaskId());
                context.startActivity(intent);
            });
            deleteBtn.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Delete", (dialog, which) -> {
                            // Perform the delete operation here
                            TaskDatabaseHelper dbHelper = new TaskDatabaseHelper(context);
                            dbHelper.deleteTask(task.getTaskId());
                            Intent intent = new Intent( context,TaskList.class);
                            context.startActivity(intent);

                        })
                        .setNegativeButton("Cancel", (dialog, which) -> {
                            // Cancel the delete operation or do nothing
                        })
                        .create()
                        .show();
            });

        }
    }
}
