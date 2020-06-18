package com.example.mylogin.SEARCH.Detail;

import android.graphics.Bitmap;

public class PriceItem {
    private String zone;
    private String facility;
    private String price;

    public PriceItem( String zone, String facility, String price) {
        this.zone = zone;
        this.facility = facility;
        this.price = price;
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
