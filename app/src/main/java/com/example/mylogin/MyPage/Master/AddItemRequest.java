package com.example.mylogin.MyPage.Master;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddItemRequest extends StringRequest {
    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://3.34.136.232/CampItemInsert.php";
    private Map<String, String> map;

    public AddItemRequest(int campcode, int campitemcode, String itemname, String itemdesc, int price, Response.Listener<String> listener)
    {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("campcode", campcode + "");
        map.put("campitemcode", campitemcode + "");
        map.put("itemname",itemname);
        map.put("itemdesc",itemdesc);
        map.put("price",price+ "");
        System.out.println(campcode + campitemcode + itemname + itemdesc + price + "@@@@@@@@@@@@@@@@@@@@");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
