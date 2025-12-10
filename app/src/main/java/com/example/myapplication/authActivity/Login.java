package com.example.myapplication.authActivity;

import static com.example.myapplication.BuildConfig.SUPABASE_AUTH_URL;
import static com.example.myapplication.BuildConfig.SUPABASE_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Home.Home;
import com.example.myapplication.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    
    EditText email, pwd;
    Button login;
    TextView signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailStr = email.getText().toString().trim();
                String passwordStr = pwd.getText().toString().trim();
                if (emailStr.isEmpty() || passwordStr.isEmpty()) {
                    Toast.makeText(Login.this, "Email and Password cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Log.d("LOGIN_DATA", "Email: " + emailStr + " | Pass: " + passwordStr);

                    LoginEmailPassword(emailStr, passwordStr);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.getContext().getApplicationContext(),signup.class));
            }
        });
    }
    private void LoginEmailPassword(String emailStr, String passwordStr) {
        OkHttpClient client = new OkHttpClient();

        JsonObject obj = new JsonObject();
        obj.addProperty("email", emailStr);
        obj.addProperty("password", passwordStr);

        RequestBody body = RequestBody.create(
                obj.toString(),
                MediaType.parse("application/json")
        );
        Request requset = new Request.Builder()
                .url(SUPABASE_AUTH_URL + "/token?grant_type=password")
                .addHeader("apikey", SUPABASE_KEY)
                .addHeader("Content-type","application/json")
                .post(body)
                .build();
        client.newCall(requset).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = response.body().string();

                    Log.d("SUPABASE AUTH", "onResponse: " + res);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(res);
                        String accessToken = jsonObject.getString("access_token");
                        String refreshToken = jsonObject.getString("refresh_token");
                        SessionManager sessionManager = new SessionManager(Login.this);
                        sessionManager.saveSession(accessToken, refreshToken);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    runOnUiThread(() -> {
                        Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    });
                    startActivity(new Intent(Login.this, Home.class));
                } else {
                    String error = response.body().string();
                    Log.e("SUPABASE AUTH", "Login failed: " + error);
                    if(error.contains("Invalid login credentials"))
                    {
                        runOnUiThread(() ->
                                Toast.makeText(Login.this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show());

                    }else {
                        runOnUiThread(() ->
                                Toast.makeText(Login.this, "Login Failed: " + error, Toast.LENGTH_LONG).show()
                        );
                    }
                }
            }


            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("SUPABASE AUTH", "onFailure: " + e.getMessage());
            }
        });
    }
}