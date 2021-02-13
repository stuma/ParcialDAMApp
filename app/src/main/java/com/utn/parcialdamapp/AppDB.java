package com.utn.parcialdamapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Dato.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB database;
    public static String databaseName = "db_app";

    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public abstract DatoDAO datoDAO();

    static AppDB getInstance(final Context context) {

        if(database == null){

            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDB.class, databaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
