package com.jesusmariagarcia.petagram2.pojo;

import com.jesusmariagarcia.petagram2.presentador.IListaMascotasFragmentPresenter;

/**
 * Created by jesusmgarcia on 12/3/17.
 */

public class FotoPerfil {

    private String userName;
    private String mediaId;
    private String fullName;
    private String urlFoto;
    private int rating = 0;

    public FotoPerfil() {
    }

    public IListaMascotasFragmentPresenter getiListaMascotasFragmentPresenter() {
        return iListaMascotasFragmentPresenter;
    }

    public void setiListaMascotasFragmentPresenter(IListaMascotasFragmentPresenter iListaMascotasFragmentPresenter) {
        this.iListaMascotasFragmentPresenter = iListaMascotasFragmentPresenter;
    }

    private IListaMascotasFragmentPresenter iListaMascotasFragmentPresenter;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }






}
