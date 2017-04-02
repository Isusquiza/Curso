package com.jesusmariagarcia.petagram2.presentador;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jesusmariagarcia.petagram2.db.ConstructorMascotas;
import com.jesusmariagarcia.petagram2.fragment.IListaMascotasFragment;
import com.jesusmariagarcia.petagram2.pojo.Followers;
import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;
import com.jesusmariagarcia.petagram2.pojo.Mascota;
import com.jesusmariagarcia.petagram2.restApi.ConstantesRestApi;
import com.jesusmariagarcia.petagram2.restApi.EndpointsApi;
import com.jesusmariagarcia.petagram2.restApi.adapter.RestApiAdapter;
import com.jesusmariagarcia.petagram2.restApi.model.FollowersResponse;
import com.jesusmariagarcia.petagram2.restApi.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jesusmgarcia on 19/3/17.
 */

public class ListaMascotasFragmentPresenter implements IListaMascotasFragmentPresenter {

    private IListaMascotasFragment iListaMascotasFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<FotoPerfil> fotosPerfilUsuario;
    private ArrayList<FotoPerfil> fotosPerfilTotal;
    private ArrayList<Followers> followers;

    public ListaMascotasFragmentPresenter(IListaMascotasFragment iListaMascotasFragment, Context context) {
        this.iListaMascotasFragment = iListaMascotasFragment;
        this.context = context;

        //obtenerMascotasBaseDatos();
        fotosPerfilTotal = new ArrayList<>();

        obtenerFollowers();
    }

    /*@Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }*/


    @Override
    public void obtenerFollowers() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonFollowers = restApiAdapter.construyeGsonDeserializadorFollowers();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollowers);

        String url = ConstantesRestApi.URL_GET_FOLLOWERS;
        Call<FollowersResponse> followersResponseCall = endpointsApi.getFollowers(url);

        followersResponseCall.enqueue(new Callback<FollowersResponse>() {
            @Override
            public void onResponse(Call<FollowersResponse> call, Response<FollowersResponse> response) {

                FollowersResponse followersResponse = response.body();
                followers = followersResponse.getFollowers();

                for(int i = 0; i < followers.size(); i++) {
                    String id = followers.get(i).getFollowerId();

                    obtenerFollowersMediaRecent(id);
                }
            }

            @Override
            public void onFailure(Call<FollowersResponse> call, Throwable t) {

                Toast.makeText(context, "Falló la conexión", Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void obtenerFollowersMediaRecent(String id) {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);

        String url = String.format(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER, id);
        Call<UserResponse> userResponseCall = endpointsApi.getRecentMedia(url);

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                UserResponse userResponse = response.body();
                fotosPerfilUsuario = userResponse.getFotosPerfil();

                // Añadir fotos de cada perfil a mascotas
                fotosPerfilTotal.addAll(fotosPerfilUsuario);
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iListaMascotasFragment.inicializarAdaptadorRV(iListaMascotasFragment.crearAdaptador(fotosPerfilTotal));
        iListaMascotasFragment.generarLinearLayout();

    }
}
