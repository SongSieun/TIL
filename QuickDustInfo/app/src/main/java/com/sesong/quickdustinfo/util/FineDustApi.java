package com.sesong.quickdustinfo.util;

import com.sesong.quickdustinfo.model.FineDust;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FineDustApi {
    String BASE_URL = "https://api2.sktelecom.com/";

    @Headers("appkey: [45369c1e-5c1c-4cf0-a236-b7957f681eb6]")
    // 쿼리
    @GET("weather/dust?version=1")
    Call<FineDust> getFineDust(@Query("lat") double latitude, @Query("lon") double longitude);
}
