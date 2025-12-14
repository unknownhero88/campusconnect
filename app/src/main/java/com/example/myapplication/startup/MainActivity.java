package com.example.myapplication.startup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Home.Home;
import com.example.myapplication.R;
import com.example.myapplication.authActivity.Login;
import com.example.myapplication.authActivity.SessionManager;
import com.example.myapplication.authActivity.signup;
import com.example.myapplication.testDB.DB_test;

public class MainActivity extends AppCompatActivity {

    Button clickme, db_test, signup, login, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        clickme = findViewById(R.id.button);
        db_test = findViewById(R.id.db_test);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        logout = findViewById(R.id.logout);

        clickme.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Home.class)));

        db_test.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, DB_test.class)));

        signup.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, signup.class)));

        login.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Login.class)));

        logout.setOnClickListener(v -> logout());
    }

    private void logout() {
        SessionManager sessionManager = new SessionManager(this);
        sessionManager.logout();

        // Clear UID
        getSharedPreferences("USER_DATA", MODE_PRIVATE)
                .edit()
                .clear()
                .apply();

        // Redirect to Login & clear back stack
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
