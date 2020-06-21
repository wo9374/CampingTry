package com.example.mylogin.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylogin.MainActivity;
import com.example.mylogin.MyPage.RequestProfile.RequestProfileActivity;
import com.example.mylogin.R;

public class MyPage extends AppCompatActivity {

    private TextView d_myid, d_mynic;
    private String d_nic, d_name, d_pass;
    private Button m_requestpro, d_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        d_myid = findViewById(R.id.d_myid);
        d_mynic = findViewById(R.id.d_mynic);

        m_requestpro = findViewById(R.id.m_requestpro);
        d_close = findViewById(R.id.d_close);

        Intent intent = getIntent();
        String s_id = intent.getStringExtra("id");
        String s_nic = intent.getStringExtra("nic");
        String s_name = intent.getStringExtra("name");
        String s_pass = intent.getStringExtra("pass");

        d_myid.setText(s_id);
        d_mynic.setText("("+s_nic+")님 반갑습니다!");

        d_nic = s_nic;  //닉네임 원본

        d_name = s_name;
        d_pass = s_pass;


        //회원정보 수정
        m_requestpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, RequestProfileActivity.class);
                intent.putExtra("id", d_myid.getText().toString());
                intent.putExtra("name", d_name);
                intent.putExtra("pass", d_pass);
                startActivity(intent);
            }
        });


        //닫기
        d_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, MainActivity.class);
                intent.putExtra("userID", d_myid.getText().toString());
                intent.putExtra("userSubname", d_nic);
                startActivity(intent);
                finish();
            }
        });
    }
}