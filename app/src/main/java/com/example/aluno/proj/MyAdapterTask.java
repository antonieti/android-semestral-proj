package com.example.aluno.proj;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

import br.edu.ifsp.cmp.entities.Habit;
import br.edu.ifsp.cmp.entities.Task;

public class MyAdapterTask extends RecyclerView.Adapter<MyAdapterTask.MyViewHolder> {

    private static List<Task> tasks;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkTask;


        public MyViewHolder(final View v) {
            super(v);
            checkTask = (CheckBox) v.findViewById(R.id.text_view);
            checkTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Task task = findTask(checkTask.getText().toString());
                    tasks.remove(task);
                }
            });
        }

        private Task findTask(String name) {
            for (Task task : tasks) {
                if ((task!=null) && StringUtils.isNotEmpty(task.getName()) && task.getName().equals(name)) {
                    return task;
                }
            }
            return null;
        }
    }

    public MyAdapterTask(List<Task> task) {
        MyAdapterTask.tasks = task;
        this.notifyDataSetChanged();
    }

    @Override
    public MyAdapterTask.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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