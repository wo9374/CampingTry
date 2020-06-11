package com.example.mylogin.SEARCH.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mylogin.R;

import java.util.ArrayList;

public class DetailInformation extends AppCompatActivity {

    TextView add_txt;
    TextView tel_txt;
    TextView env_txt;
    TextView the_txt;
    TextView day_txt;


    RecyclerView image_recycle;
    ImageAdapter imageAdapter;

    RecyclerView icon_recycle;
    IconAdapter iconAdapter;

    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);

        add_txt = findViewById(R.id.add_txt);
        tel_txt = findViewById(R.id.tel_txt);
        env_txt = findViewById(R.id.env_txt);
        the_txt = findViewById(R.id.the_txt);
        day_txt = findViewById(R.id.day_txt);
        // 이 찾아준 값에 디비 불러와서 setText 해주삼



        mLayoutManager = new LinearLayoutManager(this); //수평 레이아웃 매니저
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //수평으로 지정



        image_recycle = findViewById(R.id.image_recycle); // 이미지 리사이클러뷰
        image_recycle.setLayoutManager(mLayoutManager);  //레이아웃 매니저 지정
        imageAdapter = new ImageAdapter(); //init 어뎁터

        ArrayList<ImageItem> img_data = new ArrayList<>();
        img_data.add(new ImageItem(R.drawable.tema_0));

        imageAdapter.setData(img_data);//set data
        image_recycle.setAdapter(imageAdapter);



        icon_recycle = findViewById(R.id.icon_recycle); // 아이콘 리사이클러뷰
        icon_recycle.setLayoutManager(mLayoutManager); //레이아웃 매니저 지정
        iconAdapter = new IconAdapter(); //init 어뎁터

        ArrayList<IconItem> icon_data = new ArrayList<>();
        icon_data.add(new IconItem(R.drawable.tema_4,"테스트"));

        iconAdapter.setData(icon_data); //set data
        icon_recycle.setAdapter(iconAdapter);
    }
}
