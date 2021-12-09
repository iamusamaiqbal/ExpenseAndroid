package com.example.moneywallet.models;

public class RecordModel {

    public int id;
    public String action;
    public String account;
    public int did;
    public int amount;
    public String date;

    public RecordModel(int id, String action, String account, int did, int amount, String date) {
        this.id = id;
        this.account = account;
        this.action = action;
        this.did = did;
        this.amount = amount;
        this.date = date;
    }

    public RecordModel(String action, String account, int did, int amount, String date) {
        this.account = account;
        this.action = action;
        this.did = did;
        this.amount = amount;
        this.date = date;
    }
}
