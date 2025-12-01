package com.example.myapplication.Community;

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

public class CommunityFragment extends Fragment {

    RecyclerView recyclerView;
    rv_adapter_community rv_adapter ;
    List<rv_model_community> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        recyclerView = rootView.findViewById(R.id.rv_community);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.add(new rv_model_community("paragr","1-12-25","https://i.ibb.co/zTCTHnsW/9eb6b3cc068b.jpg",4));
        rv_adapter = new rv_adapter_community(list,getActivity());
        recyclerView.setAdapter(rv_adapter);

        return rootView ;
    }

    public void onfabClick() {
        startActivity(new Intent(getActivity(), addPost_community.class));
    }
}