package com.jesusmariagarcia.petagram2.pojo;

/**
 * Created by jesusmgarcia on 12/3/17.
 */

public class FotoPerfil {

    private int foto;
    private int rating;

    public FotoPerfil(int foto, int rating) {
        this.foto = foto;
        this.rating = rating;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
