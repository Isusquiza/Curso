package com.jesusmariagarcia.petagram2.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jesusmariagarcia.petagram2.pojo.Followers;
import com.jesusmariagarcia.petagram2.restApi.JsonKeys;
import com.jesusmariagarcia.petagram2.restApi.model.FollowersResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class FollowersDeserializador implements JsonDeserializer<FollowersResponse> {
    @Override
    public FollowersResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        FollowersResponse followersResponse = gson.fromJson(json, FollowersResponse.class);
        JsonArray followersResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.FOLLOWERS_RESPONSE_ARRAY);

        followersResponse.setFollowers (deserializarFotoPerfilDeJson(followersResponseData));
        return followersResponse;
    }

    private ArrayList<Followers> deserializarFotoPerfilDeJson(JsonArray followersResponseData) {
        ArrayList<Followers> followers = new ArrayList<>();

        for(int i = 0; i < followersResponseData.size(); i++) {

            JsonObject followersResponseDataObject = followersResponseData.get(i).getAsJsonObject();

            String id =  followersResponseDataObject.get(JsonKeys.FOLLOWERS_ID).getAsString();

            Followers followerUser = new Followers();
            followerUser.setFollowerId(id);

            followers.add(followerUser);
        }

        return followers;
    }
}
