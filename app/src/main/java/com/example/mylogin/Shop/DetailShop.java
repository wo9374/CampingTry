package com.example.mylogin.Shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.MainActivity;
import com.example.mylogin.R;
import com.example.mylogin.SEARCH.Detail.ImageItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DetailShop extends AppCompatActivity {
    TextView name,content,writer,tel;

    RecyclerView image_recycle; //이미지 수평 리사이클
    DetailShopAdapter imageAdapter;
    LinearLayoutManager image_LayoutManager;

    Bitmap img;
    Drawable drawable;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shop);

        Intent intent = getIntent();
        String shopcode = intent.getStringExtra("shopcode");
        String userid = intent.getStringExtra("userid");
        String url = intent.getStringExtra("url");
        String desc = intent.getStringExtra("desc");
        String named = intent.getStringExtra("name");


        name = findViewById(R.id.name); //상품이름
        content = findViewById(R.id.content); //상품설명
        writer = findViewById(R.id.writer); //작성자이름
        tel = findViewById(R.id.tel); //전화번호

        Response.Listener<String> responseListener3 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success)//검색 결과 성공
                    {
                        String nn = jsonObject.getString("name");
                        writer.setText(jsonObject.getString("name"));
                        tel.setText(jsonObject.getString("phone"));
                        System.out.println(nn);
                    } else { //검색 결과 없음

                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //실제 서버로 Volley를 이용해서 요청을 함.
        DetailRequest detailRequest = new DetailRequest(userid, responseListener3);
        RequestQueue queue3 = Volley.newRequestQueue(DetailShop.this);
        queue3.add(detailRequest);

        name.setText(named);
        content.setText(desc);



        image_LayoutManager = new LinearLayoutManager(this); //수평 레이아웃 매니저
        image_LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //수평으로 지정 (아이콘 리사이클)

        image_recycle = findViewById(R.id.image_recycle);
        image_recycle.setLayoutManager(image_LayoutManager);  //레이아웃 매니저 지정
        imageAdapter = new DetailShopAdapter(); //init 어뎁터


        ArrayList<DetailShopImageItem> img_data = new ArrayList<>();

        //drawable = getResources().getDrawable(R.drawable.tema_4); //기본 사진
        //img = ((BitmapDrawable)drawable).getBitmap();

        img_data.clear();
        final String[] imgurls = url.split(",");

        for (int x=0; x<imgurls.length; x++){
            Thread mThread = new Thread(){
                @Override
                public void run(){
                    try {
                        URL url = new URL("http://3.34.136.232:8080/image/campingjang/" + imgurls[i]);
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
            img_data.add(new DetailShopImageItem(img));
        }


        imageAdapter.setData(img_data);
        image_recycle.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
        //이미지 데이터 지정, 리사이클에 어뎁터 지정
    }
}
