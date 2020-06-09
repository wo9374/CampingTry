package com.example.mylogin;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://hwi4011.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userEmail, String userID, String userPassword, String userSubname, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userEmail", userEmail + "");
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userSubname", userSubname);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
