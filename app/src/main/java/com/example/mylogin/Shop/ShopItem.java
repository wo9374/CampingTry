package com.example.mylogin.Shop;

import android.graphics.Bitmap;

public class ShopItem {
    Bitmap image;
    String name;
    String price;
    String shopcode;
    String userid;

    public ShopItem(Bitmap image, String name, String price, String shopcode, String userid) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.shopcode = shopcode;
        this.userid = userid;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
