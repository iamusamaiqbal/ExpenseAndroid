package com.example.moneywallet.models;

public class ShoppingListModel {

    public int id;
    public String name;
    public int totalitem;
    public int amount;

    public ShoppingListModel(int id, String name, int totalitem, int amount) {
        this.id = id;
        this.name = name;
        this.totalitem = totalitem;
        this.amount = amount;
    }

    public ShoppingListModel(String name, int totalitem, int amount) {
        this.name = name;
        this.totalitem = totalitem;
        this.amount = amount;
    }
}
