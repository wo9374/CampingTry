package com.example.mylogin.SEARCH.Detail;

import android.graphics.Bitmap;

public class IconItem {
    private Bitmap icon_img;
    private String icon_txt;

    public IconItem(Bitmap icon_img, String icon_txt) {
        this.icon_img = icon_img;
        this.icon_txt = icon_txt;
    }

    public Bitmap getIcon_img() {
        return icon_img;
    }

    public String getIcon_txt() {
        return icon_txt;
    }
}
