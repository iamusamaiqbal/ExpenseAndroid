package com.example.moneywallet;

public class BudgetModel {

    int id;
    String name;
    int amount;
    String currency;
    int category;
    String account;
    String start;
    String end;

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
