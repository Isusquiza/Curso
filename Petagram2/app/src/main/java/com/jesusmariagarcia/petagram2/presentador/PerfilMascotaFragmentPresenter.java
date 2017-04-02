package com.jesusmariagarcia.petagram2.presentador;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jesusmariagarcia.petagram2.fragment.IPerfilMascotaFragment;
import com.jesusmariagarcia.petagram2.pojo.BusquedaUsuario;
import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;
import com.jesusmariagarcia.petagram2.restApi.ConstantesRestApi;
import com.jesusmariagarcia.petagram2.restApi.EndpointsApi;
import com.jesusmariagarcia.petagram2.restApi.adapter.RestApiAdapter;
import com.jesusmariagarcia.petagram2.restApi.model.BusquedaResponse;
import com.jesusmariagarcia.petagram2.restApi.model.ProfileResponse;
import com.jesusmariagarcia.petagram2.restApi.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class PerfilMascotaFragmentPresenter implements IPerfilMascotaFragmentPresenter {

    private IPerfilMascotaFragment iPerfilMascotasFragment;
    private Context context;
    private ArrayList<FotoPerfil> fotosPerfil;
    private String profileFullName;
    private String profileImage;
    private ArrayList<BusquedaUsuario> busquedaUsuarios;
    private String instaUser;
    private BusquedaUsuario usuario;

    public PerfilMascotaFragmentPresenter(IPerfilMascotaFragment iPerfilMascotasFragment, Context context, String instaUser) {
        this.iPerfilMascotasFragment = iPerfilMascotasFragment;
        this.context = context;
        this.instaUser = instaUser;

        usuario = new BusquedaUsuario();

        buscarUserId();
    }

    @Override
    public void obtenerPerfilUsuario() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonPerfil = restApiAdapter.construyeGsonDeserializadorPerfil();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonPerfil);

        String url = String.format(ConstantesRestApi.URL_GET_PROFILE, usuario.getUserId());
        Call<ProfileResponse> profileResponseCall = endpointsApi.getProfile(url);

        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                ProfileResponse profileResponse = response.body();

                profileFullName = profileResponse.getFullName();
                profileImage = profileResponse.getProfileImage();
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void obtenerPerfilMascotaMediosRecientes() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);

        String url = String.format(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER, usuario.getUserId());
        Call<UserResponse> userResponseCall = endpointsApi.getRecentMedia(url);

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                UserResponse userResponse = response.body();
                fotosPerfil = userResponse.getFotosPerfil();
                mostrarPerfilMascotaRV();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void buscarUserId() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonBuscarUserId = restApiAdapter.construyeGsonDeserializadorBusquedaUsuario();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonBuscarUserId);

        String url = String.format(ConstantesRestApi.URL_SEARCH_USERS, instaUser);
        Call<BusquedaResponse> busquedaResponseCall = endpointsApi.getSearchUsers(url);

        busquedaResponseCall.enqueue (new Callback<BusquedaResponse>() {
            @Override
            public void onResponse(Call<BusquedaResponse> call, Response<BusquedaResponse> response) {

                BusquedaResponse busquedaResponse = response.body();
                busquedaUsuarios = busquedaResponse.getBusquedaUsuario();

                for(int i = 0; i < busquedaUsuarios.size(); i++) {
                    String tempUserName = busquedaUsuarios.get(i).getUserName();

                    if( tempUserName.equals(instaUser)) {
                        usuario.setUserName(tempUserName);
                        usuario.setUserId(busquedaUsuarios.get(i).getUserId());

                        obtenerPerfilUsuario();
                        obtenerPerfilMascotaMediosRecientes();
                    }

                }

            }

            @Override
            public void onFailure(Call<BusquedaResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void mostrarPerfilMascotaRV() {
        iPerfilMascotasFragment.inicializarPerfil(profileFullName, profileImage);
        iPerfilMascotasFragment.inicializarAdaptadorRV(iPerfilMascotasFragment.crearAdaptador(fotosPerfil));
        iPerfilMascotasFragment.generarGridLayout();

    }
}
