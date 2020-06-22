package com.example.mylogin.MyPage.RequestProfile;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RequestProfileRequest extends StringRequest {

    final static private String URL = "http://hwi4011.dothome.co.kr/Request.php";
    private Map<String, String> map;

    public RequestProfileRequest(String userEmail, String userPassword, String userSubname, int userNum, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userEmail", userEmail + "");
        map.put("userPassword", userPassword);
        map.put("userSubname", userSubname);
        map.put("userNum", userNum + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
