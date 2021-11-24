package com.example.moneywallet;

public class CategoryModel {

    int id;
    String name;

    CategoryModel(int id, String name){
        this.id = id;
        this.name = name;
    }

    CategoryModel(String name){
        this.name = name;
    }

}
