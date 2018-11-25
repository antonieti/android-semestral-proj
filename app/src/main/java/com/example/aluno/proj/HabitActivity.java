package com.example.aluno.proj;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.cmp.entities.Habit;

public class HabitActivity extends AppCompatActivity {

    private TextView calText;
    private TextView taskText;
    private FloatingActionButton habitFab;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    public static List<Habit> habits = new ArrayList<Habit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);

        this.calText = findViewById(R.id.habit_calTab);
        this.taskText = findViewById(R.id.habit_taskTab);
        mRecyclerView = (RecyclerView) findViewById(R.id.habit_recycler);
        mAdapter = new MyAdapterHabit(habits);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



        calText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityCal = new Intent(HabitActivity.this, MainActivity.class);
                startActivity(activityCal);
            }
        });

        taskText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityTask = new Intent(HabitActivity.this, TaskActivity.class);
                startActivity(activityTask);
            }
        });

        this.habitFab = findViewById(R.id.habit_fab);

        habitFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Context context = HabitActivity.this;
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText habitNameTxt = new EditText(context);
                habitNameTxt.setHint("Nome");
                layout.addView(habitNameTxt); // Notice this is an add method

                final EditText goalCountTxt = new EditText(context);
                goalCountTxt.setHint("Meta");
                goalCountTxt.setRawInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(goalCountTxt); // Another add method

                new AlertDialog.Builder(HabitActivity.this)
                        .setTitle("Inserir Tarefa")
                        .setMessage("Nome da Tarefa:")
                        .setView(layout)
                        .setPositiveButton("Inserir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String nomeTask = habitNameTxt.getText().toString();
                                Short goalTimes = Short.parseShort(goalCountTxt.getText().toString());
                                Habit habit = Habit.builder().name(nomeTask).goalCount(goalTimes).build();
                                habits.add(habit);
//                                TextView text = findViewById(R.id.tarefa1);
//                                text.setText(task);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();

            }
        });


    }
}
