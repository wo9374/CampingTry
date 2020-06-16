package com.example.mylogin.SNS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mylogin.R;

public class PhotoResult extends AppCompatActivity {
    ImageView result_img;
    Button cancel;
    Button write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_result);

        result_img = findViewById(R.id.result_img);
        cancel = findViewById(R.id.cancel);
        write = findViewById(R.id.write);

        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap)intent.getParcelableExtra("image");

        result_img.setImageBitmap(bitmap);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
