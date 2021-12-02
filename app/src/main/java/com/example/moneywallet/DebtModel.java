package com.example.moneywallet;

public class DebtModel {

    int id;
    String name;
    String description;
    String account;
    String date;
    String duedate;
    int amount;
    String type;
    int isActive;

    DebtModel(int id, String name, String description, String account, String date, String duedate, int amount, String type, int isActive ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.account = account;
        this.date = date;
        this.duedate = duedate;
        this.amount = amount;
        this.type = type;
        this.isActive = isActive;
    }

    DebtModel(String name, String description, String account, String date, String duedate, int amount, String type, int isActive){

        this.name = name;
        this.description = description;
        this.account = account;
        this.date = date;
        this.duedate = duedate;
        this.amount = amount;
        this.type = type;
        this.isActive = isActive;
    }
}
