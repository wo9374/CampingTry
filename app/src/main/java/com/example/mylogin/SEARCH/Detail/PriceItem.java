package com.example.mylogin.SEARCH.Detail;

import android.graphics.Bitmap;

public class PriceItem {
    private Bitmap price_img;
    private String zone;
    private String facility;
    private String price;

    public PriceItem(Bitmap price_img, String zone, String facility, String price) {
        this.price_img = price_img;
        this.zone = zone;
        this.facility = facility;
        this.price = price;
    }

    public Bitmap getPrice_img() {
        return price_img;
    }

    public String getZone() {
        return zone;
    }

    public String getFacility() {
        return facility;
    }

    public String getPrice() {
        return price;
    }
}
