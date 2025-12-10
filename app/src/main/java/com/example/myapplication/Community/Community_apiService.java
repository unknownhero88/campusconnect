package com.example.myapplication.Community;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Community_apiService {

    @POST("Community")
    Call<JsonElement> insertEmp(@Body JsonObject body);

    @GET("Community?select=*")
    Call<JsonElement> getEmp();

    @PATCH("Community")
    Call<JsonElement> updateEmp(
            @Query("id") String idCondition,
            @Body JsonObject body
    );

    @DELETE("Community")
    Call<JsonElement> deleteEmp(
            @Query("id") String idCondition
    );
}
