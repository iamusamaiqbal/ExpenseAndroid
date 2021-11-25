package com.example.moneywallet;

public class TransactionModel {

    int id;
    int amount;
    String cat;
    int isExpense;
    int isIncome;
    int isTransfer;
    String date;

    public TransactionModel(int id, String cat, int amount, int isTransfer, int isExpense, int isIncome, String date) {
        this.id = id;
        this.amount = amount;
        this.cat = cat;
        this.isExpense = isExpense;
        this.isIncome = isIncome;
        this.isTransfer = isTransfer;
        this.date = date;
    }

    public TransactionModel(String cat, int amount, int isTransfer, int isExpense, int isIncome, String date) {
        this.amount = amount;
        this.isExpense = isExpense;
        this.isIncome = isIncome;
        this.isTransfer = isTransfer;
        this.date = date;
        this.cat =cat;
    }
}
