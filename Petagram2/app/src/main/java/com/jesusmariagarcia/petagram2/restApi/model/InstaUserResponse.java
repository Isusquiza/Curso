package com.jesusmariagarcia.petagram2.restApi.model;

/**
 * Created by jesusmgarcia on 14/4/17.
 */

public class InstaUserResponse {
    private String id;
    private String token;
    private String user;


    public InstaUserResponse() {
    }

    public InstaUserResponse(String id, String token, String user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String instaUser) {
        this.user = user;
    }
}
