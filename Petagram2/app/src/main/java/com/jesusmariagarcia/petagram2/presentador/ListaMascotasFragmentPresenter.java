package com.jesusmariagarcia.petagram2.presentador;

import android.content.Context;

import com.jesusmariagarcia.petagram2.db.ConstructorMascotas;
import com.jesusmariagarcia.petagram2.fragment.IListaMascotasFragment;
import com.jesusmariagarcia.petagram2.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 19/3/17.
 */

public class ListaMascotasFragmentPresenter implements IListaMascotasFragmentPresenter {

    private IListaMascotasFragment iListaMascotasFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public ListaMascotasFragmentPresenter(IListaMascotasFragment iListaMascotasFragment, Context context) {
        this.iListaMascotasFragment = iListaMascotasFragment;
        this.context = context;

        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iListaMascotasFragment.inicializarAdaptadorRV(iListaMascotasFragment.crearAdaptador(mascotas));
        iListaMascotasFragment.generarLinearLayout();

    }
}
