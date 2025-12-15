package com.example.myapplication.StudyMaterial;

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


public class StudyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_study, container, false);
        List<StudyMaterialModel> list = new ArrayList<>();

        list.add(new StudyMaterialModel(
                "Operating System Notes", "PDF • 2.3 MB"));
        list.add(new StudyMaterialModel(
                "DBMS Handwritten Notes", "PDF • 1.8 MB"));
        list.add(new StudyMaterialModel(
                "Java Interview Questions", "PDF • 900 KB"));
        list.add(new StudyMaterialModel(
                "Computer Networks PPT", "PPT • 4.5 MB"));
        list.add(new StudyMaterialModel(
                "Data Structures Cheatsheet", "PDF • 600 KB"));

        RecyclerView rv = view.findViewById(R.id.rv_study_material);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new StudyMaterialAdapter(list));

        return view;
    }
}