package com.jesusmariagarcia.petagram2.fragment;

import com.jesusmariagarcia.petagram2.adapter.FotosPerfilAdapter;
import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;

import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public interface IPerfilMascotaFragment {
    public void generarGridLayout();
    public FotosPerfilAdapter crearAdaptador(ArrayList<FotoPerfil> fotosPerfil);
    public void inicializarAdaptadorRV(FotosPerfilAdapter adapter);
    public void inicializarPerfil(String fullName, String image);
}
