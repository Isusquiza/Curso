package com.jesusmariagarcia.petagram2.fragment;

import com.jesusmariagarcia.petagram2.adapter.MascotaAdapter;
import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;
import com.jesusmariagarcia.petagram2.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 19/3/17.
 */

public interface IListaMascotasFragment {
    public void generarLinearLayout();
    public MascotaAdapter crearAdaptador(ArrayList<FotoPerfil> mascotas);
    public void inicializarAdaptadorRV(MascotaAdapter adapter);
}
