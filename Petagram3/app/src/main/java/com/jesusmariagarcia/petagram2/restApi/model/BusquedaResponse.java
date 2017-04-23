package com.jesusmariagarcia.petagram2.restApi.model;

import com.jesusmariagarcia.petagram2.pojo.BusquedaUsuario;

import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class BusquedaResponse {

    ArrayList<BusquedaUsuario> busquedaUsuario;

    public ArrayList<BusquedaUsuario> getBusquedaUsuario() {
        return busquedaUsuario;
    }

    public void setBusquedaUsuario(ArrayList<BusquedaUsuario> busquedaUsuario) {
        this.busquedaUsuario = busquedaUsuario;
    }
}
