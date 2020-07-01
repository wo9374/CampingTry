package com.example.mylogin.Shop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylogin.R;

import java.util.ArrayList;

public class ShopAdapter extends BaseAdapter {
    ArrayList<ShopItem> items = new ArrayList<ShopItem>();
    Context context;

    public void addItem(ShopItem item){
        items.add(item);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext(); //콘텍스트 객체는 엑티비티 정보를 읽어올수 있다
        ShopItem shopItem = items.get(position); //position에 해당하는 샵아이템

        //샵아이템을 inflate하고 convertView를 참조
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.holder_shop, parent, false);
        }

        //xml에 이미지뷰와 텍스트뷰 참조
        ImageView icon_img = convertView.findViewById(R.id.icon_img);
        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);
        final TextView shop_code = convertView.findViewById(R.id.shop_code);


        icon_img.setImageBitmap(shopItem.getImage());
        name.setText(shopItem.getName());
        price.setText(shopItem.getPrice());
        shop_code.setText(shopItem.getShopcode());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailShop.class);
                intent.putExtra("shopcode",shop_code.getText());
                v.getContext().startActivity(intent);
            }
        });

        return convertView; //뷰 객체 반환
    }
}
