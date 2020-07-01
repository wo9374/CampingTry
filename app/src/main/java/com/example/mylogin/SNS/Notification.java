package com.example.mylogin.SNS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogin.R;

import java.util.ArrayList;

public class Notification extends Fragment {
    private View view;
    RecyclerView mRecyclerView = null;
    NotificationAdapter mAdapter = null;
    ArrayList<NotificationItem> mList = new ArrayList<NotificationItem>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sns_notification, container, false);
        mList.clear();

        addItem("범재","20-07-01_17:38");

        mRecyclerView = view.findViewById(R.id.notification_recycle);
        mAdapter = new NotificationAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        return view;
    }

    void addItem(String nic, String time){
        NotificationItem item = new NotificationItem();

        item.setNic(nic);
        item.setTime(time);

        mList.add(item);
    }
}