package com.example.mylogin.SNS.Home;

public class HomeItem {
    private String username;
    private String time;
    private String content;
    private int image;
    private String like;
    private String comment;

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getImage() {
        return image;
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

    public void setImage(int image) {
        this.image = image;
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
}
