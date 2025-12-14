package com.example.myapplication.startup;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;

public class OnboardingActivity extends AppCompatActivity {

    public ViewPager2 viewPager; // IMPORTANT: public for adapter access

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new OnboardingAdapter(this));

        // Optional smooth animation
        viewPager.setPageTransformer((page, position) -> {
            page.setAlpha(0.3f + (1 - Math.abs(position)) * 0.7f);
            page.setScaleY(0.85f + (1 - Math.abs(position)) * 0.15f);
        });
    }
}
