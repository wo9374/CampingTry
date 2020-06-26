package com.example.mylogin.SNS;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PhotoRequest extends StringRequest {
    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://3.34.136.232/SnsInsert.php1";
    private Map<String, String> map;

    public PhotoRequest(String usernickname, String comment, String imgurl, Response.Listener<String> listener)
    {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("usernickname", usernickname);
        map.put("comment", comment);
        map.put("imgurl",imgurl);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
