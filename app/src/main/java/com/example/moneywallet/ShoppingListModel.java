package com.example.moneywallet;

class ShoppingListModel {

    int id;
    String name;
    int totalitem;
    int amount;

    ShoppingListModel(int id, String name, int totalitem, int amount) {
        this.id = id;
        this.name = name;
        this.totalitem = totalitem;
        this.amount = amount;
    }

    ShoppingListModel(String name, int totalitem, int amount) {
        this.name = name;
        this.totalitem = totalitem;
        this.amount = amount;
    }
}
