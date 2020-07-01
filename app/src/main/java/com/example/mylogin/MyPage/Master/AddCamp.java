package com.example.mylogin.MyPage.Master;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddCamp extends AppCompatActivity {

    String id;

    private Spinner tema_spinner, facility_spinner;

    private String[] data;
    private ArrayAdapter<String> adapter;

    private String[] facility = {"전기", "온수", "산책로", "운동시설", "와이파이", "장작대여", "편의점", "운동장", "수영장", "장비대여", "동물동반"};
    private int[] facilityImgs = new int[11]; //테마 선택 그림 넣을 배열변수
    public static boolean chk[] = new boolean[11];
    private String facility_chk;

    private EditText camp_name, camp_addr, camp_tel, camp_keyword, camp_openday, price_name, price, price_content;
    private Button album_btn, add_price, add_camp_btn,reset_price;

    ArrayList<AddPriceItem> priceDataList;

    private ArrayList<String> p_title;
    private ArrayList<String> p_content;
    private ArrayList<String> p_price;

    final static int REQUEST_TAKE_ALBUM = 2;
    int campcode;
    int z = 0;
    String imageFileName;
    String imageFileNamePlus;
    int lessprice = 1000000000;
    String urlUpload = "http://3.34.136.232/CampingjangPhotoUpload.php";
    String urlUpload1 = "http://3.34.136.232/CampingjangPhotoUpload1.php";
    String urlUpload2 = "http://3.34.136.232/CampingjangPhotoUpload2.php";
    String urlUpload3 = "http://3.34.136.232/CampingjangPhotoUpload3.php";
    String urlUpload4 = "http://3.34.136.232/CampingjangPhotoUpload4.php";
    private Uri photoURI;

    private ArrayList<Bitmap> p_bitmap;

    String imageData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camp);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);//키보드 올라올시 화면도 올라가게

        Intent intent = getIntent();
        id = intent.getExtras().getString("id"); //아이디 불러옴

        int j;
        for (j = 0; j < chk.length; j++) {
            chk[j] = false;
        }

        Response.Listener<String> responseListener4 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success)//검색 결과 성공
                    {
                        campcode = jsonObject.getInt("campcode");
                        campcode = campcode + 1;
                    } else { //검색 결과 없음
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //실제 서버로 Volley를 이용해서 요청을 함.
        CampCodeGet campCodeGet = new CampCodeGet(responseListener4);
        RequestQueue queue4 = Volley.newRequestQueue(AddCamp.this);
        queue4.add(campCodeGet);

        tema_spinner = findViewById(R.id.tema_spinner);
        facility_spinner = findViewById(R.id.facility_spinner);


        data = getResources().getStringArray(R.array.테마);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, data);
        tema_spinner.setAdapter(adapter);


        for (int i = 0; i < facilityImgs.length; i++) {
            facilityImgs[i] = getResources().getIdentifier("icon_facility_" + i, "drawable", "com.example.mylogin");
            //시설선택 스피너에 넣을 이미지
        }
        final AdapterFacilitySpinner adapterFacilitySpinner = new AdapterFacilitySpinner(facility, facilityImgs, this);
        facility_spinner.setAdapter(adapterFacilitySpinner);


        camp_name = findViewById(R.id.camp_name);
        camp_addr = findViewById(R.id.camp_addr);
        camp_tel = findViewById(R.id.camp_tel);
        camp_keyword = findViewById(R.id.keyword);
        camp_openday = findViewById(R.id.openday);



        album_btn = findViewById(R.id.album_btn);
        album_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p_bitmap.clear();
                //카메라 권한부분은 MainActivity에서 미리 받고 있음
                getAlbum();
            }
        });


        price_name = findViewById(R.id.price_name);
        price = findViewById(R.id.price);
        price_content = findViewById(R.id.price_content);

        priceDataList = new ArrayList<AddPriceItem>();

        final ListView price_list = (ListView) findViewById(R.id.price_list);
        final AddPriceAdapter addPriceAdapter = new AddPriceAdapter(this, priceDataList);
        price_list.setAdapter(addPriceAdapter);

        p_title = new ArrayList<>();
        p_content = new ArrayList<>();
        p_price = new ArrayList<>();


        reset_price = findViewById(R.id.reset_price);
        reset_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceDataList.clear();

                price_name.setText("");
                price.setText("");
                price_content.setText("");
                addPriceAdapter.notifyDataSetChanged();
            }
        });

        add_price = findViewById(R.id.add_price);
        add_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceDataList.add(new AddPriceItem(price_name.getText().toString(), price_content.getText().toString(), price.getText().toString()));
                addPriceAdapter.notifyDataSetChanged();

                p_title.add(price_name.getText().toString());
                p_content.add(price_content.getText().toString());
                p_price.add(price.getText().toString());

                price_name.setText("");
                price.setText("");
                price_content.setText("");
            }
        });

        p_bitmap = new ArrayList<>();

        add_camp_btn = findViewById(R.id.add_camp_btn);
        add_camp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(camp_name.getText() + "  캠핑장 이름"); //캠핑장
                System.out.println(camp_addr.getText() + "  캠핑장 주소"); //캠핑장
                System.out.println(camp_tel.getText() + "  캠핑장 문의처"); //캠핑장
                System.out.println(camp_keyword.getText() + "  캠핑장 키워드"); //캠핑장
                System.out.println(camp_openday.getText() + "  캠핑장 운영일"); //캠핑장

                int select_tema = tema_spinner.getSelectedItemPosition() + 1; //선택된 테마 //캠핑장
                System.out.println(select_tema + "  선택 된 테마");

                CheckTema();
                System.out.println(facility_chk + "   기타 시설정보 체크한 시설정보"); // facility_chk 이 배열이 선택한 체크한 시설정보 //캠핑장
                imageFileNamePlus = "";
                for (int i =0; i < p_bitmap.size(); i++){
                    if(i== p_bitmap.size()-1 ){
                        imageFileNamePlus += imageFileName + i + ".jpg";
                    }else {
                        imageFileNamePlus += imageFileName + i + ".jpg,";
                    }
                }

                for (int i = 0; i < p_price.size(); i++){
                    if (lessprice > Integer.parseInt(p_price.get(i))){
                        lessprice = Integer.parseInt(p_price.get(i));
                    }
                }

                for (int i = 0; i < priceDataList.size(); i++) {
                    System.out.println(p_title.get(i)); //캠핑장아이템
                    System.out.println(p_content.get(i)); //캠핑장아이템
                    System.out.println(p_price.get(i)); //캠핑장아이템
                    Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success)//검색 결과 성공
                            {
                            } else { //검색 결과 없음
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //실제 서버로 Volley를 이용해서 요청을 함.
                    AddItemRequest addItemRequest = new AddItemRequest(campcode,i+1, p_title.get(i), p_content.get(i), Integer.parseInt(p_price.get(i)), responseListener2);
                RequestQueue queue2 = Volley.newRequestQueue(AddCamp.this);
                queue2.add(addItemRequest);
            }

                Response.Listener<String> responseListener1 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success)//검색 결과 성공
                            {
                            } else { //검색 결과 없음
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //실제 서버로 Volley를 이용해서 요청을 함.
                AddCampingjang addCampingjang = new AddCampingjang(camp_name.getText().toString(), camp_addr.getText().toString(), camp_tel.getText().toString(),
                        camp_keyword.getText().toString(), camp_openday.getText().toString(), select_tema, facility_chk, imageFileNamePlus, lessprice, responseListener1);
                RequestQueue queue1 = Volley.newRequestQueue(AddCamp.this);
                queue1.add(addCampingjang);

                if (p_bitmap.size() >= 1) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpload, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddCamp.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            imageData = imamgeToString(p_bitmap.get(0));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 0);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddCamp.this);
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
                            Toast.makeText(AddCamp.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            imageData = imamgeToString(p_bitmap.get(1));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 1);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddCamp.this);
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
                            Toast.makeText(AddCamp.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            imageData = imamgeToString(p_bitmap.get(2));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 2);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddCamp.this);
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
                            Toast.makeText(AddCamp.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            imageData = imamgeToString(p_bitmap.get(3));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 3);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddCamp.this);
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
                            Toast.makeText(AddCamp.this,"error:" + error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            imageData = imamgeToString(p_bitmap.get(4));
                            params.put("image", imageData);
                            params.put("userid", imageFileName + 4);
                            z++;
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AddCamp.this);
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    void CheckTema() {
        facility_chk = "";
        for (int y = 0; y < chk.length; y++) {
            if (chk[y]) {
                if (facility_chk.equals("")) {
                    facility_chk = String.valueOf(y);
                } else {
                    facility_chk = facility_chk + "," + y;
                }
            }
        }
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
                                imageFileName = id + "_" + timeStamp + "_";
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), clipData.getItemAt(i).getUri());
                                p_bitmap.add(bitmap);

                                System.out.println(p_bitmap.get(i) + "사진넣는곳@@@@@@@@@@@@@@@");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Toast.makeText(this,"사진은 5장 이하만 첨부가능합니다.",Toast.LENGTH_LONG).show();
                        return;
                    }
                }//리절트 오케이 끝
                break;
            }
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