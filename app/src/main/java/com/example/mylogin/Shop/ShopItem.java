package com.example.mylogin.Shop;

import android.graphics.Bitmap;

public class ShopItem {
    Bitmap image;
    String name;
    String price;
    String shopcode;
    String userid;

    String url;
    String desc;

    public ShopItem(Bitmap image, String name, String price, String shopcode, String userid, String url, String desc) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.shopcode = shopcode;
        this.userid = userid;
        this.url = url;
        this.desc = desc;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
