package com.example.myapplication.testDB;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    // CREATE
    @POST("emp")
    Call<JsonElement> insertEmp(@Body JsonObject body);

    // READ
    @GET("emp?select=*")
    Call<JsonElement> getEmp();

    // UPDATE
    @PATCH("emp?id=eq.{id}")
    Call<JsonElement> updateEmp(@Path("id") long id, @Body JsonObject body);

    // DELETE
    @DELETE("emp?id=eq.{id}")
    Call<JsonElement> deleteEmp(@Path("id") long id);
}
