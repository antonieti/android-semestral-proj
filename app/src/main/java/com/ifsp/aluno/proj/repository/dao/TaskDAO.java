package com.ifsp.aluno.proj.repository.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ifsp.aluno.proj.repository.dbhelpers.DBHelper;

import com.ifsp.aluno.proj.repository.enums.TaskEnum;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.cmp.entities.Task;
import br.edu.ifsp.cmp.repository.TaskRepository;

public class TaskDAO implements TaskRepository {

    private SQLiteDatabase db;
    private DBHelper taskDatabase;

    public TaskDAO(Context context){
        taskDatabase = new DBHelper(context);
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

    public void update(Task task){
        ContentValues valores;
        String where;

        db = taskDatabase.getWritableDatabase();

        where = TaskEnum.name.toString() + "= '" + task.getName()+"'";

        valores = new ContentValues();
        valores.put(TaskEnum.name.toString(), task.getName());
        valores.put(TaskEnum.id.toString(), task.getId());
        valores.put(TaskEnum.status.toString(),1);

        db.update(TaskEnum.task.toString(),valores,where,null);
        db.close();
    }
    @Override
    public void remove(Task task) {

    }

    @Override
    public Task searchByName(String name) {
        return null;
    }

    @Override
    public List<Task> getAll() {
        db = taskDatabase.getReadableDatabase();
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
