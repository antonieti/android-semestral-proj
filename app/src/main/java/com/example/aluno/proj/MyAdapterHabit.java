package com.example.aluno.proj;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.companyname.habittracking.entities.Habit;
import com.companyname.habittracking.entities.Task;

import java.util.List;

public class MyAdapterTask extends RecyclerView.Adapter<MyAdapterTask.MyViewHolder> {

    private static List<Habit> habits;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkTask;

        public MyViewHolder(View v) {
            super(v);
            checkTask = (CheckBox) v.findViewById(R.id.text_view);
        }
    }

    public MyAdapterTask(List<Habit> habit) {
        MyAdapterTask.habits = habit;
    }

    @Override
    public MyAdapterTask.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_detail, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Habit habit= habits.get(position);
        holder.checkTask.setText(habit.getName());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}