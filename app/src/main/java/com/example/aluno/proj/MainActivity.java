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

public class MainActivity extends AppCompatActivity {

    private TextView habitText;
    private TextView taskText;
    private FloatingActionButton calFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.habitText = findViewById(R.id.cal_habitTab);
        this.taskText = findViewById(R.id.cal_taskTab);

        habitText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityHabit = new Intent(MainActivity.this, HabitActivity.class);
                startActivity(activityHabit);
            }
        });

        taskText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityTask = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(activityTask);
            }
        });

        this.calFab = findViewById(R.id.cal_fab);

        calFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText habit_txt = new EditText(MainActivity.this);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                builder.setView(inflater.inflate(R.layout.alert_cal, null));

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
