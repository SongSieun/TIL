package com.sesong.imageretrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitService {
    public static final String BASE_URL = "https://combeenation.herokuapp.com";

    @FormUrlEncoded
    @POST("/combination")
    Call<JsonObject> combinations(
            @Header("token") String token,
            @Field("name") String name,
            @Field("image") String image,
            @Field("combination") String combination,
            @Field("type") String type);
}
