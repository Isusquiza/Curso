package com.jesusmariagarcia.petagram2.restApi.model;

import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;

import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class UserResponse {

    ArrayList<FotoPerfil> fotosPerfil;

    public ArrayList<FotoPerfil> getFotosPerfil() {
        return fotosPerfil;
    }

    public void setFotosPerfil(ArrayList<FotoPerfil> fotosPerfil) {
        this.fotosPerfil = fotosPerfil;
    }

}
