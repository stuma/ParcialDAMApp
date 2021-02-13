package com.utn.parcialdamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatoRepository.OnResultCallback{

    Application context = this.getApplication();
    DatoRepository.OnResultCallback callback = this;

    ToggleButton toggleButton;
    EditText editText;
    Button guardarButton, verDatosButton;
    ListView listView;
    private ArrayAdapter<String> datosArrayAdapter;
    List<Dato> listaDatos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatoRepository repository = new DatoRepository(MainActivity.super.getApplication(), callback);
        repository.findAll();
        Dato dato = new Dato();

        toggleButton = findViewById(R.id.toggle_valido);
        guardarButton = findViewById(R.id.save_button);
        verDatosButton = findViewById(R.id.ver_datos_button);
        editText = findViewById(R.id.input_datos);

        CharSequence t3 = "No Válido";
        toggleButton.setTextOff(t3);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButton.isChecked()){
                    CharSequence t1 = "Válido";
                    toggleButton.setTextOn(t1);
                }else{
                    CharSequence t2 = "No Válido";
                    toggleButton.setTextOff(t2);
                }
            }
        });

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dato.setValor(editText.getText().toString());
                dato.setValido(toggleButton.isChecked());
                repository.insert(dato);

                Toast.makeText(MainActivity.this, "Dato Ingresado", Toast.LENGTH_SHORT).show();
            }
        });

        listView = findViewById(R.id.list_view);
        verDatosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setVisibility(View.VISIBLE);


                List<String> listaDatosAplanados = new ArrayList<>();

                for (Dato dato: listaDatos) {
                    listaDatosAplanados.add(dato.getValor());
                }

                datosArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listaDatosAplanados);
                listView.setAdapter(datosArrayAdapter);
            }
        });
    }

    @Override
    public void onResult(List result) {
        listaDatos = result;
    }
}