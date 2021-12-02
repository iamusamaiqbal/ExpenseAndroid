package com.example.moneywallet;

public class ShoppingItemModel {
    int id;
    String name;
    int amount;
    int sid;

    ShoppingItemModel(int id, String name, int amount, int sid) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.sid = sid;
    }

    ShoppingItemModel(String name, int amount, int sid) {
        this.name = name;
        this.amount = amount;
        this.sid = sid;
    }

}
