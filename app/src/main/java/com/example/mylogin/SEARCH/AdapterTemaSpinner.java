package com.example.mylogin.SEARCH;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mylogin.R;

import static com.example.mylogin.SEARCH.Frag2.chk;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_tema,null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.txt_tema);
        ImageView imgTema = (ImageView) convertView.findViewById(R.id.img_tema);
        final CheckBox c1= (CheckBox)convertView.findViewById(R.id.chk_tema);

        tv.setText(tema[position]);
        imgTema.setImageResource(temaImg[position]);
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
