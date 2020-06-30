package com.example.mylogin.MyPage.Master;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import com.example.mylogin.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddCamp extends AppCompatActivity {

    String id;

    private Spinner tema_spinner,facility_spinner;

    private String[] data;
    private ArrayAdapter<String> adapter;

    private String[] facility = {"전기","온수","산책로","운동시설","와이파이","장작대여","편의점","운동장","수영장","장비대여","동물동반"};
    private int[] facilityImgs= new int[11]; //테마 선택 그림 넣을 배열변수
    public static boolean chk[] = new boolean[11];
    private String facility_chk;

    private EditText camp_name,camp_addr,camp_tel, camp_nature , price_name,price,price_content;
    private Button album_btn,add_price,add_camp_btn;

    ArrayList<AddPriceItem> priceDataList;

    private ArrayList<String> p_title;
    private ArrayList<String> p_content;
    private ArrayList<String> p_price ;

    final static int REQUEST_TAKE_ALBUM  = 2;

    String imageFileName;
    String imageFileNamePlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camp);

        Intent intent = getIntent();
        id = intent.getExtras().getString("id"); //아이디 불러옴

        int j;
        for(j=0; j<chk.length;j++){
            chk[j]= false;
        }

        tema_spinner = findViewById(R.id.tema_spinner);
        facility_spinner = findViewById(R.id.facility_spinner);


        data = getResources().getStringArray(R.array.테마);
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,data);
        tema_spinner.setAdapter(adapter);



        for (int i=0; i<facilityImgs.length;i++){
            facilityImgs[i] = getResources().getIdentifier("icon_facility_"+i,"drawable","com.example.mylogin");
            //시설선택 스피너에 넣을 이미지
        }
        final AdapterFacilitySpinner adapterFacilitySpinner = new AdapterFacilitySpinner(facility,facilityImgs,this);
        facility_spinner.setAdapter(adapterFacilitySpinner);


        camp_name = findViewById(R.id.camp_name);
        camp_addr = findViewById(R.id.camp_addr);
        camp_tel = findViewById(R.id.camp_tel);
        camp_nature = findViewById(R.id.keyword);


        album_btn = findViewById(R.id.album_btn);
        album_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카메라 권한부분은 MainActivity에서 미리 받고 있음
                getAlbum();
            }
        });



        price_name = findViewById(R.id.price_name);
        price = findViewById(R.id.price);
        price_content = findViewById(R.id.price_content);

        priceDataList = new ArrayList<AddPriceItem>();

        final ListView price_list = (ListView)findViewById(R.id.price_list);
        final AddPriceAdapter addPriceAdapter = new AddPriceAdapter(this,priceDataList);
        price_list.setAdapter(addPriceAdapter);

        p_title = new ArrayList<>();
        p_content = new ArrayList<>();
        p_price = new ArrayList<>();

        add_price= findViewById(R.id.add_price);
        add_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceDataList.add(new AddPriceItem(price_name.getText().toString(),price_content.getText().toString(),price.getText().toString()));
                addPriceAdapter.notifyDataSetChanged();

                p_title.add(price_name.getText().toString());
                p_content.add(price_content.getText().toString());
                p_price.add(price.getText().toString());
            }
        });


        add_camp_btn = findViewById(R.id.add_camp_btn);
        add_camp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //camp_name.getText(); //캠핑장 이름
                //camp_addr.getText(); //캠핑장 주소
                //camp_tel.getText(); //문의처 전화번호
                //camp_nature.getText(); //캠핑장 환경

                int select_tema = tema_spinner.getSelectedItemPosition()+1; //선택된 테마

                CheckTema();
                System.out.println(facility_chk); // facility_chk 이 배열이 선택한 체크한 시설정보

                for (int i=0; i< priceDataList.size(); i++){
                    //System.out.println(p_title.get(i));
                    //System.out.println(p_content.get(i));
                    //System.out.println(p_price.get(i));
                }

            }
        });
    }

    void CheckTema(){
        facility_chk = "";
        for (int y=0; y<chk.length; y++){
            if(chk[y]){
                if(facility_chk.equals("")){
                    facility_chk = String.valueOf(y);
                }else{
                    facility_chk = facility_chk+","+ y;
                }
            }
        }
    }

    private void getAlbum() {
        Intent getAlbumintent = new Intent(Intent.ACTION_PICK);
        getAlbumintent.setType("image/*");
        getAlbumintent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        getAlbumintent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);

        startActivityForResult(getAlbumintent, REQUEST_TAKE_ALBUM);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = id + "_" + timeStamp;
        imageFileNamePlus = imageFileName + ".jpg";
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case REQUEST_TAKE_ALBUM:{
                if (resultCode == RESULT_OK) {
                    if (intent.getData()!=null){
                        try{
                           // photoURI = intent.getData();
                           // photo.setImageURI(photoURI);
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.v("알림","앨범에서 가져오기 에러");
                        }
                    }
                }
                break;
            }
        }
    }
}
