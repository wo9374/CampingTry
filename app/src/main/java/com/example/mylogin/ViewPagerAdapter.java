package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {//프래그먼트 위치에 따라서 어떤 이미지를 표시할지
        switch (position)
        {
            case 0:
                return FragSearchID.newInstance();
            case 1:
                return FragSearchPass.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() { // 프래그먼트 갯수 명시
        return 2;
    }

    //상단의 탭 레이아웃 인디케이터에 텍스트 선언
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "아이디 찾기";
            case 1:
                return "비밀번호 찾기";
            default:
                return null;
        }
    }
}
