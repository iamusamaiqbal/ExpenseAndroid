package com.example.moneywallet;

public class GoalModel {
    int id;
    String name;
    String note;
    String date;
    String status;
    int amount;
    int alreadySaved;

    GoalModel(int id, String name, String note, String date, int amount, int alreadySaved,String status) {
        this.alreadySaved = alreadySaved;
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.note = note;
        this.date = date;
        this.status = status;
    }

    GoalModel(String name, String note, String date, int amount, int alreadySaved, String status) {
        this.alreadySaved = alreadySaved;
        this.amount = amount;
        this.name = name;
        this.note = note;
        this.date = date;
        this.status = status;
    }
}
