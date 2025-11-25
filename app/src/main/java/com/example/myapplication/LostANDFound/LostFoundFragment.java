package com.example.myapplication.LostANDFound;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;


public class LostFoundFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_lost_found, container, false);
    }
    public void onFabClick() {
        startActivity(new Intent(getActivity(), add_lost_found_items.class));
    }
}