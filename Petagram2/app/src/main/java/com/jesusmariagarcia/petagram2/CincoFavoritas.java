package com.jesusmariagarcia.petagram2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class CincoFavoritas extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco_favoritas);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar2);
        setSupportActionBar(actionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        // Dataset
        //

        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("Perro1", R.drawable.puppy1));
        mascotas.add(new Mascota("Perro2", R.drawable.puppy2));
        mascotas.add(new Mascota("Perro3", R.drawable.puppy3));
        mascotas.add(new Mascota("Perro4", R.drawable.puppy4));
        mascotas.add(new Mascota("Perro5", R.drawable.puppy5));

        //
        // Inicialización del RecyclerView
        //

        listaMascotas = (RecyclerView) findViewById(R.id.rv5MascotasFavoritas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarAdapter();

    }

    public void inicializarAdapter() {
        MascotaAdapter adapter = new MascotaAdapter(mascotas);
        listaMascotas.setAdapter(adapter);

    }
}
