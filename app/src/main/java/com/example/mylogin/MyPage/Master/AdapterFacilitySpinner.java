package com.example.mylogin.MyPage.Master;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylogin.R;

import static com.example.mylogin.MyPage.Master.AddCamp.chk;

public class AdapterFacilitySpinner extends BaseAdapter {

    String facility[];
    Context context;
    int facilityImg[];

    public AdapterFacilitySpinner(String[] facility, int[] facilityImg, Context context) {
        this.facility = facility;
        this.context = context;
        this.facilityImg = facilityImg;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_tema,null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.txt_tema);
        ImageView imgTema = (ImageView) convertView.findViewById(R.id.img_tema);
        final CheckBox c1= (CheckBox)convertView.findViewById(R.id.chk_tema);

        tv.setText(facility[position]);
        imgTema.setImageResource(facilityImg[position]);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk[position]==true){
                    chk[position]=false;
                }else{
                    chk[position]=true;
                }
            }
        });
        return convertView;
    }
}
