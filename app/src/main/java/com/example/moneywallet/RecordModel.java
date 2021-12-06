package com.example.moneywallet;

public class RecordModel {

    int id;
    String action;
    String account;
    int did;
    int amount;

    RecordModel(int id, String action, String account, int did, int amount) {
        this.id = id;
        this.account = account;
        this.action = action;
        this.did = did;
        this.amount = amount;
    }

    RecordModel(String action, String account, int did, int amount) {
        this.account = account;
        this.action = action;
        this.did = did;
        this.amount = amount;
    }
}
