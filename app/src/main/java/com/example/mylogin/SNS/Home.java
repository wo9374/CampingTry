package com.example.mylogin.SNS;

import android.content.Context;
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

public class Home extends Fragment {
    private View view;
    private Context ct;

    RecyclerView mRecyclerView = null;
    HomeAdapter mAdapter= null;
    ArrayList<HomeItem> mList = new ArrayList<HomeItem>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sns_home, container, false);

        mList.clear();

        addItem("테스트 이름","2020-06-13","sns게시물 테스트",R.drawable.tema_4);

        mRecyclerView = view.findViewById(R.id.home_recycle);
        mAdapter = new HomeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        return view;
    }

    void addItem(String username, String time, String content, int image){
        HomeItem item = new HomeItem();

        item.setUsername(username);
        item.setTime(time);
        item.setContent(content);
        item.setImage(image);

        mList.add(item);
    }
}
