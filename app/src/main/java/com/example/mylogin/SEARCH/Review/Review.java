package com.example.mylogin.SEARCH.Review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mylogin.R;

import java.util.ArrayList;

public class Review extends AppCompatActivity {

    RecyclerView review_recycle;
    ReviewAdapter reviewAdapter;
    LinearLayoutManager review_LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Intent intent = getIntent();
        String code = intent.getExtras().getString("code"); //코드 불러옴


        review_recycle = findViewById(R.id.review_recycle);

        review_LayoutManager = new LinearLayoutManager(this); //수직 레이아웃 매니저
        review_LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//수직으로 지정
        review_recycle.setLayoutManager(review_LayoutManager); //레이아웃 매니저 지정

        reviewAdapter = new ReviewAdapter(); //init 어뎁터

        ArrayList<ReviewItem> review_data = new ArrayList<>();

        for(int x=0; x<3;x++){
            review_data.add(new ReviewItem(4,"2020-06-18","이재범","주옥같다"));
        }
        //임시로 사진이랑 텍스트 넣음

        reviewAdapter.setData(review_data); //set data
        review_recycle.setAdapter(reviewAdapter);
    }
}
