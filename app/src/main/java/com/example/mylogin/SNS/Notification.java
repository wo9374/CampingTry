package com.example.mylogin.SNS;

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
import com.example.mylogin.MyPage.Reserving;
import com.example.mylogin.MyPage.UserReservingRequest;
import com.example.mylogin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Notification extends Fragment {
    private View view;
    RecyclerView mRecyclerView = null;
    NotificationAdapter mAdapter = null;
    ArrayList<NotificationItem> mList = new ArrayList<NotificationItem>();
    private ArrayList<String>codeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sns_notification, container, false);
        mList.clear();
        codeList = new ArrayList<>();

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
                            String snscode = jsonObject.getString("code");
                            codeList.add(snscode);
                            System.out.println(snscode + "sns코드 받아와요@@@@@@@@@@@@@@@@@@");
                        }
                        System.out.println(codeList.size() + "코싸@@@@@@@@@@@@@@@@");
                        for (int z = 0; z < codeList.size(); z++){
                            Response.Listener<String> responseListener3 = new Response.Listener<String>() {
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
                                                String snscodes = jsonObject.getString("code");
                                                String nic = jsonObject.getString("nic");
                                                String time = jsonObject.getString("time");
                                                System.out.println(snscodes + nic + time + "마지막으로 받아온값@@@@@@@@@@@@@@@");
                                                addItem(nic,time);
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
                            SearchMyComment searchMyComment = new SearchMyComment(Integer.parseInt(codeList.get(z)), responseListener3);
                            RequestQueue queue3 = Volley.newRequestQueue(getContext());
                            queue3.add(searchMyComment);
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
        GetCodeRequest getCodeRequest = new GetCodeRequest(Frag1.userid, responseListener2);
        RequestQueue queue2 = Volley.newRequestQueue(getContext());
        queue2.add(getCodeRequest);
        System.out.println(codeList.size() + "코싸@@@@@@@@@@@@@@@");

        mRecyclerView = view.findViewById(R.id.notification_recycle);
        mAdapter = new NotificationAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        return view;
    }

    void addItem(String nic, String time){
        NotificationItem item = new NotificationItem();

        item.setNic(nic);
        item.setTime(time);

        mList.add(item);
    }
}