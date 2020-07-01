package com.example.mylogin.Shop;

import android.graphics.Bitmap;

public class ShopItem {
    Bitmap image;
    String name;
    String price;

    public ShopItem(Bitmap image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
