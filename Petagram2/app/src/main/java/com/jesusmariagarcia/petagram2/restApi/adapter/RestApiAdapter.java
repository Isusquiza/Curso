package com.jesusmariagarcia.petagram2.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jesusmariagarcia.petagram2.restApi.ConstantesRestApi;
import com.jesusmariagarcia.petagram2.restApi.EndpointsApi;
import com.jesusmariagarcia.petagram2.restApi.deserializador.BusquedaUsuarioDeserializador;
import com.jesusmariagarcia.petagram2.restApi.deserializador.FollowersDeserializador;
import com.jesusmariagarcia.petagram2.restApi.deserializador.FotoPerfilDeserializador;
import com.jesusmariagarcia.petagram2.restApi.deserializador.ProfileDeserializador;
import com.jesusmariagarcia.petagram2.restApi.model.BusquedaResponse;
import com.jesusmariagarcia.petagram2.restApi.model.FollowersResponse;
import com.jesusmariagarcia.petagram2.restApi.model.ProfileResponse;
import com.jesusmariagarcia.petagram2.restApi.model.UserResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent () {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UserResponse.class, new FotoPerfilDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorBusquedaUsuario () {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BusquedaResponse.class, new BusquedaUsuarioDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorPerfil() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ProfileResponse.class, new ProfileDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFollowers() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FollowersResponse.class, new FollowersDeserializador());

        return gsonBuilder.create();
    }
}
