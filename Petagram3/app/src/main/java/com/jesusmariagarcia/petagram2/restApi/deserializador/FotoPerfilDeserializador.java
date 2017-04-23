package com.jesusmariagarcia.petagram2.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;
import com.jesusmariagarcia.petagram2.restApi.JsonKeys;
import com.jesusmariagarcia.petagram2.restApi.model.UserResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class FotoPerfilDeserializador implements JsonDeserializer<UserResponse> {

    @Override
    public UserResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        UserResponse userResponse = gson.fromJson(json, UserResponse.class);
        JsonArray userResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        userResponse.setFotosPerfil(deserializarFotoPerfilDeJson(userResponseData));
        return userResponse;
    }

    private ArrayList<FotoPerfil> deserializarFotoPerfilDeJson(JsonArray userResponseData) {
        ArrayList<FotoPerfil> fotoPerfil = new ArrayList<>();

        for(int i = 0; i < userResponseData.size(); i++) {

            JsonObject userResponseDataObject = userResponseData.get(i).getAsJsonObject();
            String mediaId = userResponseDataObject.get(JsonKeys.MEDIA_ID).getAsString();

            JsonObject userJson = userResponseDataObject.getAsJsonObject(JsonKeys.USER);

            String userName =  userJson.get(JsonKeys.USER_NAME).getAsString();
            String fullName = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson = userResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResImageJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResImageJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = userResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            FotoPerfil fotoPerfilActual = new FotoPerfil();

            fotoPerfilActual.setUserName(userName);
            fotoPerfilActual.setMediaId(mediaId);
            fotoPerfilActual.setFullName(fullName);
            fotoPerfilActual.setUrlFoto(urlFoto);
            fotoPerfilActual.setRating(likes);

            fotoPerfil.add(fotoPerfilActual);
        }

        return fotoPerfil;
    }
}
