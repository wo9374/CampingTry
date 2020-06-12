package com.example.mylogin.SEARCH;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends StringRequest {
    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://3.34.136.232/Search.php";
    private Map<String, String> map;

    public SearchRequest(String mAdd, String sAdd, String keyword_txt, String tema_chk, Response.Listener<String> listener)
    {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("mAdd", mAdd);
        map.put("sAdd", sAdd);
        map.put("keyword_txt",keyword_txt);
        map.put("tema_chk",tema_chk);
        System.out.println("전달완료@@@@@@@@@@@@#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(mAdd+"@@@"+sAdd+"@@@"+keyword_txt+"@@@"+tema_chk +"@@@");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
