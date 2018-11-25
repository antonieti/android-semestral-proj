package com.example.aluno.proj;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.cmp.entities.Task;


public class TaskActivity extends AppCompatActivity {

    private TextView habitText;
    private TextView calText;
    private TextView habito;
    private FloatingActionButton taskFab;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private final int SENSOR_SENSITIVITY = 2;
    static List<Task> tasks = new ArrayList<Task>();

    @Override
    protected void onStart() {
        super.onStart();
        if (tasks.size()>0){
            NotificationManager NM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification.Builder(getApplicationContext()).setContentTitle("Tarefas").setContentText("Você ainda tem tarefas por fazer").setSmallIcon(R.drawable.ic_playlist_add_check_black_24dp).build();

            notify.flags = Notification.FLAG_HIGH_PRIORITY;
            NM.notify(0,notify);

        }


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);




        mRecyclerView = (RecyclerView) findViewById(R.id.task_list);
        mAdapter = new MyAdapterTask(tasks);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        this.habitText = findViewById(R.id.task_habitTab);
        this.calText = findViewById(R.id.task_calTab);

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
                                String nomeTask = task_txt.getText().toString();
                                Task task = Task.builder().name(nomeTask).build();
                                tasks.add(task);
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

