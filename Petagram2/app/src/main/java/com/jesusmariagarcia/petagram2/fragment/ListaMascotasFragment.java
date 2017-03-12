package com.jesusmariagarcia.petagram2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jesusmariagarcia.petagram2.R;
import com.jesusmariagarcia.petagram2.adapter.MascotaAdapter;
import com.jesusmariagarcia.petagram2.pojo.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaMascotasFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    public ListaMascotasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_lista_mascotas, container, false);

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

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarAdapter();

        return v;

    }

    public void inicializarAdapter() {
        MascotaAdapter adapter = new MascotaAdapter(mascotas);
        listaMascotas.setAdapter(adapter);

    }

}
