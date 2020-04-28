package com.example.mylogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterFacilitySpinner extends BaseAdapter {

    String facility[];
    Context context;
    int facilityImgs[];

    public AdapterFacilitySpinner(String[] tema, int[] temaImg, Context context){
        this.facility= tema;
        this.facilityImgs=temaImg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return facility.length;
    }

    @Override
    public Object getItem(int position) {
        return facility[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_tema,null);
            //스피너테마 레이아웃 그대로 사용
        }

        TextView tv = (TextView) convertView.findViewById(R.id.txt_tema);
        ImageView imgTema = (ImageView) convertView.findViewById(R.id.img_tema);
        CheckBox c1= (CheckBox)convertView.findViewById(R.id.chk_tema);

        tv.setText(facility[position]);
        imgTema.setImageResource(facilityImgs[position]);
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){ //시설에서 체크된 부분 position 값으로 분류

                }
            }
        });
        return convertView;
    }
}
