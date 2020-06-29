package com.example.mylogin.SEARCH.Detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.mylogin.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailInformation extends AppCompatActivity implements OnMapReadyCallback {

    TextView name; //캠핑장 이름

    RecyclerView image_recycle; //이미지 수평 리사이클
    ImageAdapter imageAdapter;

    RecyclerView icon_recycle; //아이콘 수평 리사이클
    IconAdapter iconAdapter;

    RecyclerView price_recycle; //가격 수직 리사이클
    PriceAdapter priceAdapter;

    RecyclerView review_recycle; //리뷰 수직 리사이클
    ReviewAdapter reviewAdapter;

    LinearLayoutManager image_LayoutManager;
    LinearLayoutManager icon_LayoutManager;
    LinearLayoutManager price_LayoutManager;
    LinearLayoutManager review_LayoutManager;
    //각 리사이클에 대한 레이아웃 매니저


    FragmentManager fragmentManager;
    MapFragment mapFragment;
    Bitmap img;
    String addr1;
    String nameii;
    List<Address> list1 = null;
    //구글맵

    int i; //이미지 불러올때 배열증가에 쓰인 변수

    ArrayList<PriceItem> price_data = new ArrayList<PriceItem>(); //가격표시할 배열

    private CalendarView calendarView; //달력
    String firDay = null; //체크인 날짜
    String endDay = null; //체크아웃 날짜

    Button reservation_btn; //예약하기 버튼

    int campitemcode = 0; //구역 선택시 구역코드 넣어줄 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);

        Intent intent = getIntent();
        String code = intent.getExtras().getString("code"); //코드 불러옴
        final int codeint = Integer.parseInt(code);
        final String imgurl = intent.getExtras().getString("url");
        final String userid = intent.getExtras().getString("userid");
        addr1 = intent.getExtras().getString("addr");
        nameii = intent.getExtras().getString("name");

        name = findViewById(R.id.name); //캠핑장 이름
        name.setText(intent.getExtras().getString("name"));


        icon_LayoutManager = new LinearLayoutManager(this); //수평 레이아웃 매니저
        icon_LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //수평으로 지정 (사진 리사이클)


        image_LayoutManager = new LinearLayoutManager(this); //수평 레이아웃 매니저
        image_LayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //수평으로 지정 (아이콘 리사이클)


        price_LayoutManager = new LinearLayoutManager(this); //수직 레이아웃 매니저
        price_LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//수직으로 지정 (가격 리사이클)


        fragmentManager = getFragmentManager(); //구글맵 설정
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.Detail_map);
        mapFragment.getMapAsync(this);


        image_recycle = findViewById(R.id.image_recycle); // 이미지 리사이클러뷰
        image_recycle.setLayoutManager(image_LayoutManager);  //레이아웃 매니저 지정
        imageAdapter = new ImageAdapter(); //init 어뎁터

        ArrayList<ImageItem> img_data = new ArrayList<>();
        Drawable drawable = getResources().getDrawable(R.drawable.tema_4); //기본 사진
        final String[] imgurls = imgurl.split(",");

        for (int x=0; x<imgurls.length; x++){
            Thread mThread = new Thread(){
                @Override
                public void run(){
                    try {
                        URL url = new URL("http://3.34.136.232:8080/image/" + imgurls[i]);
                        i++;
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        InputStream is = conn.getInputStream();
                        img = BitmapFactory.decodeStream(is);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            mThread.start();
            try {
                mThread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            img_data.add(new ImageItem(img));
        }

        imageAdapter.setData(img_data);
        image_recycle.setAdapter(imageAdapter);
        //이미지 데이터 지정, 리사이클에 어뎁터 지정



        //아이콘 리사이클러뷰
        icon_recycle = findViewById(R.id.icon_recycle); // 아이콘 리사이클러뷰
        icon_recycle.setLayoutManager(icon_LayoutManager); //레이아웃 매니저 지정
        iconAdapter = new IconAdapter(); //init 어뎁터

        ArrayList<IconItem> icon_data = new ArrayList<>();
        Bitmap icon_img = ((BitmapDrawable)drawable).getBitmap();

        for (int x = 0; x <10; x++){
            icon_data.add(new IconItem(icon_img,"테스트"));
        }//임시로 아이콘이랑 아이콘 텍스트 넣음

        iconAdapter.setData(icon_data); //set data
        icon_recycle.setAdapter(iconAdapter);


        // 가격 리사이클러뷰
        price_recycle = findViewById(R.id.price_recycle);
        price_recycle.setLayoutManager(price_LayoutManager); //레이아웃 매니저 지정
        priceAdapter = new PriceAdapter(); //init 어뎁터

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String responses) {
                try {
                    JSONArray jsonArray = new JSONArray(responses);
                    JSONObject jsonObjectfirst = jsonArray.getJSONObject(0);
                    boolean success = jsonObjectfirst.getBoolean("success");
                    if (success)//검색 결과 성공
                    {
                        for (int i =0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String campitemcode = jsonObject.getString("campitemcode");
                            String zone = jsonObject.getString("itemname");
                            String zonedesc = jsonObject.getString("itemdesc");
                            String price = jsonObject.getString("price");

                            price_data.add(new PriceItem(zone,zonedesc,price,campitemcode));
                            //*********가격코드 넣어둠 스트링으로 xml에 안보이게 해둠  변수만들어서 넣어면 댐
                        }
                        priceAdapter.notifyDataSetChanged(); //새로고침
                    } else { //검색 결과 없음
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //실제 서버로 Volley를 이용해서 요청을 함.
        DetailInformationRequest detailInformationRequest = new DetailInformationRequest(codeint, responseListener);
        RequestQueue queue = Volley.newRequestQueue(DetailInformation.this);
        queue.add(detailInformationRequest);

        priceAdapter.setData(price_data); //set data
        price_recycle.setAdapter(priceAdapter);
        priceAdapter.setOnItemClickListener(new PriceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                campitemcode = position+1;
            }
        });
        // 가격 리사이클러뷰 end


        //리뷰 리사이클러뷰
        review_recycle = findViewById(R.id.review_recycle);
        review_LayoutManager = new LinearLayoutManager(this); //수직 레이아웃 매니저
        review_LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//수직으로 지정
        review_recycle.setLayoutManager(review_LayoutManager); //레이아웃 매니저 지정
        reviewAdapter = new ReviewAdapter(); //init 어뎁터

        final ArrayList<ReviewItem> review_data = new ArrayList<>();

        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String responses) {
                try {
                    JSONArray jsonArray = new JSONArray(responses);
                    JSONObject jsonObjectfirst = jsonArray.getJSONObject(0);
                    boolean success = jsonObjectfirst.getBoolean("success");
                    if (success)//검색 결과 성공
                    {
                        for (int i =0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            float score = jsonObject.getInt("score");
                            String date = jsonObject.getString("date");
                            String nickname = jsonObject.getString("nickname");
                            String review = jsonObject.getString("review");
                            review_data.add(new ReviewItem(score,date,nickname,review));
                        }
                        reviewAdapter.notifyDataSetChanged(); //새로고침
                    } else { //검색 결과 없음
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //실제 서버로 Volley를 이용해서 요청을 함.
        ReviewRequest reviewRequest = new ReviewRequest(codeint, responseListener2);
        RequestQueue queue2 = Volley.newRequestQueue(DetailInformation.this);
        queue2.add(reviewRequest);

        reviewAdapter.setData(review_data); //set data
        review_recycle.setAdapter(reviewAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.calendar);
        setSupportActionBar(toolbar);

        initViews();

        reservation_btn = findViewById(R.id.reservation_btn);
        reservation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //예약하기 클릭시
                List<Calendar> days = calendarView.getSelectedDates();

                String result="";
                for( int i=0; i<days.size(); i++)
                {
                    Calendar calendar = days.get(i);
                    final int day = calendar.get(Calendar.DAY_OF_MONTH);
                    final int month = calendar.get(Calendar.MONTH);
                    final int year = calendar.get(Calendar.YEAR);
                    String week = new SimpleDateFormat("EE").format(calendar.getTime());
                    //String day_full = year + (month+1) + day  + week;
                    String day_full = Integer.toString(year);
                    day_full = day_full + 0 +(month+1);
                    day_full = day_full + day;
                    //day_full = day_full + week;
                    result += (day_full + "\n");

                    if(i==0){
                        firDay = day_full;
                    }

                    if(i==days.size()-1){
                        endDay = day_full;
                    }
                }


                if(firDay != null){
                    Date now_current = Calendar.getInstance().getTime();
                    SimpleDateFormat now_dateFor= new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                    String now_date = now_dateFor.format(now_current);
                    int today = Integer.parseInt(now_date);
                    int selday = Integer.parseInt(firDay);

                    if(selday >= today){
                        if(campitemcode != 0){
                            Response.Listener<String> responseListener3 = new Response.Listener<String>() {
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
                            ReserveRequest reserveRequest = new ReserveRequest(codeint,campitemcode,userid,firDay,endDay, responseListener3);
                            RequestQueue queue3 = Volley.newRequestQueue(DetailInformation.this);
                            queue3.add(reserveRequest);
                        }else{
                            Toast.makeText(DetailInformation.this, "예약할 구역을 선택해 주세요.", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(DetailInformation.this, "체크인 날짜는 오늘 날짜부터 가능합니다.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(DetailInformation.this, "체크인 날짜를 선택해 주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initViews() {
        calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setCalendarOrientation(OrientationHelper.HORIZONTAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calendar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.clear_selections:
                clearSelectionsMenuClick();
                return true;

            case R.id.show_selections:
                List<Calendar> days = calendarView.getSelectedDates();

                String result="";
                for( int i=0; i<days.size(); i++)
                {
                    Calendar calendar = days.get(i);
                    final int day = calendar.get(Calendar.DAY_OF_MONTH);
                    final int month = calendar.get(Calendar.MONTH);
                    final int year = calendar.get(Calendar.YEAR);

                    String day_full = Integer.toString(year);
                    day_full = day_full + 0 +(month+1);
                    day_full = day_full + day;
                    result += (day_full + "\n");

                    if(i==0){
                        firDay = day_full;
                    }

                    if(i==days.size()-1){
                        endDay = day_full;
                    }
                }
                Toast.makeText(DetailInformation.this, result, Toast.LENGTH_LONG).show();
                System.out.println("체크인 날짜 : "+firDay);
                System.out.println("체크아웃 날짜 : "+endDay);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearSelectionsMenuClick() {
        calendarView.clearSelections();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {//구글맵 마커
        try {
            Geocoder geocoder = new Geocoder(this);
            list1 = geocoder.getFromLocationName
                    (addr1, // 지역 이름
                            10); // 읽을 개수
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        Address addrr = list1.get(0);
        double lat = addrr.getLatitude();
        double lon = addrr.getLongitude();

        LatLng location = new LatLng(lat, lon);//좌표 : 위도,경도
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(nameii);//위치 명
        markerOptions.snippet(addr1);//부가 설명
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));//해당 좌표에 해당 수치만큼 확대
    }
}