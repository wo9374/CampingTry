package com.example.mylogin.SNS.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.mylogin.R;

public class Comment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        // 윈도우 매니저 객체 얻어오고 디스플레이 객체 얻어오기

        int width = (int) (display.getWidth() * 0.9); // 얻어온 화면의 폭의 90프로만큼 width에 지정
        int height = (int) (display.getHeight() * 0.9); // 얻어온 화면의 높이의 70프로 만큼 height에 지정

        getWindow().getAttributes().width = width; //댓글 레이아웃의 폭을 width로 지정
        getWindow().getAttributes().height = height; //댓글 레이아웃의 폭을 height 지정
        getWindow().setGravity(Gravity.CENTER); //댓글 레이아웃 센터지정
    }
}
