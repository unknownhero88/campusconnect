package com.example.myapplication.LostANDFound;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface lostfound_apiService {

    @POST("LostFound")
    Call<JsonElement> insertEmp(@Body JsonObject body);

    // READ
    @GET("LostFound?select=*")
    Call<JsonElement> getEmp();

    @PATCH("LostFound")
    Call<JsonElement> updateEmp(
            @Query("id") String idCondition,
            @Body JsonObject body
    );

    @DELETE("LostFound")
    Call<JsonElement> deleteEmp(
            @Query("id") String idCondition
    );

}
