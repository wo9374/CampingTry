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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.R;
import com.example.mylogin.SNS.Home.Home;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddShop extends AppCompatActivity {

    String id;
    String imageFileName;
    int z = 0;

    String urlUpload = "http://3.34.136.232/ProductPhotoUpload.php";
    String urlUpload1 = "http://3.34.136.232/ProductPhotoUpload1.php";
    String urlUpload2 = "http://3.34.136.232/ProductPhotoUpload2.php";
    String urlUpload3 = "http://3.34.136.232/ProductPhotoUpload3.php";
    String urlUpload4 = "http://3.34.136.232/ProductPhotoUpload4.php";
    String imageFileNamePlus;

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

                imageFileNamePlus = "";
                for (int i =0; i < p_bitmap.size(); i++){
                    if(i== p_bitmap.size()-1 ){
                        imageFileNamePlus += imageFileName + i + ".jpg";
                    }else {
                        imageFileNamePlus += imageFileName + i + ".jpg,";
                    }
                }

                Response.Listener<String> responseListener1 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success)//검색 결과 성공
                            {
                                finish();
                            } else { //검색 결과 없음
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //실제 서버로 Volley를 이용해서 요청을 함.
                AddShopRequest addShopRequest = new AddShopRequest(item_name.getText().toString(), Integer.parseInt(item_price.getText().toString()), item_content.getText().toString(), Home.userid, imageFileNamePlus, responseListener1);
                RequestQueue queue1 = Volley.newRequestQueue(AddShop.this);
                queue1.add(addShopRequest);

                if (p_bitmap.size() >= 1) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpload, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddShop.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            String imageData = imamgeToString(p_bitmap.get(0));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 0);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddShop.this);
                    requestQueue.add(stringRequest);
                }

                if (p_bitmap.size() >= 2) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpload1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddShop.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            String imageData = imamgeToString(p_bitmap.get(1));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 1);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddShop.this);
                    requestQueue.add(stringRequest);
                }

                if (p_bitmap.size() >= 3) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpload2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddShop.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            String imageData = imamgeToString(p_bitmap.get(2));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 2);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddShop.this);
                    requestQueue.add(stringRequest);
                }
                if (p_bitmap.size() >= 4) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpload3, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddShop.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            String imageData = imamgeToString(p_bitmap.get(3));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 3);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddShop.this);
                    requestQueue.add(stringRequest);
                }
                if (p_bitmap.size() >= 5) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpload4, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddShop.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            String imageData = imamgeToString(p_bitmap.get(4));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 4);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddShop.this);
                    requestQueue.add(stringRequest);
                }
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
                    if (clipData != null && clipData.getItemCount() < 6) {
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            try {
                                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                imageFileName = Home.userid + "_" + timeStamp+"_"+i;
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), clipData.getItemAt(i).getUri());
                                p_bitmap.add(bitmap);

                                System.out.println(p_bitmap.get(i) + "사진넣는곳@@@@@@@@@@@@@@@");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Toast.makeText(this,"사진은 5장 이하만 첨부가능합니다.",Toast.LENGTH_LONG).show();
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
