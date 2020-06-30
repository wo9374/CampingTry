package com.example.mylogin.MyPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.R;
import com.example.mylogin.SEARCH.Detail.DetailInformation;
import com.example.mylogin.SEARCH.Detail.ReviewItem;
import com.example.mylogin.SEARCH.Detail.ReviewRequest;
import com.example.mylogin.SEARCH.SearchRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Reserving extends AppCompatActivity {

    public static String userId,nic;

    Bitmap img;
    Drawable drawable;
    boolean plz = false;

    RecyclerView mRecyclerView = null ;
    ReservingAdapter mAdapter = null;
    ArrayList<ReservingItem> mList =  new ArrayList<ReservingItem>();
    private ArrayList<String>codeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserving);

        Intent intent = getIntent();
        userId = intent.getStringExtra("id");
        nic = intent.getStringExtra("nic");
        codeList = new ArrayList<>();

        drawable = getResources().getDrawable(R.drawable.tema_4);
        img = ((BitmapDrawable)drawable).getBitmap();

        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
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
                            String code = jsonObject.getString("campcode");
                            codeList.add(code);
                        }
                        for (int i = 0; i < codeList.size(); i++){
                            final Response.Listener<String> responseListener = new Response.Listener<String>() {
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
                                                String code = jsonObject.getString("code");
                                                String name = jsonObject.getString("name");
                                                String addr = jsonObject.getString("addr");
                                                String price = jsonObject.getString("price");
                                                String keyword = jsonObject.getString("keyword");
                                                final String imgurl = jsonObject.getString("imgurl");
                                                final String[] imgurls = imgurl.split(",");
                                                Thread mThread = new Thread(){
                                                    @Override
                                                    public void run(){
                                                        try {
                                                            URL url = new URL("http://3.34.136.232:8080/image/" + imgurls[0]);
                                                            img = ((BitmapDrawable)drawable).getBitmap();
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
                                                addItem(img, name, keyword,price,addr,code,imgurl, (float)4);
                                            }

                                            mAdapter.notifyDataSetChanged(); //새로고침

                                        } else { //검색 결과 없음
                                            mAdapter.notifyDataSetChanged(); //새로고침
                                            return;
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            //실제 서버로 Volley를 이용해서 요청을 함.
                            MyReservingRequest myReservingRequest = new MyReservingRequest(Integer.parseInt(codeList.get(i).toString()), responseListener);
                            RequestQueue queue = Volley.newRequestQueue(Reserving.this);
                            queue.add(myReservingRequest);
                        }
                    } else { //검색 결과 없음
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //실제 서버로 Volley를 이용해서 요청을 함.
        UserReservingRequest userReservingRequest = new UserReservingRequest(userId, responseListener2);
        RequestQueue queue2 = Volley.newRequestQueue(Reserving.this);
        queue2.add(userReservingRequest);

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
