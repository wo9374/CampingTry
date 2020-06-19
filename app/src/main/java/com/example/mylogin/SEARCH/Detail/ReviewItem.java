package com.example.mylogin.SEARCH.Detail;

public class ReviewItem {
    private float star;
    private String time;
    private String name;
    private String content;

    public ReviewItem(float star, String time, String name, String content) {
        this.star = star;
        this.time = time;
        this.name = name;
        this.content = content;
    }

    public float getStar() {
        return star;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
