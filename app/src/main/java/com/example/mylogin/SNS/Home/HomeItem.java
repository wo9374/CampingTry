package com.example.mylogin.SNS.Home;

import android.graphics.Bitmap;

public class HomeItem {
    private String username;
    private String time;
    private String content;
    private String like;
    private String comment;
    private Bitmap image;

    private String snscode;
    private String campcode;

    private String title;
    private String url;

    private String like_text;

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getSnscode() {
        return snscode;
    }

    public void setSnscode(String snscode) {
        this.snscode = snscode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCampcode() {
        return campcode;
    }

    public void setCampcode(String campcode) {
        this.campcode = campcode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLike_text() {
        return like_text;
    }

    public void setLike_text(String like_text) {
        this.like_text = like_text;
    }
}
