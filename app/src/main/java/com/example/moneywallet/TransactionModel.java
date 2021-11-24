package com.example.moneywallet;

public class TransactionModel {

    int id;
    int amount;
    int cid;
    int isExpense;
    int isIncome;
    int isTransfer;
    String date;

    public TransactionModel(int id, int cid, int amount, int isTransfer, int isExpense, int isIncome, String date) {
        this.id = id;
        this.amount = amount;
        this.cid = cid;
        this.isExpense = isExpense;
        this.isIncome = isIncome;
        this.isTransfer = isTransfer;
        this.date = date;
    }

    public TransactionModel(int cid, int amount, int isTransfer, int isExpense, int isIncome, String date) {
        this.amount = amount;
        this.isExpense = isExpense;
        this.isIncome = isIncome;
        this.isTransfer = isTransfer;
        this.date = date;
        this.cid =cid;
    }
}
