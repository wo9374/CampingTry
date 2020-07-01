package com.example.mylogin.Shop;

import android.graphics.Bitmap;

public class DetailShopImageItem {
    private Bitmap Image_img;

    public DetailShopImageItem(Bitmap image_img) {
        Image_img = image_img;
    }

    public Bitmap getImage_img() {
        return Image_img;
    }

    public void setImage_img(Bitmap image_img) {
        Image_img = image_img;
    }
}
