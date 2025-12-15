package com.example.myapplication.DigitalNoticeBoard;

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

public class NoticeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        RecyclerView rv = view.findViewById(R.id.rv_notice);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<NoticeModel> noticeList = new ArrayList<>();

        noticeList.add(new NoticeModel(
                "Mid-Semester Exams",
                "Mid-semester exams will start from 20th September. Please check the timetable.",
                "15 Sep 2025"));

        noticeList.add(new NoticeModel(
                "Holiday Notice",
                "College will remain closed on 2nd October on account of Gandhi Jayanti.",
                "28 Sep 2025"));

        noticeList.add(new NoticeModel(
                "Workshop on AI",
                "A hands-on workshop on Artificial Intelligence will be conducted in Seminar Hall.",
                "10 Oct 2025"));

        noticeList.add(new NoticeModel(
                "Fee Submission Deadline",
                "Last date for fee submission is 30th September. Late fees will be applicable.",
                "18 Sep 2025"));

        NoticeAdapter adapter = new NoticeAdapter(noticeList);
        rv.setAdapter(adapter);


        return view;
    }
}