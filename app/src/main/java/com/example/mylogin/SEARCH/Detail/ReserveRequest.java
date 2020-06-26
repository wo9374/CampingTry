package com.example.mylogin.SEARCH.Detail;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReserveRequest extends StringRequest {
    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://3.34.136.232/Reserve.php";
    private Map<String, String> map;

    public ReserveRequest(int code, int campitemcode, String userid ,String firDay, String endDay, Response.Listener<String> listener)
    {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("campcode", code + "");
        map.put("campitemcode", campitemcode + "");
        map.put("userid", userid);
        map.put("checkindate", firDay);
        map.put("checkoutdate", endDay);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
