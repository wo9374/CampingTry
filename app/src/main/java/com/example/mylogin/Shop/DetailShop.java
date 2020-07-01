package com.example.mylogin.Shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mylogin.R;

import java.util.ArrayList;

public class DetailShop extends AppCompatActivity {
    TextView name,content;

    RecyclerView image_recycle; //이미지 수평 리사이클
    DetailShopAdapter imageAdapter;
    LinearLayoutManager image_LayoutManager;

    Bitmap img;
    Drawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shop);

        Intent intent = getIntent();
        String shopcode = intent.getStringExtra("shopcode");

        name = findViewById(R.id.name); //상품이름
        content = findViewById(R.id.content); //상품설명



        image_LayoutManager = new LinearLayoutManager(this); //수평 레이아웃 매니저
        image_LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //수평으로 지정 (아이콘 리사이클)

        image_recycle = findViewById(R.id.image_recycle);
        image_recycle.setLayoutManager(image_LayoutManager);  //레이아웃 매니저 지정
        imageAdapter = new DetailShopAdapter(); //init 어뎁터


        ArrayList<DetailShopImageItem> img_data = new ArrayList<>();

        drawable = getResources().getDrawable(R.drawable.tema_4); //기본 사진
        img = ((BitmapDrawable)drawable).getBitmap();


        img_data.clear();
        img_data.add(new DetailShopImageItem(img));
        img_data.add(new DetailShopImageItem(img));
        img_data.add(new DetailShopImageItem(img));
        img_data.add(new DetailShopImageItem(img));


        imageAdapter.setData(img_data);
        image_recycle.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
        //이미지 데이터 지정, 리사이클에 어뎁터 지정
    }
}
