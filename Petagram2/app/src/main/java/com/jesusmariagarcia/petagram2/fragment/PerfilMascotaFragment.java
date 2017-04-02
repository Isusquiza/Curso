package com.jesusmariagarcia.petagram2.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jesusmariagarcia.petagram2.R;
import com.jesusmariagarcia.petagram2.adapter.FotosPerfilAdapter;
import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;
import com.jesusmariagarcia.petagram2.presentador.IPerfilMascotaFragmentPresenter;
import com.jesusmariagarcia.petagram2.presentador.PerfilMascotaFragmentPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilMascotaFragment extends Fragment implements IPerfilMascotaFragment{

    private ArrayList<FotoPerfil> fotosPerfil;
    private RecyclerView listaFotosPerfil;
    private ImageView imgProfileImage;
    private TextView tvProfileFullName;

    private IPerfilMascotaFragmentPresenter presenter;
    private String instaUser = "1";
    boolean presenterExec = false;

    public PerfilMascotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_perfil_mascota, container, false);

        listaFotosPerfil = (RecyclerView) v.findViewById(R.id.rvPerfilMascota);
        imgProfileImage = (ImageView) v.findViewById(R.id.imgPerfilMascota);
        tvProfileFullName = (TextView) v.findViewById(R.id.tvNombrePerfilMascota);

        getInstaUser();
        if(instaUser != "1") {
            presenter = new PerfilMascotaFragmentPresenter(this, getContext(), instaUser);
            presenterExec = true;
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(!presenterExec) {

            getInstaUser();

            if(instaUser != "1")
                presenter =  new PerfilMascotaFragmentPresenter(this, getContext(), instaUser);

        }
    }

    public void getInstaUser() {
        SharedPreferences misPreferencias = this.getActivity().getSharedPreferences("Instagram", Context.MODE_PRIVATE);
        instaUser = misPreferencias.getString("User", "1");

        if(instaUser == "1")
            Toast.makeText(getContext(), "Por favor configure la cuenta de instagram", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        listaFotosPerfil.setLayoutManager(glm);
    }

    @Override
    public FotosPerfilAdapter crearAdaptador(ArrayList<FotoPerfil> fotosPerfil) {
        FotosPerfilAdapter adapter = new FotosPerfilAdapter(fotosPerfil, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(FotosPerfilAdapter adapter) {
        listaFotosPerfil.setAdapter(adapter);
    }

    @Override
    public void inicializarPerfil(String fullName, String image) {
        tvProfileFullName.setText(fullName);
        Picasso.with(getActivity())
                .load(image)
                .placeholder(R.drawable.puppy1)
                .into(imgProfileImage);
    }
}
