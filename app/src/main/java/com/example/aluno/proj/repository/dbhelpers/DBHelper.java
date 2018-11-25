package com.example.aluno.proj.repository.dbhelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aluno.proj.repository.enums.HabitEnum;
import com.example.aluno.proj.repository.enums.TaskEnum;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calendapp.db";
    private static final String TABLE_HABIT = HabitEnum.habit.toString();
    private static final String COLUNM_ID_HABIT = HabitEnum.id.toString();
    private static final String COLUNM_NAME_HABIT = HabitEnum.name.toString();
    private static final String COLUNM_PROGRESS_HABIT = HabitEnum.progress.toString();
    private static final String COLUNM_GOAL_HABIT = HabitEnum.goal.toString();
    private static final String TABLE = TaskEnum.task.toString();
    private static final String COLUNM_ID = TaskEnum.id.toString();
    private static final String COLUNM_NAME = TaskEnum.name.toString();
    private static final String COLUNM_STATUS = TaskEnum.status.toString();
    private static final int VERSION = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_HABIT + "("
                + COLUNM_ID_HABIT + " integer primary key autoincrement, "
                + COLUNM_NAME_HABIT + " text unique, "
                + COLUNM_GOAL_HABIT + " integer, "
                + COLUNM_PROGRESS_HABIT + " integer default 0);";

        db.execSQL(sql);

        String sqlTask = "CREATE TABLE "+TABLE+"("
                + COLUNM_ID + " integer primary key autoincrement,"
                + COLUNM_NAME + " text unique,"
                + COLUNM_STATUS + " integer default 0);";

        db.execSQL(sqlTask);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HABIT);
        onCreate(db);
    }
}
