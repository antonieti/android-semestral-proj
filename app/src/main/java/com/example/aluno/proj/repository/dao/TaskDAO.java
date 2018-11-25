package com.example.aluno.proj.repository.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aluno.proj.repository.TaskDatabase;
import com.example.aluno.proj.repository.TaskEnum;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.cmp.entities.Task;
import br.edu.ifsp.cmp.repository.TaskRepository;

public class TaskDAO implements TaskRepository {

    private SQLiteDatabase db;
    private TaskDatabase taskDatabase;

    public TaskDAO(Context context){
        taskDatabase = new TaskDatabase(context);
    }

    @Override
    public String insert(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskEnum.name.toString(),task.getName());
        db = taskDatabase.getWritableDatabase();
        long result = db.insert(TaskEnum.task.toString(), null, values);

        db.close();

        if (result ==-1){
            return "Erro ao inserir tarefa";
        }
        else {
            return "Tarefa inserida com sucesso";
        }

    }

    @Override
    public void remove(Task task) {

    }

    @Override
    public Task search(Task task) {
        return null;
    }

    @Override
    public List<Task> getAll(Task task) {
        Cursor cursor = db.rawQuery("select * from "+TaskEnum.task.toString()+ " where "+ TaskEnum.status+ "!= 1", null);
        List<Task> tasks = new ArrayList<>();

        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                tasks.add(Task.builder().id(cursor.getLong(cursor.getColumnIndex(TaskEnum.id.toString())))
                        .name(cursor.getString(cursor.getColumnIndex(TaskEnum.name.toString()))).build());

                cursor.moveToNext();

            }
        }
        db.close();

        return tasks;
    }
}
