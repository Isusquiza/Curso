package com.jesusmariagarcia.petagram2.presentador;

/**
 * Created by jesusmgarcia on 19/3/17.
 */

public interface IListaMascotasFragmentPresenter {
    //public void obtenerMascotasBaseDatos();
    public void obtenerFollowers();
    public void obtenerFollowersMediaRecent(String id);
    public void mediaSetLike(String mediaId);
    public void registerLike(String token, String userId, String mediaId);
    public void mostrarMascotasRV();
}
