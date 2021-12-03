package com.example.moneywallet;

public class ShoppingItemModel {
    int id;
    String name;
    int amount;
    int sid;
    int isChecked;

    ShoppingItemModel(int id, String name, int amount, int sid, int isChecked) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.sid = sid;
        this.isChecked = isChecked;
    }

    ShoppingItemModel(String name, int amount, int sid, int isChecked) {
        this.name = name;
        this.amount = amount;
        this.sid = sid;
        this.isChecked = isChecked;
    }

}
