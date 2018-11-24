package com.example.aluno.proj;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HabitActivity extends AppCompatActivity {

    private TextView calText;
    private TextView taskText;
    private FloatingActionButton habitFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);

        this.calText = findViewById(R.id.habit_calTab);
        this.taskText = findViewById(R.id.habit_taskTab);

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
                final EditText habit_txt = new EditText(HabitActivity.this);

                AlertDialog.Builder builder = new AlertDialog.Builder(HabitActivity.this);
                LayoutInflater inflater = HabitActivity.this.getLayoutInflater();

                builder.setView(inflater.inflate(R.layout.alert_habit, null));

                builder.setPositiveButton("Inserir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //String url = habit_txt.getText().toString();
                                //Inserir(null, url);
                            }
                        });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                builder.show();
            }
        });



    }
}
