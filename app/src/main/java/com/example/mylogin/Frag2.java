package com.example.mylogin;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Frag2 extends Fragment {

    private View view;
    private Context ct;

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;

    public String[] data;
    ArrayAdapter<String> adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);
        ct = container.getContext();

        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        spinner3 = view.findViewById(R.id.spinner3);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //전체 도 및 광역시 스피너
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    data = getResources().getStringArray(R.array.시군);
                }else{
                    switch (position){ //도 및 광역시 스피너에 따라 행정구역 시/군 변경
                        case 1:
                            data = getResources().getStringArray(R.array.서울시);
                            break;
                        case 2:
                            data = getResources().getStringArray(R.array.부산시);
                            break;
                        case 3:
                            data = getResources().getStringArray(R.array.대구시);
                            break;
                        case 4:
                            data = getResources().getStringArray(R.array.인천시);
                            break;
                        case 5:
                            data = getResources().getStringArray(R.array.광주시);
                            break;
                        case 6:
                            data = getResources().getStringArray(R.array.대전시);
                            break;
                        case 7:
                            data = getResources().getStringArray(R.array.울산시);
                            break;
                        case 8:
                            data = getResources().getStringArray(R.array.세종시);
                            break;
                        case 9:
                            data = getResources().getStringArray(R.array.경기도);
                            break;
                        case 10:
                            data = getResources().getStringArray(R.array.강원도);
                            break;
                        case 11:
                            data = getResources().getStringArray(R.array.충청북도);
                            break;
                        case 12:
                            data = getResources().getStringArray(R.array.충청남도);
                            break;
                        case 13:
                            data = getResources().getStringArray(R.array.전라북도);
                            break;
                        case 14:
                            data = getResources().getStringArray(R.array.전라남도);
                            break;
                        case 15:
                            data = getResources().getStringArray(R.array.경상북도);
                            break;
                        case 16:
                            data = getResources().getStringArray(R.array.경상남도);
                            break;
                        case 17:
                            data = getResources().getStringArray(R.array.제주도);
                            break;
                    }
                }
                adapter = new ArrayAdapter<String>(ct, R.layout.support_simple_spinner_dropdown_item,data);
                spinner2.setAdapter(adapter);
            } //spinner1 selected end

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });//spinner1 selected litener end

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 시/군 선택부분
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });//spinner2 selected end

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 테마 선택 부분
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });//spinner3 selected end


        return view;
    }
}
