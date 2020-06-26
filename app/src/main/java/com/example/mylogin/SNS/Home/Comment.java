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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        final int snscode = Integer.parseInt(intent.getExtras().getString("snscode")); //코드 불러옴

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String responses) {
                try {
                    System.out.println(snscode + "@@@@@@@@@@@@@@@@@");
                    JSONArray jsonArray = new JSONArray(responses);
                    JSONObject jsonObjectfirst = jsonArray.getJSONObject(0);
                    boolean success = jsonObjectfirst.getBoolean("success");
                    if (success)//검색 결과 성공
                    {
                        for (int i =0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String comment = jsonObject.getString("comment");
                            String date = jsonObject.getString("date");
                            addItem(name, comment, date);
                        }
                        mAdapter.notifyDataSetChanged(); //새로고침
                    } else { //검색 결과 없음
                        mAdapter.notifyDataSetChanged();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //실제 서버로 Volley를 이용해서 요청을 함.
        CommentRequest commentRequest = new CommentRequest(snscode, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Comment.this);
        queue.add(commentRequest);

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
