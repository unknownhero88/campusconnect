package com.example.myapplication.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Community.CommunityFragment;
import com.example.myapplication.DigitalNoticeBoard.NoticeFragment;
import com.example.myapplication.LostANDFound.LostFoundFragment;
import com.example.myapplication.R;
import com.example.myapplication.StudyMaterial.StudyFragment;
import com.example.myapplication.authActivity.Login;
import com.example.myapplication.authActivity.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Home extends AppCompatActivity {
    BottomNavigationView bottomNav;
    com.google.android.material.floatingactionbutton.FloatingActionButton fab;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManager sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, Login.class));
            finish();
        }

        setContentView(R.layout.activity_home);
        bottomNav = findViewById(R.id.bottomNav);
        fab = findViewById(R.id.fab);
        loadFragment(new HomeFragment());
        bottomNav.setOnItemSelectedListener(item -> {
            androidx.fragment.app.Fragment fragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.nav_lost_found) {
                fragment = new LostFoundFragment();
                fab.setVisibility(View.VISIBLE);
                } else if (itemId == R.id.nav_notice) {
                fragment = new NoticeFragment();
                fab.setVisibility(View.GONE);
                } else if (itemId == R.id.nav_home) {
                fragment = new HomeFragment();
                fab.setVisibility(View.GONE);
                } else if (itemId == R.id.nav_study) {
                fragment = new StudyFragment();
                fab.setVisibility(View.GONE);
                } else if (itemId == R.id.nav_community) {
                fragment = new CommunityFragment();
                fab.setVisibility(View.VISIBLE);
                }
            return loadFragment(fragment);
        });
        fab.setOnClickListener(v -> {
            androidx.fragment.app.Fragment fragment = getSupportFragmentManager()
                    .findFragmentById(R.id.frame_container);

            if (fragment instanceof LostFoundFragment) {
                ((LostFoundFragment) fragment).onFabClick();
            }
            if(fragment instanceof CommunityFragment)
            {
                ((CommunityFragment) fragment).onfabClick();
            }
        });
    }


    private boolean loadFragment(androidx.fragment.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
        return true;
    }
}
