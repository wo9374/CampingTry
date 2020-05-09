package com.example.mylogin.SNS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.mylogin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Frag1 extends Fragment {

    private View view;

    private BottomNavigationView bottom_navView;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private Sns_home s_frag1;
    private Sns_search s_frag2;
    private Sns_photo s_frag3;
    private Sns_favorite s_frag4;
    private Sns_account s_frag5;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1, container, false);

        bottom_navView = view.findViewById(R.id.top_navigation);
        bottom_navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_searched:
                        setFrag(1);
                        break;
                    case R.id.action_add_photo:
                        setFrag(2);
                        break;
                    case R.id.action_favorite_alarm:
                        setFrag(3);
                        break;
                    case R.id.action_account:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });

        s_frag1 = new Sns_home();
        s_frag2 = new Sns_search();
        s_frag3 = new Sns_photo();
        s_frag4 = new Sns_favorite();
        s_frag5 = new Sns_account();

        setFrag(0);//첫 프래그먼트 화면 지정

        return view;
    }

    //프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n)
    {
        fm = getActivity().getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.main_content, s_frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_content, s_frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_content, s_frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_content, s_frag4);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_content, s_frag5);
                ft.commit();
                break;
        }
    }

}
