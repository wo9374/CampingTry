package com.example.mylogin.Chat;

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

public class Frag5 extends Fragment {

    private View view;

    private BottomNavigationView bottom_navView;
    private FragmentManager fm;
    private FragmentTransaction ft;

    public static String userid, nic;

    private Chat_People c_frag1;
    private Chat_Room c_frag2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag5, container, false);

        bottom_navView = view.findViewById(R.id.top_navigation);
        bottom_navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.chat_people:
                        setFrag(0);
                        break;
                    case R.id.chat_room:
                        setFrag(1);
                        break;
                }
                return true;
            }
        });

        c_frag1 = new Chat_People();
        c_frag2 = new Chat_Room();

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
                c_frag1.setArguments(bundle);
                ft.replace(R.id.main_content, c_frag1);
                ft.commit();
                break;
            case 1:
                c_frag2.setArguments(bundle);
                ft.replace(R.id.main_content, c_frag2);
                ft.commit();
                break;
        }
    }
}
