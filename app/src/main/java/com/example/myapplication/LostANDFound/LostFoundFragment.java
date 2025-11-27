package com.example.myapplication.LostANDFound;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class LostFoundFragment extends Fragment {
    RecyclerView recyclerView;
    rv_adapter_lost_found rv_adapter ;
    List<rv_model_lost_found> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = recyclerView.findViewById(R.id.rv_lost_found);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_adapter = new rv_adapter_lost_found(list,getActivity());
        recyclerView.setAdapter(rv_adapter);



        return inflater.inflate(R.layout.fragment_lost_found, container, false);
    }
    public void onFabClick() {
        startActivity(new Intent(getActivity(), add_lost_found_items.class));
    }
}