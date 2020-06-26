package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mylogin.Chat.Frag5;
import com.example.mylogin.MyPage.MyPage;
import com.example.mylogin.SEARCH.Frag2;
import com.example.mylogin.SNS.Frag1;
import com.example.mylogin.Shop.ShopFrag;
import com.example.mylogin.WebView.WebViewActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private DrawerLayout drawerLayout;
    private View drawerView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;
    private Frag5 frag5;

    private TextView tv_id, tv_nic;
    private ImageView tv_profile;
    private String userID, userSubname, userPass, userName, userEmail;

    Context context;


    private long time= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_sns:
                        setFrag(0);
                        break;
                    case R.id.action_search:
                        setFrag(1);
                        break;
                    case R.id.action_shopping:
                        setFrag(2);
                        break;
                    case R.id.action_map:
                        setFrag(3);
                        break;
                    case R.id.action_chat:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });

        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        frag4 = new Frag4();
        frag5 = new Frag5();
        setFrag(0);//첫 프래그먼트 화면 지정



        tv_id = findViewById(R.id.tv_id);
        tv_nic = findViewById(R.id.tv_nic);
        tv_profile = findViewById(R.id.tv_profile);



        //넘겨받은 계정 정보
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userSubname = intent.getStringExtra("userSubname");
        userPass = intent.getStringExtra("userPass");
        userName = intent.getStringExtra("userName");
        userEmail = intent.getStringExtra("userEmail");
        String photoUrl = intent.getStringExtra("photoUrl");    //프로필 이미지 가져오기



        context = this.getBaseContext();

        tv_id.setText(userID);
        tv_nic.setText(userSubname);
        Glide.with(this).load(photoUrl).into(tv_profile);   //프로필 이미지 표시



        //사이드 바
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        Button btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });



        //마이페이지 버튼
        Button d_mypage = (Button)findViewById(R.id.d_mypage);
        d_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MyPage.class);
                intent1.putExtra("id", userID);
                intent1.putExtra("nic", userSubname);
                intent1.putExtra("pass", userPass);
                intent1.putExtra("name", userName);
                startActivity(intent1);
            }
        });


        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        checkPermissions();
    }



    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(context, "권한 허용을 하지 않으면 서비스를 이용할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    };

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) { // 마시멜로(안드로이드 6.0) 이상 권한 체크
            TedPermission.with(context)
                    .setPermissionListener(permissionlistener)
                    .setRationaleMessage("앱을 이용하기 위해서는 접근 권한이 필요합니다")
                    .setDeniedMessage("앱에서 요구하는 권한설정이 필요합니다...\n [설정] > [권한] 에서 사용으로 활성화해주세요.")
                    .setPermissions(new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                     })
                    .check();

        } else {
        }
    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override//슬라이드 했을시
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override//오픈상황
        public void onDrawerOpened(@NonNull View drawerView) {
            Button go_camping = (Button)findViewById(R.id.go_camping);
            go_camping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    startActivity(intent);
                }
            });
        }

        @Override//슬라이드메뉴 닫혔을때
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override//상태 체인지
        public void onDrawerStateChanged(int newState) {

        }
    };

    //프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.main_frame, frag1);
                ft.commit();
                break;
            case 1:
                Bundle bundle1 = new Bundle();
                bundle1.putString("userid", userID);
                Frag2 frag2 = new Frag2();
                frag2.setArguments(bundle1);
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, frag4);
                ft.commit();
                break;
            case 4:
                Bundle bundle = new Bundle();
                bundle.putString("nic",userSubname);
                Frag5 frag5 = new Frag5();
                frag5.setArguments(bundle);
                ft.replace(R.id.main_frame, frag5);
                ft.commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 \n로그아웃 됩니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
        }
    }
}
