package com.example.mylogin.SNS;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

public class Account extends Fragment {
    private View view;

    Bitmap img;
    Drawable drawable;

    RecyclerView mRecyclerView = null;
    AccountAdapter mAdapter = null;
    ArrayList<AccountItem> mList = new ArrayList<AccountItem>();

    public static String userid, nic; //아이디, 닉네임

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sns_account, container, false);

        if (getArguments() != null) {
            userid = getArguments().getString("userid");
            nic = getArguments().getString("nic");
        }

        mList.clear();

        drawable = getResources().getDrawable(R.drawable.tema_4);
        img = ((BitmapDrawable)drawable).getBitmap();
        addItem("이재범","2020-06-28","내 게시물 임시 테스트",img,"5","0","0");


        mRecyclerView = view.findViewById(R.id.account_recycle);
        mAdapter = new AccountAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        return view;
    }


    void addItem(String username, String time, String content, Bitmap image, String like, String comment, String snscode){
        AccountItem item = new AccountItem();

        item.setUsername(username);
        item.setTime(time);
        item.setContent(content);
        item.setImage(image);
        item.setLike(like);
        item.setComment(comment);
        item.setSnscode(snscode);

        mList.add(item);
    }
}