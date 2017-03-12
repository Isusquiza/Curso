package com.jesusmariagarcia.petagram2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jesusmariagarcia.petagram2.R;
import com.jesusmariagarcia.petagram2.adapter.FotosPerfilAdapter;
import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilMascotaFragment extends Fragment {

    private ArrayList<FotoPerfil> fotosPerfil;
    private RecyclerView listaFotosPerfil;

    public PerfilMascotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil_mascota, container, false);

        //
        // Dataset perfil de mascota
        //

        fotosPerfil = new ArrayList<FotoPerfil>();

        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 2));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 0));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 2));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 7));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 2));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 8));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 2));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 12));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 7));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 2));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 8));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 7));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 2));
        fotosPerfil.add(new FotoPerfil(R.drawable.puppy1, 8));


        listaFotosPerfil = (RecyclerView) v.findViewById(R.id.rvPerfilMascota);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);

        listaFotosPerfil.setLayoutManager(glm);

        inicializarAdapter();


        return v;
    }

    public void inicializarAdapter() {
        FotosPerfilAdapter adapter = new FotosPerfilAdapter(fotosPerfil);
        listaFotosPerfil.setAdapter(adapter);

    }

}
