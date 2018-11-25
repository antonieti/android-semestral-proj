package com.ifsp.aluno.proj.repository.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ifsp.aluno.proj.repository.dbhelpers.DBHelper;
import com.ifsp.aluno.proj.repository.enums.HabitEnum;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.cmp.entities.Habit;
import br.edu.ifsp.cmp.repository.HabitRepository;

public class HabitDAO implements HabitRepository {
    private SQLiteDatabase db;
    private DBHelper habitDatabase;

    public HabitDAO(Context context){
        habitDatabase = new DBHelper(context);
    }

    @Override
    public String insert(Habit habit) {
        ContentValues values = new ContentValues();
        values.put(HabitEnum.name.toString(),habit.getName());
        values.put(HabitEnum.goal.toString(),habit.getGoalCount());
        db = habitDatabase.getWritableDatabase();
        long result = db.insert(HabitEnum.habit.toString(), null, values);

        db.close();

        if (result ==-1){
            return "Erro ao inserir habito";
        }
        else {
            return "Habito inserido com sucesso";
        }
    }

    @Override
    public String update(Habit habit) {
        ContentValues valores;
        String where;

        db = habitDatabase.getWritableDatabase();

        where = HabitEnum.name.toString() + "= '" + habit.getName()+"'";

        valores = new ContentValues();
        valores.put(HabitEnum.name.toString(), habit.getName());
        valores.put(HabitEnum.id.toString(), habit.getId());
        valores.put(HabitEnum.goal.toString(),habit.getGoalCount());
        habit.incrementProgressCount();
        valores.put(HabitEnum.progress.toString(),habit.getProgressCount());
        db.update(HabitEnum.habit.toString(),valores,where,null);
        db.close();



        return null;
    }

    @Override
    public void remove(Habit habit) {
        String where = HabitEnum.name.toString()+ "='" + habit.getName()+"'";
        db = habitDatabase.getReadableDatabase();
        db.delete(HabitEnum.habit.toString(),where,null);
        db.close();
    }

    @Override
    public Habit searchByName(String s) {
        return null;
    }

    @Override
    public List<Habit> getAll() {
        db = habitDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+HabitEnum.habit.toString(), null);
        List<Habit> habits= new ArrayList<>();

        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Habit habit= Habit.builder().id(cursor.getLong(cursor.getColumnIndex(HabitEnum.id.toString())))
                        .name(cursor.getString(cursor.getColumnIndex(HabitEnum.name.toString())))
                        .goalCount(cursor.getShort(cursor.getColumnIndex(HabitEnum.goal.toString())))
                        .build();
                habit.setProgressCount(cursor.getShort(cursor.getColumnIndex(HabitEnum.progress.toString())));
                habits.add(habit);

                cursor.moveToNext();

            }
        }
        db.close();

        return habits;
    }
}
