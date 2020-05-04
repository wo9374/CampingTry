package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register, btn_searchlog;
    public static TextView tv_state;
    private NetworkReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_searchlog = findViewById(R.id.btn_searchlog);
        tv_state = findViewById(R.id.tv_state);

        //브로드 캐스트 리시버 등록
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
//        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(receiver, filter);



        //회원가입
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //아이디/비밀번호 찾기
        btn_searchlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SearchIdActivity.class);
                startActivity(intent);
            }
        });

        //로그인
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success)//로그인 성공
                            {
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");
                                String userSubname = jsonObject.getString("userSubname");

                                Toast.makeText(getApplicationContext(),"로그인에 성공하셨습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPass", userPass);
                                intent.putExtra("userSubname", userSubname);
                                startActivity(intent);
                            } else { //로그인 실패
                                Toast.makeText(getApplicationContext(),"로그인에 실패하셨습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //브로드 캐스트 리시버 해제
        unregisterReceiver(receiver);

        //앱 종료시 꼭 이 구문을 사용해줘야함
    }

}
