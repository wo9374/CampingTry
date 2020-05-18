package com.example.mylogin.SEARCH;

public class SearchModel {
    String imageuri;
    String title_address;
    String title_name;
    String content;

    String address;

    SearchModel(){}

    SearchModel(String imageuri, String title_address,String title_name, String content , String address){
        this.imageuri = imageuri;
        this.title_address = title_address;
        this.title_name=title_name;
        this.content = content;
        this.address=address;
    }
}
