package com.example.mylogin.MyPage.Master;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.mylogin.R;
import java.util.ArrayList;

public class AddPriceAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<AddPriceItem> sample;

    public AddPriceAdapter(Context context, ArrayList<AddPriceItem> data){
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public Object getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.holder_add_price, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView content = (TextView) view.findViewById(R.id.content);
        TextView price = (TextView) view.findViewById(R.id.price);

        title.setText(sample.get(position).getTitle());
        content.setText(sample.get(position).getContent());
        price.setText(sample.get(position).getPrice());

        return view;
    }
}
