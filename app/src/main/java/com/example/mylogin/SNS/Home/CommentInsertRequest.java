package com.example.mylogin.SNS.Home;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CommentInsertRequest extends StringRequest {
    //서버 URL 설정 (php파일 연동)
    final static private String URL = "http://3.34.136.232/CommentInsert.php";
    private Map<String, String> map;

    public CommentInsertRequest(int snscode, String usernickname, String comment, Response.Listener<String> listener)
    {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("snscode", snscode + "");
        map.put("usernickname", usernickname);
        map.put("comment", comment);
        System.out.println(snscode + usernickname + comment +"@@@!@@@@@@@@@@!!!!!!!!!!");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
