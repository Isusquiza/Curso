package com.jesusmariagarcia.petagram2.pojo;

/**
 * Created by jesusmgarcia on 12/3/17.
 */

public class FotoPerfil {

    private String id;
    private String fullName;
    private String urlFoto;
    private int rating = 0;

    public FotoPerfil() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFoto() {
        return urlFoto;
    }

    public void setFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }






}
