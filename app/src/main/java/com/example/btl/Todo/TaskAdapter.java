package com.example.btl.Todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
//        private TextView monthTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            dateTextView=itemView.findViewById(R.id.dateTextView);
//            monthTextView=itemView.findViewById(R.id.monthTextView);

        }

        public void bind(@NonNull Task task) {
            titleTextView.setText(task.getTaskTitle());
            descriptionTextView.setText(task.getTaskDescription());
            dateTextView.setText(task.getDate());
        }
    }
}
