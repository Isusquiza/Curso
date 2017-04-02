package com.jesusmariagarcia.petagram2.restApi.deserializador;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.jesusmariagarcia.petagram2.pojo.BusquedaUsuario;
import com.jesusmariagarcia.petagram2.restApi.JsonKeys;
import com.jesusmariagarcia.petagram2.restApi.model.BusquedaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class BusquedaUsuarioDeserializador implements JsonDeserializer<BusquedaResponse> {

    @Override
    public BusquedaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        BusquedaResponse busquedaResponse = gson.fromJson(json, BusquedaResponse.class);
        JsonArray busquedaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.SEARCH_RESPONSE_ARRAY);

        busquedaResponse.setBusquedaUsuario(deserializarBusquedaUserIdDeJson(busquedaResponseData));
        return busquedaResponse;
    }

    private ArrayList<BusquedaUsuario> deserializarBusquedaUserIdDeJson(JsonArray busquedaResponseData) {
        ArrayList<BusquedaUsuario> busquedaUsuarios = new ArrayList<>();

        for(int i = 0; i < busquedaResponseData.size(); i++) {

            JsonObject busquedaResponseDataObject = busquedaResponseData.get(i).getAsJsonObject();

            String userName = busquedaResponseDataObject.get(JsonKeys.SEARCH_USERNAME).getAsString();
            String id = busquedaResponseDataObject.get(JsonKeys.SEARCH_ID).getAsString();


            BusquedaUsuario busquedaActual = new BusquedaUsuario();

            busquedaActual.setUserId(id);
            busquedaActual.setUserName(userName);

            busquedaUsuarios.add(busquedaActual);
        }

        return busquedaUsuarios;
    }
}
