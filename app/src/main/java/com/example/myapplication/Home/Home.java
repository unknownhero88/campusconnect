package com.example.myapplication.Home;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Community.CommunityFragment;
import com.example.myapplication.DigitalNoticeBoard.NoticeFragment;
import com.example.myapplication.LostANDFound.LostFoundFragment;
import com.example.myapplication.R;
import com.example.myapplication.StudyMaterial.StudyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNav;
    com.google.android.material.floatingactionbutton.FloatingActionButton fab;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                } else if (itemId == R.id.nav_home) {
                fragment = new HomeFragment();
                } else if (itemId == R.id.nav_study) {
                fragment = new StudyFragment();
                } else if (itemId == R.id.nav_community) {
                fragment = new CommunityFragment();
                }
            if (fragment != null) {
            }
            return loadFragment(fragment);
        });
        fab.setOnClickListener(v -> {
            androidx.fragment.app.Fragment fragment = getSupportFragmentManager()
                    .findFragmentById(R.id.frame_container);

            if (fragment instanceof LostFoundFragment) {
                ((LostFoundFragment) fragment).onFabClick();
            }
        });
    }


    private boolean loadFragment(androidx.fragment.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
        return true;
    }
}
