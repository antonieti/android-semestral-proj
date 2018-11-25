package com.example.aluno.proj;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;


import br.edu.ifsp.cmp.entities.Habit;
import br.edu.ifsp.cmp.repository.TaskRepository;

public class MyAdapterHabit extends RecyclerView.Adapter<MyAdapterHabit.MyViewHolder> {

    private static  List<Habit> habitList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ProgressBar progressBar;
        public TextView habit;
        public ImageButton add_times;
        public ImageButton remove_button;

        public MyViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progress_habit);
            habit = (TextView) v.findViewById(R.id.habit_text);

            add_times = (ImageButton) v.findViewById(R.id.add_time);


            add_times.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Habit habitSearched = null;

                    habitSearched = findHabit(habit.getText().toString());
                    habitSearched.incrementProgressCount();
                    progressBar.setProgress(habitSearched.getProgressCount());
                    if(habitSearched.getGoalCount()==habitSearched.getProgressCount()){
                        habitList.remove(habitSearched);
                    }

                }
            });

            remove_button = (ImageButton) v.findViewById(R.id.delete_button);
            remove_button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Habit habitSearched = findHabit(habit.getText().toString());
                    habitList.remove(habitSearched);

                }
            });
        }

        private Habit findHabit(String  name) {
            for (Habit habito : habitList) {
                if ((habito!=null) && StringUtils.isNotEmpty(habito.getName()) && habito.getName().equals(name)) {
                    return habito;
                }
            }
            return null;
        }
    }

    public MyAdapterHabit(List<Habit> habits) {
        MyAdapterHabit.habitList=habits;
    }

    @Override
    public MyAdapterHabit.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_detail, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Habit habit = habitList.get(position);
        holder.habit.setText(habit.getName());
        holder.progressBar.setMax(habit.getGoalCount());
        holder.progressBar.setProgress(habit.getProgressCount());
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }
}