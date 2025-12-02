package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Home.Home;
import com.example.myapplication.testDB.DB_test;

public class MainActivity extends AppCompatActivity {

    Button clickme, db_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        clickme = findViewById(R.id.button);
        clickme.setOnClickListener(view -> {
            startActivity(new Intent( MainActivity.this, Home.class));
        });
        db_test=findViewById(R.id.db_test);
        db_test.setOnClickListener(view -> {
            startActivity(new Intent( MainActivity.this, DB_test.class));
        });


    }
}