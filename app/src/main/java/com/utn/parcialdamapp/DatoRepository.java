package com.utn.parcialdamapp;

import android.app.Application;
import android.util.Log;

import java.util.List;

public class DatoRepository implements OnDatoResultCallback {
    private DatoDAO datoDAO;
    private OnResultCallback callback;

    public DatoRepository(Application application, OnResultCallback context){
        AppDB db = AppDB.getInstance(application);
        datoDAO = db.datoDAO();
        callback = context;
    }

    public void insert(final Dato dato){

        AppDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                datoDAO.insert(dato);
            }
        });
    }

    public void delete(final Dato dato){
        AppDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                datoDAO.delete(dato);
            }
        });
    }

    public void actualizar(final Dato dato){
        AppDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                datoDAO.update(dato);
            }
        });
    }

    public void search(final String id) {
        AppDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                datoDAO.search(id);
            }
        });
    }

    public void findAll()
         {
            new DatosAsyncTask(datoDAO, (OnDatoResultCallback) this).execute();
        }

    @Override
    public void onResult(List<Dato> dato) {
        callback.onResult(dato);
    }

    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }
}
