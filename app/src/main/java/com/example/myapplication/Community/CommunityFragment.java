package com.example.myapplication.Community;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragment extends Fragment {

    RecyclerView recyclerView;
    rv_adapter_community rv_adapter;
    List<rv_model_community> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_community, container, false);

        recyclerView = rootView.findViewById(R.id.rv_community);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        // ✅ HARD CODED DATA
        list = new ArrayList<>();

        list.add(new rv_model_community(
                "Anyone has DSA notes for Unit-3?",
                "01-12-2025",
                null,
                4
        ));

        list.add(new rv_model_community(
                "Sharing DBMS important interview questions.",
                "02-12-2025",
                null,
                6
        ));

        list.add(new rv_model_community(
                "Workshop on AI conducted today. Very informative!",
                "03-12-2025",
                "https://i.ibb.co/zTCTHnsW/9eb6b3cc068b.jpg",
                10
        ));

        list.add(new rv_model_community(
                "Can anyone explain JVM architecture?",
                "04-12-2025",
                null,
                3
        ));

        rv_adapter = new rv_adapter_community(list, getActivity());
        recyclerView.setAdapter(rv_adapter);

        return rootView;
    }

    // FAB Click → Add Post Screen
    public void onfabClick() {
        startActivity(new Intent(getActivity(), addPost_community.class));
    }
}
