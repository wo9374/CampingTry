package com.example.mylogin.SEARCH.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mylogin.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DetailInformation extends AppCompatActivity implements OnMapReadyCallback {

    TextView name;

    RecyclerView image_recycle;
    ImageAdapter imageAdapter;

    RecyclerView icon_recycle;
    IconAdapter iconAdapter;

    RecyclerView price_recycle;
    PriceAdapter priceAdapter;

    LinearLayoutManager image_LayoutManager;
    LinearLayoutManager icon_LayoutManager;
    LinearLayoutManager price_LayoutManager;

    FragmentManager fragmentManager;
    MapFragment mapFragment;
    Bitmap img;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);

        Intent intent = getIntent();
        String code = intent.getExtras().getString("code"); //코드 불러옴
        final String imgurl = intent.getExtras().getString("url");

        name = findViewById(R.id.name); //캠핑장 이름
        name.setText(intent.getExtras().getString("name"));


        icon_LayoutManager = new LinearLayoutManager(this); //수평 레이아웃 매니저
        icon_LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //수평으로 지정 (사진 리사이클)


        image_LayoutManager = new LinearLayoutManager(this); //수평 레이아웃 매니저
        image_LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //수평으로 지정 (아이콘 리사이클)


        price_LayoutManager = new LinearLayoutManager(this); //수직 레이아웃 매니저
        price_LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//수직으로 지정 (가격 리사이클)


        fragmentManager = getFragmentManager(); //구글맵 설정
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.Detail_map);
        mapFragment.getMapAsync(this);





        image_recycle = findViewById(R.id.image_recycle); // 이미지 리사이클러뷰
        image_recycle.setLayoutManager(image_LayoutManager);  //레이아웃 매니저 지정
        imageAdapter = new ImageAdapter(); //init 어뎁터


        ArrayList<ImageItem> img_data = new ArrayList<>();

        Drawable drawable = getResources().getDrawable(R.drawable.tema_4);
        final String[] imgurls = imgurl.split(",");

        for (int x=0; x<imgurls.length; x++){
            Thread mThread = new Thread(){
                @Override
                public void run(){
                    try {
                        URL url = new URL("http://3.34.136.232:8080/image/" + imgurls[i]);
                        i++;
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        InputStream is = conn.getInputStream();
                        img = BitmapFactory.decodeStream(is);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            mThread.start();
            try {
                mThread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            img_data.add(new ImageItem(img));
        }
        //임시로 사진 넣음

        imageAdapter.setData(img_data);//set data
        image_recycle.setAdapter(imageAdapter);




        icon_recycle = findViewById(R.id.icon_recycle); // 아이콘 리사이클러뷰
        icon_recycle.setLayoutManager(icon_LayoutManager); //레이아웃 매니저 지정
        iconAdapter = new IconAdapter(); //init 어뎁터

        ArrayList<IconItem> icon_data = new ArrayList<>();

        Bitmap icon_img = ((BitmapDrawable)drawable).getBitmap();

        for (int x = 0; x <10; x++){
            icon_data.add(new IconItem(icon_img,"테스트"));
        }
        //임시로 아이콘이랑 아이콘 텍스트 넣음

        iconAdapter.setData(icon_data); //set data
        icon_recycle.setAdapter(iconAdapter);





        price_recycle = findViewById(R.id.price_recycle); // 가격 리사이클러뷰
        price_recycle.setLayoutManager(price_LayoutManager); //레이아웃 매니저 지정
        priceAdapter = new PriceAdapter(); //init 어뎁터

        ArrayList<PriceItem> price_data = new ArrayList<>();

        Bitmap price_img = ((BitmapDrawable)drawable).getBitmap();


        for(int x=0; x<3;x++){
            price_data.add(new PriceItem("테스트 구역","테스트 시설 정보","50000"));
        }
        //임시로 사진이랑 텍스트 넣음

        priceAdapter.setData(price_data); //set data
        price_recycle.setAdapter(priceAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {//구글맵 마커
        LatLng location = new LatLng(35.896371, 128.622029);//좌표 : 위도,경도
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("영진전문대학교");//위치 명
        markerOptions.snippet("우리학교");//부가 설명
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));//해당 좌표에 해당 수치만큼 확대
    }
}
