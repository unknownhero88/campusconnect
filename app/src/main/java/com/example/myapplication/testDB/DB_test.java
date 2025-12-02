package com.example.myapplication.testDB;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DB_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_db_test);

        ApiService api = ApiClient.getClient().create(ApiService.class);


        JsonObject obj = new JsonObject();
        obj.addProperty("name", "Rishi");
        obj.addProperty("email", "rishi@gmail.com");

        api.insertEmp(obj).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d("SUPABASE", "Inserted: " + response.body());
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("SUPABASE", t.getMessage());
            }
        });
        api.getEmp().enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d("SUPABASE", "Data: " + response.body());
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("SUPABASE", t.getMessage());
            }
        });
        JsonObject obj1 = new JsonObject();
        obj1.addProperty("name", "Updated Name");
        obj1.addProperty("email", "updated@gmail.com");

        api.updateEmp(1, obj1).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d("SUPABASE", "Updated: " + response.body());
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("SUPABASE", t.getMessage());
            }
        });
        api.deleteEmp(1).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d("SUPABASE", "Deleted: " + response.body());
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("SUPABASE", t.getMessage());
            }
        });

    }
}