package com.example.mylogin.SNS.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.mylogin.R;

import java.util.ArrayList;

public class Comment extends AppCompatActivity {

    RecyclerView mRecyclerView = null; //댓글 리사이클러뷰
    CommentAdapter mAdapter = null;
    ArrayList<CommentItem> mList = new ArrayList<CommentItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // 사이즈 조절
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        // 윈도우 매니저 객체 얻어오고 디스플레이 객체 얻어오기

        int width = (int) (display.getWidth() * 0.9); // 얻어온 화면의 폭의 90프로만큼 width에 지정
        int height = (int) (display.getHeight() * 0.9); // 얻어온 화면의 높이의 70프로 만큼 height에 지정

        getWindow().getAttributes().width = width; //댓글 레이아웃의 폭을 width로 지정
        getWindow().getAttributes().height = height; //댓글 레이아웃의 폭을 height 지정
        getWindow().setGravity(Gravity.CENTER); //댓글 레이아웃 센터지정





        Intent intent = getIntent();
        String snscode = intent.getExtras().getString("snscode"); //코드 불러옴



        addItem("상원이", "와아아아웅!!!", "2020-06-26");

        mRecyclerView = findViewById(R.id.comment_recycle);
        mAdapter = new CommentAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

    }

    void addItem(String username, String comment, String time){
        CommentItem item = new CommentItem();

        item.setUsername(username);
        item.setContent(comment);
        item.setTime(time);

        mList.add(item);
    }
}
