package com.example.moneywallet.models;

public class BudgetModel {

    public int id;
    public String name;
    public int amount;
    public String currency;
    public int category;
    public String account;
    public String start;
    public String end;

    public BudgetModel(int id, String name, int amount, int category, String account, String currency, String start, String end) {
        this.account = account;
        this.amount = amount;
        this.category = category;
        this.name = name;
        this.id = id;
        this.currency = currency;
        this.start = start;
        this.end = end;
    }

    public BudgetModel(String name, int amount, int category, String account, String currency, String start, String end) {
        this.account = account;
        this.amount = amount;
        this.category = category;
        this.name = name;
        this.currency = currency;
        this.start = start;
        this.end = end;
    }

}
