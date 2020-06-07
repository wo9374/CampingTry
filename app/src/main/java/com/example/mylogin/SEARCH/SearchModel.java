package com.example.mylogin.SEARCH;

public class SearchModel {
    String imageuri;
    String title_name;
    String content;
    String review;
    String address;

    SearchModel(){}

    SearchModel(String imageuri, String title_name, String content , String address, String review){
        this.imageuri = imageuri;
        this.title_name=title_name;
        this.content = content;
        this.address=address;
        this.review = review;
    }
}
