package com.example.mylogin.Shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mylogin.R;

public class DetailShop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shop);

        Intent intent = getIntent();
        String shopcode = intent.getStringExtra("shopcode");



    }
}
