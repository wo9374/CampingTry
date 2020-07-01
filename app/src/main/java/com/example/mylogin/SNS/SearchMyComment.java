package com.example.mylogin.SNS;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchMyComment extends StringRequest {
    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://3.34.136.232/SearchMyComment.php";
    private Map<String, String> map;

    public SearchMyComment(int snscode, Response.Listener<String> listener)
    {
        super(Request.Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("snscode", snscode + "");
        System.out.println(snscode + "코드 받아왓어요@@@@@@@@@@@@@@@리퀘스트@@@");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
