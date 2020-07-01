package com.example.mylogin.Shop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylogin.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddShop extends AppCompatActivity {

    String id;
    String imageFileName;

    TextView item_name, item_content, item_price;
    Button album_button,add_shop_btn;

    final static int REQUEST_TAKE_ALBUM = 2;

    private ArrayList<Bitmap> p_bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        item_name = findViewById(R.id.item_name);
        item_content = findViewById(R.id.item_content);
        item_price = findViewById(R.id.item_price);

        album_button = findViewById(R.id.album_button);

        p_bitmap = new ArrayList<>();
        album_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_bitmap.clear();
                //카메라 권한부분은 MainActivity에서 미리 받고 있음
                getAlbum();
            }
        });


        add_shop_btn = findViewById(R.id.add_shop_btn);
        add_shop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item_name.getText().toString();      //물건이름
                //item_content.getText().toString();   //물건설명
                //item_price.getText().toString();     //물건가격

                //p_bitmap //비트맵 들어간 비트맵 배열
            }
        });
    }


    private void getAlbum() {
        Intent getAlbumintent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);

        getAlbumintent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        getAlbumintent.setAction(Intent.ACTION_GET_CONTENT);
        getAlbumintent.setType("image/*");
        startActivityForResult(getAlbumintent, REQUEST_TAKE_ALBUM);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case REQUEST_TAKE_ALBUM: {
                if (resultCode == RESULT_OK) {
                    ClipData clipData = intent.getClipData();
                    if (clipData != null) {
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            try {
                                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                imageFileName = id + "_" + timeStamp+"_"+i;
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), clipData.getItemAt(i).getUri());
                                p_bitmap.add(bitmap);

                                System.out.println(p_bitmap.get(i) + "사진넣는곳@@@@@@@@@@@@@@@");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("사진 널 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    }
                }//리절트 오케이 끝
                break;
            } //테이크 앨범 끝
        }
    }

    private String imamgeToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return  encodedImage;
    }
}
