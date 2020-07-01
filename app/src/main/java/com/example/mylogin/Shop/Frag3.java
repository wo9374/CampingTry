package com.example.mylogin.Shop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mylogin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Frag3 extends Fragment {

    private View view;
    GridView shop_gridview;
    ShopAdapter mAdapter;

    Drawable drawable;
    Bitmap img;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3, container, false);

        shop_gridview = view.findViewById(R.id.shop_gridview);
        mAdapter = new ShopAdapter();

        drawable = getResources().getDrawable(R.drawable.tema_4);
        img = ((BitmapDrawable) drawable).getBitmap();

        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        addItem(img,"물건이름","10000","1","test12");
        shop_gridview.setAdapter(mAdapter);

        FloatingActionButton fab = view.findViewById(R.id.write_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddShop.class);
                startActivity(intent);
            }
        });
        return view;
    }

    void addItem(Bitmap image, String name, String price,String shopcode,String userid){
        mAdapter.addItem(new ShopItem(image,name, price,shopcode,userid));
    }
}
