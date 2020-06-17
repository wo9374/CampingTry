package com.example.mylogin.SEARCH;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mylogin.LoginActivity;
import com.example.mylogin.R;
import com.example.mylogin.RegisterActivity;
import com.example.mylogin.RegisterRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class Frag2 extends Fragment {

    private View view;
    private Context ct;

    private Spinner spinner1,spinner2,spinner3;

    private String[] data;
    private ArrayAdapter<String> adapter;

    private ImageButton btn_search;

    private String[] tema = {"오토캠핑","글램핑","카라반","펜션","피크닉"};
    private int[] temaImgs= new int[5]; //테마 선택 그림 넣을 배열변수
    private EditText keyword;

    private String keyword_txt; //키워드를 쿼리문으로 보낼 스트링 변수
    private String mAdd, sAdd; //스피너 값에 따른 주소찾을 쿼리문으로 보낼 스트링 변수

    public static boolean chk[] = new boolean[6];
    private String tema_chk;
    Bitmap downimg;
    Bitmap img;

    RecyclerView mRecyclerView = null ;
    SearchAdapter mAdapter = null;
    ArrayList<SearchRecycleItem> mList =  new ArrayList<SearchRecycleItem>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);
        ct = container.getContext();
        int j;
        for(j=0; j<chk.length;j++){
            chk[j]= false;
        }

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
                            data = getResources().getStringArray(R.array.서울);
                            break;
                        case 2:
                            data = getResources().getStringArray(R.array.부산);
                            break;
                        case 3:
                            data = getResources().getStringArray(R.array.대구);
                            break;
                        case 4:
                            data = getResources().getStringArray(R.array.인천);
                            break;
                        case 5:
                            data = getResources().getStringArray(R.array.광주);
                            break;
                        case 6:
                            data = getResources().getStringArray(R.array.대전);
                            break;
                        case 7:
                            data = getResources().getStringArray(R.array.울산);
                            break;
                        case 8:
                            data = getResources().getStringArray(R.array.세종);
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
        final AdapterTemaSpinner adapterTemaSpinner = new AdapterTemaSpinner(tema,temaImgs,ct);
        spinner3.setAdapter(adapterTemaSpinner);
        // 어뎁터 써서 만들어둔 spinner_tema에 사진과 텍스트 체크박스를 넣고 갯수만큼 뿌려줌

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_search = view.findViewById(R.id.btn_search); //검색 돋보기 버튼
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear(); // 검색 버튼 누를때마다 초기화

                if (spinner1.getSelectedItemPosition()==0){ //지역 선택안할시 알림창
                    Toast.makeText(ct,"지역을 선택해 주세요.",Toast.LENGTH_LONG).show();
                    mAdapter.notifyDataSetChanged(); //새로고침

                }else{ //지역선택
                    if(spinner2.getSelectedItemPosition()==0){ //시군 선택안하면 선택한 도/시로 전체검색
                        mAdd = spinner1.getSelectedItem().toString();
                        sAdd = "";
                        keyword_txt = keyword.getText().toString();
                        CheckTema();
                        System.out.println(tema_chk); //구해진 tema_chk 스트링으로 테마 참아주삼 테마 체크한거 없으면 모든테마로 쿼리 ㄱㄱ

                    }else{ //시군 선택했을때

                        mAdd = spinner1.getSelectedItem().toString();
                        sAdd = spinner2.getSelectedItem().toString();
                        keyword_txt = keyword.getText().toString();
                        CheckTema();
                        System.out.println(tema_chk); //구해진 tema_chk 스트링으로 테마 참아주삼 테마 체크한거 없으면 모든테마로 쿼리 ㄱㄱ

                    }
                    final Response.Listener<String> responseListener = new Response.Listener<String>() {
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
                                        String code = jsonObject.getString("code");
                                        String name = jsonObject.getString("name");
                                        String addr = jsonObject.getString("addr");
                                        String price = jsonObject.getString("price");
                                        String keyword = jsonObject.getString("keyword");
                                        final String imgurl = jsonObject.getString("imgurl");

                                        Thread mThread = new Thread(){
                                            @Override
                                            public void run(){
                                                try {
                                                    URL url = new URL("http://3.34.136.232:8080/image/" + imgurl);
                                                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                                    conn.setDoInput(true);
                                                    conn.connect();

                                                    InputStream is = conn.getInputStream();
                                                    downimg = BitmapFactory.decodeStream(is);

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
                                            img = downimg;
                                        }catch (InterruptedException e){
                                            e.printStackTrace();
                                        }

                                        addItem(img, name, keyword,price,addr,code);
                                    }

                                    mAdapter.notifyDataSetChanged(); //새로고침

                                } else { //검색 결과 없음
                                    Toast.makeText(ct,"캠핑장 정보가 없습니다.",Toast.LENGTH_LONG).show();
                                    mAdapter.notifyDataSetChanged(); //새로고침
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    //실제 서버로 Volley를 이용해서 요청을 함.
                    SearchRequest searchRequest = new SearchRequest(mAdd, sAdd, keyword_txt, tema_chk, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ct);
                    queue.add(searchRequest);
                } //지역 선택 else 끝

            }
        });

        mRecyclerView = view.findViewById(R.id.search_Recycle);
        mAdapter = new SearchAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));


        return view;
    }//onCreateView end.

    void CheckTema(){
        tema_chk = "";
        for (int y=0; y<chk.length;y++){
            if(chk[y]){
                if(tema_chk.equals("")){
                    tema_chk = String.valueOf(y);;
                }else{
                    tema_chk = tema_chk+","+ y;
                }
            }
        }
    }

    void addItem(Bitmap image, String title, String content, String price, String address, String code){
        SearchRecycleItem item = new SearchRecycleItem();

        item.setImage(image);
        item.setTitle(title);
        item.setContent(content);
        item.setPrice(price);
        item.setAddress(address);
        item.setCode(code);

        mList.add(item);
    }
}
