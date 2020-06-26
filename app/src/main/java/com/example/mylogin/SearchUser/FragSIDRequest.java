package com.example.mylogin.SearchUser;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FragSIDRequest extends StringRequest {

    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://hwi4011.dothome.co.kr/Register.php";
    private Map<String, String> map;


    public FragSIDRequest(String userName, int userNum, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userName", userName);
        map.put("userNum", userNum + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
