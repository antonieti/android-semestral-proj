package com.example.aluno.proj.repository.utils;

import android.content.Context;

import java.io.File;

public class DBCreatingUtils {



    public static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public static boolean doesDatabaseNotExist(Context context, String dbName) {
        return !doesDatabaseExist(context, dbName);
    }
}
