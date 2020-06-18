package com.example.mylogin.MyPage.RequestProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.MyPage.MyPage;
import com.example.mylogin.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestProfileActivity extends AppCompatActivity {

    private TextView re_userid, re_username;
    private EditText re_email, re_pass, re_pass2, re_subname, re_num, check_pass;
    private Button re_fin, re_close;
    private String get_userid, get_pass, get_subname, get_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_profile);

        re_email = findViewById(R.id.et_email);
        re_pass = findViewById(R.id.et_pass);
        re_pass2 = findViewById(R.id.et_pass2);
        re_subname = findViewById(R.id.et_id);
        re_num = findViewById(R.id.et_subname);

        check_pass = findViewById(R.id.check_pass); //비번 동일한지 확인용

        re_fin = findViewById(R.id.re_fin);
        re_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userEmail = re_email.getText().toString();
                final String userPass = re_pass.getText().toString();
                final String userPass2 = re_pass2.getText().toString();
                final String userSubname = re_subname.getText().toString();
                final int userNum = Integer.parseInt(re_num.getText().toString());

                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (check_pass.getText().toString().equals(get_pass))
                            {
                                if (userPass.equals(userPass2))
                                {
                                    if (success)//정보수정 성공
                                    {
                                        Toast.makeText(getApplicationContext(),"정보를 수정하였습니다!",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RequestProfileActivity.this, MyPage.class);
                                        startActivity(intent);
                                    } else { //수정 실패
                                        Toast.makeText(getApplicationContext(),"수정을 실패하였습니다!",Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                } else {Toast.makeText(getApplicationContext(),"비밀번호가 동일하지 않습니다.",Toast.LENGTH_SHORT).show();}
                            } else {Toast.makeText(getApplicationContext(),"현재 비밀번호와 일치하지 않습니다.",Toast.LENGTH_SHORT).show();}
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //RegisterRequest registerRequest = new RegisterRequest(userEmail, userPass, userSubname, userNum, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RequestProfileActivity.this);
                //queue.add(registerRequest);
            }
        });
    }
}