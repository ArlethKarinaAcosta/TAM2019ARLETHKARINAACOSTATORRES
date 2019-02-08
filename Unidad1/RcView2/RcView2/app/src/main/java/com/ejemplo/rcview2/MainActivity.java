package com.ejemplo.rcview2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    String [] nombres={"ACOSTA TORRES ARLETH KARINA",
            "CERVANTES DÍAZ EMMANUEL",
            "DE LEÓN INDA ERICK EVERALDO",
            "LÓPEZ ROBLES SERGIO",
            "MEDINA MADERA GUILLERMO "};


    String[] noControl = {"15400771", "15400772", "15400773", "15400774", "15400775"};

    String[] carrera = {"ITICS", "ITICS","ITICS","ITICS","ITICS"};

    //int[] foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        // Crear referencias hacia el componente RecycleriView
        recyclerView = findViewById(R.id.recycler_id);

        // Crear un objeto de tipo RecyclerAdapter que recibe un arreglo de cadenas
        adapter = new RecyclerAdapter(nombres, noControl, carrera, MainActivity.this);

        // Crea un objeto de tipo LinearLayoutManager
        layoutManager = new LinearLayoutManager(this); //Proporciona una funcionalidad similar al ListView.

        // Establecer el LayautManager
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Establecer el Adapter
        recyclerView.setAdapter(adapter);




    }
}
