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
import com.example.mylogin.MyPage.User.MyPage;
import com.example.mylogin.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestProfileActivity extends AppCompatActivity {

    private TextView re_userid, re_username;    //수정불가 정보
    private EditText re_email, re_pass, re_pass2, re_subname, re_num, check_pass;   //수정 정보
    private Button re_fin;
    private String get_userid, get_pass, get_name;  //Intent정보 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_profile);

        re_email = findViewById(R.id.re_email);
        re_pass = findViewById(R.id.re_pass);
        re_pass2 = findViewById(R.id.re_pass2);
        re_subname = findViewById(R.id.re_subname);
        re_num = findViewById(R.id.re_num);

        re_userid = findViewById(R.id.re_userid);
        check_pass = findViewById(R.id.check_pass); //비번 동일한지 확인용
        re_username = findViewById(R.id.re_username);

        re_fin = findViewById(R.id.re_fin);



        Intent intent = getIntent();
        get_userid = intent.getStringExtra("id");
        get_pass = intent.getStringExtra("pass");
        get_name = intent.getStringExtra("name");

        re_userid.setText(get_userid);
        re_username.setText(get_name);

        re_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = re_email.getText().toString();
                final String userPass = re_pass.getText().toString();
                final String userPass2 = re_pass2.getText().toString();
                String userSubname = re_subname.getText().toString();
                final String Checkpass = check_pass.getText().toString();
                int userNum = Integer.parseInt(re_num.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (Checkpass.equals(get_pass))
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
                RequestProfileRequest requestProfileRequest = new RequestProfileRequest(userEmail, userPass, userSubname, userNum, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RequestProfileActivity.this);
                queue.add(requestProfileRequest);
            }
        });
    }
}