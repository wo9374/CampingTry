package com.example.mylogin.SNS.Home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Home extends Fragment {
    private View view;
    private Context ct;
    Bitmap img;
    Drawable drawable;

    RecyclerView mRecyclerView = null;
    public static  HomeAdapter mAdapter = null;
    ArrayList<HomeItem> mList = new ArrayList<HomeItem>();
    private ArrayList<String> snscodeList;
    private ArrayList<String> commentcountList;
    String commentcount1 = "0";

    public static String userid, nic; //아이디, 닉네임

    String liked;

    ArrayList<String> likelist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sns_home, container, false);
        mList.clear();
        ct = container.getContext();

        if (getArguments() != null) {
            userid = getArguments().getString("userid");
            nic = getArguments().getString("nic");
        }
        snscodeList = new ArrayList<>();
        commentcountList = new ArrayList<>();
        drawable = getResources().getDrawable(R.drawable.tema_4);


        likelist = new ArrayList<>();
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
                            String snscode = jsonObject.getString("snscode");
                            System.out.println(snscode + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                            likelist.add(snscode);
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
        LikeCheck likeCheck = new LikeCheck(userid, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ct);
        queue.add(likeCheck);

        Response.Listener<String> responseListener1 = new Response.Listener<String>() {
            @Override
            public void onResponse(String responses) {
                try {
                    JSONArray jsonArray = new JSONArray(responses);
                    JSONObject jsonObjectfirst = jsonArray.getJSONObject(0);
                    boolean success = jsonObjectfirst.getBoolean("success");
                    if (success)//검색 결과 성공
                    {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String snscode = jsonObject.getString("snscode");
                            String commentcount = jsonObject.getString("commentcount");
                            snscodeList.add(snscode);
                            commentcountList.add(commentcount);
                        }
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String responses) {
                                try {
                                    JSONArray jsonArray = new JSONArray(responses);
                                    JSONObject jsonObjectfirst = jsonArray.getJSONObject(0);
                                    boolean success = jsonObjectfirst.getBoolean("success");
                                    if (success)//검색 결과 성공
                                    {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            String snscode = jsonObject.getString("snscode");
                                            String name = jsonObject.getString("name");
                                            String date = jsonObject.getString("date");
                                            String desc = jsonObject.getString("desc");
                                            String campcode = jsonObject.getString("campcode");
                                            final String imgurl = jsonObject.getString("imgurl");
                                            String like = jsonObject.getString("like");
                                            String campname = jsonObject.getString("campname");
                                            Thread mThread = new Thread() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        URL url = new URL("http://3.34.136.232:8080/image/sns/" + imgurl);
                                                        img = ((BitmapDrawable) drawable).getBitmap();
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
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            for (int x = 0; x < snscodeList.size(); x++) {
                                                if (snscodeList.get(x) == snscode) {
                                                    commentcount1 = commentcountList.get(x);
                                                }
                                            }


                                            if(HomeAdapter.ViewHolder.like_on = true){
                                                HomeAdapter.ViewHolder.like_on = false;
                                                liked = "좋아요";
                                            }else{
                                                HomeAdapter.ViewHolder.like_on = true;
                                                liked = "취소";
                                            }

                                            for( int x =0; x<likelist.size(); x++){
                                                if(snscode.equals(likelist.get(x))){
                                                    liked = "취소";
                                                    HomeAdapter.ViewHolder.like_on = true;

                                                }
                                            }
                                            addItem(name, date, desc, img, like, commentcount1, snscode, campname, campcode,liked);
                                            commentcount1 = "0";
                                        }
                                        mAdapter.notifyDataSetChanged(); //새로고침
                                    } else { //검색 결과 없음
                                        return;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        //실제 서버로 Volley를 이용해서 요청을 함.
                        SnsRequest reserveRequest = new SnsRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(ct);
                        queue.add(reserveRequest);
                    } else { //검색 결과 없음
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //실제 서버로 Volley를 이용해서 요청을 함.
        CommentCountRequest commentCountRequest = new CommentCountRequest(responseListener1);
        RequestQueue queue1 = Volley.newRequestQueue(ct);
        queue1.add(commentCountRequest);

        mRecyclerView = view.findViewById(R.id.home_recycle);
        mAdapter = new HomeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        return view;
    }

    void addItem(String username, String time, String content, Bitmap image, String like, String comment, String snscode, String title, String campcode,String like_text) {
        HomeItem item = new HomeItem();

        item.setUsername(username);
        item.setTime(time);
        item.setContent(content);
        item.setImage(image);
        item.setLike(like);
        item.setComment(comment);
        item.setSnscode(snscode);
        item.setTitle(title);
        item.setCampcode(campcode);
        item.setLike_text(like_text);

        mList.add(item);
    }
}
