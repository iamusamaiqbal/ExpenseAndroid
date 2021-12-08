package com.example.moneywallet.models;

public class RecordModel {

    public int id;
    public String action;
    public String account;
    public int did;
    public int amount;

    public RecordModel(int id, String action, String account, int did, int amount) {
        this.id = id;
        this.account = account;
        this.action = action;
        this.did = did;
        this.amount = amount;
    }

    public RecordModel(String action, String account, int did, int amount) {
        this.account = account;
        this.action = action;
        this.did = did;
        this.amount = amount;
    }
}
