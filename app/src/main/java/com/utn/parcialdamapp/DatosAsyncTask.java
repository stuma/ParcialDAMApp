package com.utn.parcialdamapp;

import android.os.AsyncTask;

import java.util.List;

public class DatosAsyncTask extends AsyncTask<String, Void, List<Dato>> {

    private DatoDAO dao;
    private OnDatoResultCallback callback;

    public DatosAsyncTask(DatoDAO dao, OnDatoResultCallback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Dato> doInBackground(String... strings) {
        return dao.findAll();
    }

    @Override
    protected void onPostExecute(List<Dato> datos) {
        super.onPostExecute(datos);
        callback.onResult(datos);
    }
}
