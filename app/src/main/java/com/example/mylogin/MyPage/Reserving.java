package com.example.mylogin.MyPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.mylogin.R;

import java.util.ArrayList;

public class Reserving extends AppCompatActivity {

    public static String userId,nic;

    Bitmap img;
    Drawable drawable;

    RecyclerView mRecyclerView = null ;
    ReservingAdapter mAdapter = null;
    ArrayList<ReservingItem> mList =  new ArrayList<ReservingItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserving);

        Intent intent = getIntent();
        userId = intent.getStringExtra("id");
        nic = intent.getStringExtra("nic");

        drawable = getResources().getDrawable(R.drawable.tema_4);
        img = ((BitmapDrawable)drawable).getBitmap();
        addItem(img,"캠핑장제목","캠핑장설명","가격","캠핑장주소 쌸라쌸라","0","url", (float) 4);


        mRecyclerView = findViewById(R.id.review_Recycle);
        mAdapter = new ReservingAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
    }

    void addItem(Bitmap image, String title, String content, String price, String address, String code, String url, Float star){
        ReservingItem item = new ReservingItem();

        item.setImage(image);
        item.setTitle(title);
        item.setContent(content);
        item.setPrice(price);
        item.setAddress(address);
        item.setCode(code);
        item.setUrl(url);
        item.setStar(star);

        mList.add(item);
    }
}
