package com.example.mylogin;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //서버 URL설정 (php파일 연동)
    final static private String URL = "http://hwi4011.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userEmail, String userPassword, String userName, String userSubname, int userNum, int userSex, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userEmail", userEmail);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("userSubname", userSubname);
        map.put("userNum", userNum + "");
        map.put("userSex", userSex + "");

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
