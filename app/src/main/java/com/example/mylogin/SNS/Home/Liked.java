package com.example.mylogin.SNS.Home;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Liked extends StringRequest {

    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://3.34.136.232/Liked.php";
    private Map<String, String> map;

    public Liked(String userid, String snscode, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userid", userid);
        map.put("snscode", snscode);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
