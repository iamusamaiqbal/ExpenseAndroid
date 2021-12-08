package com.example.moneywallet.models;

public class DebtModel {

    public int id;
    public String name;
    public String description;
    public String account;
    public String date;
    public String duedate;
    public int amount;
    public String type;
    public int isActive;

    public DebtModel(int id, String name, String description, String account, String date, String duedate, int amount, String type, int isActive){

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

    public DebtModel(String name, String description, String account, String date, String duedate, int amount, String type, int isActive){

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
