package com.example.mylogin.SEARCH;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylogin.AdapterFacilitySpinner;
import com.example.mylogin.AdapterTemaSpinner;
import com.example.mylogin.R;

public class Frag2 extends Fragment {

    private View view;
    private Context ct;

    private Spinner spinner1,spinner2,spinner3;

    private String[] data;
    private ArrayAdapter<String> adapter;

    private ImageButton btn_search;

    private String[] tema = {"전체테마","해변","섬","산","숲","계곡","강","호수","도심"};
    private int[] temaImgs= new int[9]; //테마 선택 그림 넣을 배열변수

    private String[] facility = {"전체시설","캠핑장비대여","애완동물 출입","물놀이 시설","편의점","운동시설"};
    private int[] facilityImgs = new int[6]; //시설 선택 그림 넣을 배열변수

    private EditText keyword;

    private String keyword_txt; //키워드를 쿼리문으로 보낼 스트링 변수
    private String mAdd, sAdd; //스피너 값에 따른 주소찾을 쿼리문으로 보낼 스트링 변수
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);
        ct = container.getContext();

        keyword = view.findViewById(R.id.keyword); //키워드 창

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
        });//spinner1 selected listener end



        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 시/군 선택부분

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });//spinner2 selected end




        for (int i=0; i<temaImgs.length;i++){
            temaImgs[i] = getResources().getIdentifier("tema_"+i,"drawable","com.example.mylogin");
            //테마선택 스피너에 넣을 이미지
        }
        AdapterTemaSpinner adapterTemaSpinner = new AdapterTemaSpinner(tema,temaImgs,ct);
        spinner3.setAdapter(adapterTemaSpinner);
        // 어뎁터 써서 만들어둔 spinner_tema에 사진과 텍스트 체크박스를 넣고 갯수만큼 뿌려줌


        btn_search = view.findViewById(R.id.btn_search); //검색 돋보기 버튼
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner1.getSelectedItemPosition()==0){ //지역 선택안할시 알림창
                    Toast.makeText(ct,"지역을 선택해 주세요.",Toast.LENGTH_LONG).show();
                }else{ //지역선택
                    if(spinner2.getSelectedItemPosition()==0){ //시군 선택안하면 선택한 도/시로 전체검색

                        mAdd = spinner1.getSelectedItem().toString();
                        keyword_txt = keyword.getText().toString();
                    }else{ //시군 선택했을때

                        mAdd = spinner1.getSelectedItem().toString();
                        sAdd = spinner2.getSelectedItem().toString();
                        keyword_txt = keyword.getText().toString();
                    }
                } //지역 선택 else 끝
            }
        });

        return view;
    }//onCreateView end.
}
