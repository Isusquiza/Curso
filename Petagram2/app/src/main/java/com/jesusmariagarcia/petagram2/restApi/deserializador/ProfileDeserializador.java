package com.jesusmariagarcia.petagram2.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jesusmariagarcia.petagram2.restApi.JsonKeys;
import com.jesusmariagarcia.petagram2.restApi.model.ProfileResponse;

import java.lang.reflect.Type;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class ProfileDeserializador implements JsonDeserializer<ProfileResponse> {
    @Override
    public ProfileResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ProfileResponse profileResponse = gson.fromJson(json, ProfileResponse.class);
        JsonObject profileResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.PROFILE_RESPONSE_ARRAY);

        profileResponse.setFullName(deserializarProfileFullNameDeJson(profileResponseData));
        profileResponse.setProfileImage(deserializarProfileImage(profileResponseData));

        return profileResponse;
    }

    private String deserializarProfileFullNameDeJson(JsonObject profileResponseData) {
        return profileResponseData.get(JsonKeys.PROFILE_FULLNAME).getAsString();
    }

    private String deserializarProfileImage(JsonObject profileResponseData) {
        return profileResponseData.get(JsonKeys.PROFILE_PICTURE).getAsString();
    }
}
