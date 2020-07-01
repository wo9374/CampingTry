package com.example.mylogin.MyPage.Master;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddCampingjang extends StringRequest {
    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://3.34.136.232/CampingjangInsert.php";
    private Map<String, String> map;

    public AddCampingjang(String name, String addr, String phone, String keyword, String openday, int tema, String facility, String imgurl, int price, Response.Listener<String> listener)
    {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("name", name);
        map.put("addr", addr);
        map.put("phone",phone);
        map.put("keyword",keyword);
        map.put("openday",openday);
        map.put("tema",tema + "");
        map.put("facility",facility);
        map.put("imgurl",imgurl);
        map.put("price",price + "");
        System.out.println(name + "이름" + addr + "주소" + phone + "번호" + keyword + "키워드" + openday + "운영일" + tema + "테마" + facility + "시설" + imgurl + "이미지유알엘" + price + "가격");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
