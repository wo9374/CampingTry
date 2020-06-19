package com.example.mylogin.SEARCH.Review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.R;
import com.example.mylogin.SEARCH.Detail.DetailInformation;
import com.example.mylogin.SEARCH.Detail.DetailInformationRequest;
import com.example.mylogin.SEARCH.Detail.PriceItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        int codeint = Integer.parseInt(code);

        review_recycle = findViewById(R.id.review_recycle);

        review_LayoutManager = new LinearLayoutManager(this); //수직 레이아웃 매니저
        review_LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//수직으로 지정
        review_recycle.setLayoutManager(review_LayoutManager); //레이아웃 매니저 지정

        reviewAdapter = new ReviewAdapter(); //init 어뎁터

        final ArrayList<ReviewItem> review_data = new ArrayList<>();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String responses) {
                try {
                    JSONArray jsonArray = new JSONArray(responses);
                    JSONObject jsonObjectfirst = jsonArray.getJSONObject(0);
                    boolean success = jsonObjectfirst.getBoolean("success");
                    if (success)//검색 결과 성공
                    {
                        for (int i =0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            float score = jsonObject.getInt("score");
                            String date = jsonObject.getString("date");
                            String nickname = jsonObject.getString("nickname");
                            String review = jsonObject.getString("review");

                            review_data.add(new ReviewItem(score,date,nickname,review));
                        }

                        reviewAdapter.notifyDataSetChanged(); //새로고침
                    } else { //검색 결과 없음
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        //실제 서버로 Volley를 이용해서 요청을 함.
        ReviewRequest reviewRequest = new ReviewRequest(codeint, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Review.this);
        queue.add(reviewRequest);

        reviewAdapter.setData(review_data); //set data
        review_recycle.setAdapter(reviewAdapter);
    }
}
