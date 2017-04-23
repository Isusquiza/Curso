package com.jesusmariagarcia.petagram2.restApi;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public final class ConstantesRestApi {

    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "4963499917.92bf71f.bc84d950f9044a4b9e751ea274de20a9";
    public static final String KEY_ACCESS_TOKEN = "access_token=";


    // https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/%s/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + "?" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    // https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    public static final String KEY_SEARCH_USERS = "users/search?q=";
    public static final String URL_SEARCH_USERS = KEY_SEARCH_USERS + "%s&" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    // https://api.instagram.com/v1/users/{user-id}/?access_token=ACCESS-TOKEN
    public static final String KEY_GET_PROFILE = "users/%s/";
    public static final String URL_GET_PROFILE = KEY_GET_PROFILE + "?" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    // https://api.instagram.com/v1/users/self/follows?access_token=ACCESS-TOKEN
    public static final String KEY_GET_FOLLOWERS = "users/self/follows";
    public static final String URL_GET_FOLLOWERS = KEY_GET_FOLLOWERS + "?" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    // https://api.instagram.com/v1/media/{media-id}/likes?access_token=ACCESS-TOKEN
    public static final String KEY_SET_LIKE = "media/%s/likes";
    public static final String URL_SET_LIKE = KEY_SET_LIKE + "?" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;


    // https://lit-thicket-75404.herokuapp.com/
    public static final String HEROKU_ROOT_URL = "https://lit-thicket-75404.herokuapp.com/";
    public static final String KEY_POST_REGISTRAR_USUARIO = "registrar-usuario/";
    public static final String KEY_GET_REGISTRAR_LIKE = "registrar-like/";
}
