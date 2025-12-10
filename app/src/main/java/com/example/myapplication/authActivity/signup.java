package com.example.myapplication.authActivity;


import static com.example.myapplication.BuildConfig.SUPABASE_KEY;
import static com.example.myapplication.BuildConfig.SUPABASE_AUTH_URL;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class signup extends AppCompatActivity {

    EditText email, pwd, re_pwd,name;
    Button signup;
    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        re_pwd = findViewById(R.id.re_pwd);
        name=findViewById(R.id.name);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);



        signup.setOnClickListener(v -> {

            String emailStr = email.getText().toString().trim();
            String passwordStr = pwd.getText().toString().trim();
            String rePasswordStr = re_pwd.getText().toString().trim();
            String nameStr = name.getText().toString().trim();


            if (!passwordStr.equals(rePasswordStr)) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (emailStr.isEmpty() || passwordStr.isEmpty()) {
                Toast.makeText(this, "Email and Password cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (nameStr.isEmpty()) {
                Toast.makeText(this, "Name cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            CreateAccount(emailStr, passwordStr,nameStr);
        });

        login.setOnClickListener(v -> {
            startActivity(new Intent(signup.this, Login.class));
        });
    }

    private void CreateAccount(String email, String password,String name) {

        OkHttpClient client = new OkHttpClient();

        JsonObject obj = new JsonObject();
        obj.addProperty("email", email);
        obj.addProperty("password", password);
        obj.addProperty("name", name);


        RequestBody body = RequestBody.create(
                obj.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(SUPABASE_AUTH_URL + "/signup")
                .addHeader("apikey", SUPABASE_KEY)
                .addHeader("Authorization", "Bearer " + SUPABASE_KEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(signup.this, "Network Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String res = response.body().string();
                if(response.isSuccessful()) {
                    runOnUiThread(() ->
                            Toast.makeText(signup.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show()
                    );
                    Log.d("SUPABASE AUTH", "onResponse: "+res);
                    startActivity(new Intent(signup.this, Login.class));
                } else{
                    runOnUiThread(() ->
                            Toast.makeText(signup.this, "Error: " + res, Toast.LENGTH_SHORT).show());
                    Log.d("SUPABASE AUTH", "onFail: "+res);
                }
            }
        });
    }
}
