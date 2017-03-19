package com.jesusmariagarcia.petagram2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jesusmariagarcia.petagram2.adapter.MascotaAdapter;
import com.jesusmariagarcia.petagram2.db.ConstructorMascotas;
import com.jesusmariagarcia.petagram2.db.ConstructorTop5Mascotas;
import com.jesusmariagarcia.petagram2.pojo.Mascota;


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
        // Obtener las 5 mascotas con mas Likes
        //

        ConstructorTop5Mascotas constructorTop5Mascotas = new ConstructorTop5Mascotas(getBaseContext());
        mascotas = constructorTop5Mascotas.obtenerTop5();


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
        MascotaAdapter adapter = new MascotaAdapter(mascotas, this);
        listaMascotas.setAdapter(adapter);

    }
}
