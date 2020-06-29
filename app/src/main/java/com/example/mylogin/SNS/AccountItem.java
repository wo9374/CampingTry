package com.example.mylogin.SNS;

import android.graphics.Bitmap;

public class AccountItem {
    private String username;
    private String time;
    private String content;
    private String like;
    private String comment;
    private Bitmap image;

    private String snscode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
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
}