package com.jesusmariagarcia.petagram2.pojo;

/**
 * Created by jesusmgarcia on 4/3/17.
 */

public class Mascota {

    private int id;
    private String nombreMascota;
    private int foto;
    private int rating;

    public Mascota(String nombreMascota, int foto) {
        this.nombreMascota = nombreMascota;
        this.foto = foto;
        this.rating = 0;
    }

    public Mascota() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

}
