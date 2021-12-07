package com.example.moneywallet.models;

public class GoalModel {
    public int id;
    public String name;
    public String note;
    public String date;
    public String status;
    public int amount;
    public int alreadySaved;

    public GoalModel(int id, String name, String note, String date, int amount, int alreadySaved,String status) {
        this.alreadySaved = alreadySaved;
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.note = note;
        this.date = date;
        this.status = status;
    }

    public GoalModel(String name, String note, String date, int amount, int alreadySaved, String status) {
        this.alreadySaved = alreadySaved;
        this.amount = amount;
        this.name = name;
        this.note = note;
        this.date = date;
        this.status = status;
    }
}
