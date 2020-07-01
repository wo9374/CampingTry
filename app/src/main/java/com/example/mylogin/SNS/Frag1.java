package com.example.mylogin.SNS;

import android.content.Context;
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
import com.example.mylogin.SNS.Account.Account;
import com.example.mylogin.SNS.Home.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Frag1 extends Fragment {

    private View view;
    private Context ct;

    private BottomNavigationView bottom_navView;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private Home s_frag1;
    private Notification s_frag2;
    private Account s_frag3;

    public static String userid, nic;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1, container, false);
        ct = container.getContext();

        if (getArguments() != null) {
            userid = getArguments().getString("userid");
            nic = getArguments().getString("nic");
        }



        bottom_navView = view.findViewById(R.id.top_navigation);
        bottom_navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_favorite_alarm:
                        setFrag(1);
                        break;
                    case R.id.action_account:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        s_frag1 = new Home();
        s_frag2 = new Notification();
        s_frag3 = new Account();

        setFrag(0);//첫 프래그먼트 화면 지정

        return view;
    }

    //프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n)
    {
        fm = getActivity().getSupportFragmentManager();
        ft = fm.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("userid", userid);
        bundle.putString("nic",nic);

        switch (n)
        {
            case 0:
                s_frag1.setArguments(bundle);
                ft.replace(R.id.main_content, s_frag1);
                ft.commit();
                break;
            case 1:
                s_frag2.setArguments(bundle);
                ft.replace(R.id.main_content, s_frag2);
                ft.commit();
                break;
            case 2:
                s_frag3.setArguments(bundle);
                ft.replace(R.id.main_content, s_frag3);
                ft.commit();
                break;
        }
    }
}
