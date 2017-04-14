package com.jesusmariagarcia.petagram2.restApi;

import com.jesusmariagarcia.petagram2.restApi.model.BusquedaResponse;
import com.jesusmariagarcia.petagram2.restApi.model.FollowersResponse;
import com.jesusmariagarcia.petagram2.restApi.model.InstaUserResponse;
import com.jesusmariagarcia.petagram2.restApi.model.UserResponse;
import com.jesusmariagarcia.petagram2.restApi.model.ProfileResponse;
import com.jesusmariagarcia.petagram2.restApi.ConstantesRestApi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public interface EndpointsApi {

    @GET
    Call<UserResponse> getRecentMedia(@Url String url);

    @GET
    Call<BusquedaResponse> getSearchUsers(@Url String url);

    @GET
    Call<ProfileResponse> getProfile(@Url String url);

    @GET
    Call<FollowersResponse> getFollowers(@Url String url);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_REGISTRAR_USUARIO)
    Call<InstaUserResponse> registrarUsuario(@Field("token") String token, @Field("user") String user);

}
