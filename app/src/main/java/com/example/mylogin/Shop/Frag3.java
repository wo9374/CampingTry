package com.example.mylogin.Shop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Frag3 extends Fragment {

    private View view;
    GridView shop_gridview;
    ShopAdapter mAdapter;

    Drawable drawable;
    Bitmap img;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3, container, false);

        shop_gridview = view.findViewById(R.id.shop_gridview);
        mAdapter = new ShopAdapter();

        drawable = getResources().getDrawable(R.drawable.tema_4);

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
                            String prcode = jsonObject.getString("prcode");
                            String prname = jsonObject.getString("prname");
                            String prdesc = jsonObject.getString("prdesc");
                            String prprice = jsonObject.getString("prprice");
                            String pruser = jsonObject.getString("pruserid");
                            final String imgurl = jsonObject.getString("primg");
                            final String[] imgurls = imgurl.split(",");

                            Thread mThread = new Thread(){
                                @Override
                                public void run(){
                                    try {
                                        URL url = new URL("http://3.34.136.232:8080/image/product/" + imgurls[0]);
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
                            addItem(img,prname,prprice,prcode,pruser,imgurl,prdesc);
                        }
                        mAdapter.notifyDataSetChanged();
                    } else { //검색 결과 없음
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //실제 서버로 Volley를 이용해서 요청을 함.
        SearchProduct searchProduct = new SearchProduct(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(searchProduct);

        shop_gridview.setAdapter(mAdapter);

        FloatingActionButton fab = view.findViewById(R.id.write_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddShop.class);
                startActivity(intent);
            }
        });
        return view;
    }

    void addItem(Bitmap image, String name, String price,String shopcode,String userid, String url, String desc){
        mAdapter.addItem(new ShopItem(image,name, price,shopcode,userid,url,desc));
    }
}