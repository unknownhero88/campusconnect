package com.example.myapplication.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Community.rv_adapter_community;
import com.example.myapplication.Community.rv_model_community;
import com.example.myapplication.DigitalNoticeBoard.NoticeAdapter;
import com.example.myapplication.DigitalNoticeBoard.NoticeModel;
import com.example.myapplication.R;
import com.example.myapplication.StudyMaterial.StudyMaterialAdapter;
import com.example.myapplication.StudyMaterial.StudyMaterialModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // RecyclerViews
        RecyclerView rvNotice = view.findViewById(R.id.rv_home_notice);
        RecyclerView rvStudy = view.findViewById(R.id.rv_home_study);
        RecyclerView rvCommunity = view.findViewById(R.id.rv_home_community);

        rvNotice.setLayoutManager(new LinearLayoutManager(getContext()));
        rvStudy.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCommunity.setLayoutManager(new LinearLayoutManager(getContext()));

        /* ---------------- NOTICE LIST (LOCAL) ---------------- */
        List<NoticeModel> noticeList = new ArrayList<>();
        noticeList.add(new NoticeModel(
                "Mid-Semester Exams",
                "Mid-sem exams will start from 20th September.",
                "15 Sep 2025"));
        noticeList.add(new NoticeModel(
                "Holiday Notice",
                "College will remain closed on 2nd October.",
                "28 Sep 2025"));

        /* ---------------- STUDY MATERIAL LIST (LOCAL) ---------------- */
        List<StudyMaterialModel> studyList = new ArrayList<>();
        studyList.add(new StudyMaterialModel(
                "Operating System Notes",
                "PDF • 2.3 MB"));
        studyList.add(new StudyMaterialModel(
                "DBMS Handwritten Notes",
                "PDF • 1.8 MB"));

        /* ---------------- COMMUNITY POSTS LIST (LOCAL) ---------------- */
        List<rv_model_community> communityList = new ArrayList<>();
        communityList.add(new rv_model_community(
                "Anyone has DSA notes for Unit-3?",
                "12 Sep 2025"));
        communityList.add(new rv_model_community(
                "Lost my ID card near library.",
                "14 Sep 2025"));

        // Set adapters
        rvNotice.setAdapter(new NoticeAdapter(noticeList));
        rvStudy.setAdapter(new StudyMaterialAdapter(studyList));
        rvCommunity.setAdapter(new rv_adapter_community(communityList, getContext()));

        return view;
    }
}
