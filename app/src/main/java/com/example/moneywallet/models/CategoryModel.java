package com.example.moneywallet.models;

public class CategoryModel {

    public int id;
    public String name;

    public CategoryModel(int id, String name){
        this.id = id;
        this.name = name;
    }

    CategoryModel(String name){
        this.name = name;
    }

}
