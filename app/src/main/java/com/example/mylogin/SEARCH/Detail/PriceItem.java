package com.example.mylogin.SEARCH.Detail;

public class PriceItem {
    private String zone;
    private String facility;
    private String price;
    private String pricecode;

    public PriceItem( String zone, String facility, String price , String pricecode) {
        this.zone = zone;
        this.facility = facility;
        this.price = price;
        this.pricecode = pricecode;
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

    public String getPricecode() {
        return pricecode;
    }
}
