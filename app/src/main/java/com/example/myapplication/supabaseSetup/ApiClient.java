package com.example.myapplication.supabaseSetup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.myapplication.BuildConfig;

public class ApiClient {


    private static final String API_KEY = BuildConfig.SUPABASE_KEY;
    private static final String BASE_URL = BuildConfig.SUPABASE_URL;

    public static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("apikey", API_KEY)
                            .addHeader("Authorization", "Bearer " + API_KEY)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(request);
                }).build();

        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
