package com.example.myapplication.LostANDFound;

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

public class LostFoundFragment extends Fragment {

    RecyclerView recyclerView;
    rv_adapter_lost_found rv_adapter;
    List<rv_model_lost_found> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_lost_found, container, false);

        recyclerView = rootView.findViewById(R.id.rv_lost_found);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // ✅ HARD CODED DATA
        list = new ArrayList<>();

        list.add(new rv_model_lost_found(
                "Lost",
                "Mobile Phone",
                "Electronic",
                "11-12-2025",
                "7880103732",
                "https://i.ibb.co/zTCTHnsW/9eb6b3cc068b.jpg"
        ));

        list.add(new rv_model_lost_found(
                "Found",
                "College ID Card",
                "Document",
                "12-12-2025",
                "9876543210",
                null
        ));

        list.add(new rv_model_lost_found(
                "Lost",
                "Bluetooth Earbuds",
                "Electronic",
                "13-12-2025",
                "9123456789",
                null
        ));

        list.add(new rv_model_lost_found(
                "Found",
                "Water Bottle",
                "Personal Item",
                "14-12-2025",
                "9001122334",
                "https://i.ibb.co/zTCTHnsW/9eb6b3cc068b.jpg"
        ));

        rv_adapter = new rv_adapter_lost_found(list, getActivity());
        recyclerView.setAdapter(rv_adapter);

        return rootView;
    }

    // Floating Action Button → Add Lost/Found Item
    public void onFabClick() {
        startActivity(new Intent(getActivity(), add_lost_found_items.class));
    }
}
