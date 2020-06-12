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

    RecyclerView price_recycle;
    PriceAdapter priceAdapter;

    LinearLayoutManager image_LayoutManager;
    LinearLayoutManager icon_LayoutManager;
    LinearLayoutManager price_LayoutManager;

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



        icon_LayoutManager = new LinearLayoutManager(this); //수평 레이아웃 매니저
        icon_LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //수평으로 지정 (사진 리사이클)


        image_LayoutManager = new LinearLayoutManager(this); //수평 레이아웃 매니저
        image_LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //수평으로 지정 (아이콘 리사이클)


        price_LayoutManager = new LinearLayoutManager(this); //수직 레이아웃 매니저
        price_LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//수직으로 지정 (가격 리사이클)





        image_recycle = findViewById(R.id.image_recycle); // 이미지 리사이클러뷰
        image_recycle.setLayoutManager(image_LayoutManager);  //레이아웃 매니저 지정
        imageAdapter = new ImageAdapter(); //init 어뎁터

        ArrayList<ImageItem> img_data = new ArrayList<>();
        for (int x=0; x<4; x++){
            img_data.add(new ImageItem(R.drawable.tema_0));
        }
        //임시로 사진 넣음

        imageAdapter.setData(img_data);//set data
        image_recycle.setAdapter(imageAdapter);






        icon_recycle = findViewById(R.id.icon_recycle); // 아이콘 리사이클러뷰
        icon_recycle.setLayoutManager(icon_LayoutManager); //레이아웃 매니저 지정
        iconAdapter = new IconAdapter(); //init 어뎁터

        ArrayList<IconItem> icon_data = new ArrayList<>();

        for (int x = 0; x <10; x++){
            icon_data.add(new IconItem(R.drawable.tema_4,"테스트"));
        }
        //임시로 아이콘이랑 아이콘 텍스트 넣음

        iconAdapter.setData(icon_data); //set data
        icon_recycle.setAdapter(iconAdapter);





        price_recycle = findViewById(R.id.price_recycle); // 가격 리사이클러뷰
        price_recycle.setLayoutManager(price_LayoutManager); //레이아웃 매니저 지정
        priceAdapter = new PriceAdapter(); //init 어뎁터

        ArrayList<PriceItem> price_data = new ArrayList<>();

        price_data.add(new PriceItem(R.drawable.tema_4,"테스트 구역","테스트 시설 정보","50000"));
        price_data.add(new PriceItem(R.drawable.tema_4,"테스트 구역","테스트 시설 정보","50000"));
        price_data.add(new PriceItem(R.drawable.tema_4,"테스트 구역","테스트 시설 정보","50000"));
        //임시로 사진이랑 텍스트 넣음

        priceAdapter.setData(price_data); //set data
        price_recycle.setAdapter(priceAdapter);
    }
}
