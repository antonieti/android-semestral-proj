package com.example.aluno.proj;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.companyname.habittracking.entities.Habit;
import com.companyname.habittracking.entities.Task;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static List<Task> tasks;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkTask;

        public MyViewHolder(View v) {
            super(v);
            checkTask = (CheckBox) v.findViewById(R.id.text_view);
        }
    }

    public MyAdapter(List<Task> task) {
        MyAdapter.tasks = task;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckBox v = (CheckBox) LayoutInflater.from(parent.getContext()).inflate(R.layout.task_detail, parent, false);
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_detail, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.checkTask.setText(task.getName());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}