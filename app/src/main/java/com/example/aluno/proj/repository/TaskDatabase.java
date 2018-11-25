package com.example.aluno.proj.repository;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "calendapp.db";
    private static final String TABLE = TaskEnum.task.toString();
    private static final String COLUNM_ID = TaskEnum.id.toString();
    private static final String COLUNM_NAME = TaskEnum.name.toString();
    private static final String COLUNM_STATUS = TaskEnum.status.toString();
    private static final int VERSION=1;

    public TaskDatabase(Context context){
        super(context, DATABASE_NAME,null,VERSION);
    }


    public TaskDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE+"("
                + COLUNM_ID + " integer primary key autoincrement,"
                + COLUNM_NAME + " text unique,"
                + COLUNM_STATUS + " integer default 0);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
