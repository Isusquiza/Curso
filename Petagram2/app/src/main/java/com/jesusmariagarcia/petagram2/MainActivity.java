package com.jesusmariagarcia.petagram2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(actionBar);

        //
        // Dataset
        //

        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("Puppy1", R.drawable.puppy1));
        mascotas.add(new Mascota("Puppy2", R.drawable.puppy2));
        mascotas.add(new Mascota("Puppy3", R.drawable.puppy3));
        mascotas.add(new Mascota("Puppy4", R.drawable.puppy4));

        //
        // Inicialización del RecyclerView
        //

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarAdapter();


    }

    public void inicializarAdapter() {
        MascotaAdapter adapter = new MascotaAdapter(mascotas);
        listaMascotas.setAdapter(adapter);

    }

    public void irTop5Mascotas(View v)
    {
        Intent intent = new Intent(this, CincoFavoritas.class);
        startActivity(intent);
    }
}
