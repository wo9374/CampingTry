package com.example.mylogin.Shop;

import android.graphics.Bitmap;

public class ShopItem {
    Bitmap image;
    String name;
    String price;
    String shopcode;

    public ShopItem(Bitmap image, String name, String price, String shopcode) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.shopcode = shopcode;
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

    public String getShopcode() {
        return shopcode;
    }

    public void setShopcode(String shopcode) {
        this.shopcode = shopcode;
    }
}
