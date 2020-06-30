package com.example.mylogin.SNS.Home;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LikePlus extends StringRequest {

    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://3.34.136.232/LikeHap.php";
    private Map<String, String> map;

    public LikePlus(String snscode, int liked, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        liked = (liked+1);
        map = new HashMap<>();
        map.put("snscode", snscode);
        map.put("liked", liked + "");
        System.out.println(snscode + liked + "플러스@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
