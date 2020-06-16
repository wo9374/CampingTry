package com.example.mylogin.MyPage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylogin.MainActivity;
import com.example.mylogin.MyPage.RequestProfile.RequestProfileActivity;
import com.example.mylogin.R;

public class MyPage extends AppCompatActivity {

    private TextView d_myid, d_mynic;
    private String d_nic;
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
        String str = intent.getStringExtra("id");
        String str2 = intent.getStringExtra("nic");

        d_myid.setText(str);
        d_mynic.setText("("+str2+")님 반갑습니다!");
        d_nic = str2;



        m_requestpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPage.this, RequestProfileActivity.class);
                startActivity(intent);
            }
        });



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