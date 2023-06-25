package com.example.btl.Todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl.R;

import java.util.List;

public class TodoListAdapter extends BaseAdapter {
    private List<String> todoItems;

    public TodoListAdapter(List<String> todoItems) {
        this.todoItems = todoItems;
    }

    @Override
    public int getCount() {
        return todoItems.size();
    }

    @Override
    public String getItem(int position) {
        return todoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        }

        String itemInformation = getItem(position);
        TextView itemTextView = convertView.findViewById(R.id.todoItemTextView);
        itemTextView.setText(itemInformation);

        return convertView;
    }
}
