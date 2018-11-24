package com.example.aluno.proj;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    private TextView habitText;
    private TextView calText;
    private TextView habito;
    private FloatingActionButton taskFab;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //private Habit habito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(null);
        mRecyclerView.setAdapter(mAdapter);


        this.habitText = findViewById(R.id.task_habitTab);
        this.calText = findViewById(R.id.task_calTab);
        this.habito = findViewById(R.id.tarefa1);

        habitText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityHabit = new Intent(TaskActivity.this, HabitActivity.class);
                startActivity(activityHabit);
            }
        });

        calText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityCal = new Intent(TaskActivity.this, MainActivity.class);
                startActivity(activityCal);
            }
        });

        this.taskFab = findViewById(R.id.task_fab);

        taskFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText task_txt = new EditText(TaskActivity.this);

                new AlertDialog.Builder(TaskActivity.this)
                        .setTitle("Inserir Tarefa")
                        .setMessage("Nome da Tarefa:")
                        .setView(task_txt)
                        .setPositiveButton("Inserir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String task = task_txt.getText().toString();
                                TextView text = findViewById(R.id.tarefa1);
                                text.setText(task);
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
