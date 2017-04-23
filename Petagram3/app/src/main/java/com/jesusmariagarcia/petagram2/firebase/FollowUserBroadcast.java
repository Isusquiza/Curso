package com.jesusmariagarcia.petagram2.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jesusmariagarcia.petagram2.pojo.BusquedaUsuario;
import com.jesusmariagarcia.petagram2.restApi.ConstantesRestApi;
import com.jesusmariagarcia.petagram2.restApi.EndpointsApi;
import com.jesusmariagarcia.petagram2.restApi.adapter.RestApiAdapter;
import com.jesusmariagarcia.petagram2.restApi.model.BusquedaResponse;
import com.jesusmariagarcia.petagram2.restApi.model.LikeResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by jesusmgarcia on 23/4/17.
 */

public class FollowUserBroadcast extends BroadcastReceiver {

    private ArrayList<BusquedaUsuario> busquedaUsuarios;
    private Context classContext;
    private String searchUserName;
    private String foundUserId;
    private String tempFollow;
    @Override
    public void onReceive(Context context, Intent intent) {
        String FOLLOW_USER_KEY = "FOLLOW_USER";

        classContext = context;
        String accion = intent.getAction();

        if(FOLLOW_USER_KEY.equals(accion)) {
            Toast.makeText(classContext, "Follow User", Toast.LENGTH_SHORT).show();

            Bundle bundle = intent.getExtras();
            String userName = bundle.getString("username");
            searchAndFollow(userName, "follow");


        }
    }


    public void followUser(String userId, String follow) {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram2();

        String url = String.format(ConstantesRestApi.URL_FOLLOW_USER, userId);
        Call<LikeResponse> likeResponseCall =  endpointsApi.followUser(url, follow);

        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                LikeResponse likeResponse = response.body();
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {

            }
        });
    }

    public void searchAndFollow(String userName, String follow) {

        searchUserName = userName;
        tempFollow = follow;

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonBuscarUserId = restApiAdapter.construyeGsonDeserializadorBusquedaUsuario();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonBuscarUserId);

        String url = String.format(ConstantesRestApi.URL_SEARCH_USERS, searchUserName);
        Call<BusquedaResponse> busquedaResponseCall = endpointsApi.getSearchUsers(url);

        busquedaResponseCall.enqueue (new Callback<BusquedaResponse>() {
            @Override
            public void onResponse(Call<BusquedaResponse> call, Response<BusquedaResponse> response) {

                BusquedaResponse busquedaResponse = response.body();
                busquedaUsuarios = busquedaResponse.getBusquedaUsuario();

                for(int i = 0; i < busquedaUsuarios.size(); i++) {
                    String tempUserName = busquedaUsuarios.get(i).getUserName();

                    if( tempUserName.equals(searchUserName)) {

                        followUser(busquedaUsuarios.get(i).getUserId(), tempFollow);
                    }

                }

            }

            @Override
            public void onFailure(Call<BusquedaResponse> call, Throwable t) {
                Toast.makeText(classContext, "Falló la conexión", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
