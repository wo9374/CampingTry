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

public class AdapterTemaSpinner extends BaseAdapter {

    String tema[];
    Context context;
    int temaImg[];

    public AdapterTemaSpinner(String[] tema, int[] temaImg,Context context){
        this.tema= tema;
        this.temaImg=temaImg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tema.length;
    }

    @Override
    public Object getItem(int position) {
        return tema[position];

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_tema,null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.txt_tema);
        tv.setText(tema[position]);

        ImageView imgTema = (ImageView) convertView.findViewById(R.id.img_tema);
        imgTema.setImageResource(temaImg[position]);

        CheckBox c1= (CheckBox)convertView.findViewById(R.id.chk_tema);
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                }
            }
        });
        return convertView;
    }
}
