package com.example.moneywallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler extends SQLiteOpenHelper {

    public static String TABLE_TRANSACTION = "TransactionTable";

    public static String KEY_TID = "id";
    public static String KEY_CAT = "cat";
    public static String KEY_AMOUNT = "amount";
    public static String KEY_ISEXPENSE = "isexpense";
    public static String KEY_ISINCOME = "isincome";
    public static String KEY_ISTRANSFER = "istransfer";
    public static String KEY_DATE = "time";


    public static String TABLE_CATEGORY = "CategoryTable";

    public static String KEY_CID = "cid";
    public static String KEY_NAME = "name";


    public static String TABLE_BUDGET = "BudgetTable";

    public static String KEY_BID = "id";
    public static String KEY_BAMOUNT = "amount";
    public static String KEY_BNAME = "name";
    public static String KEY_BCURRENCY = "currency";
    public static String KEY_BACCOUNT = "account";
    public static String KEY_BSTARTDATE = "start_date";
    public static String KEY_BENDDATE = "end_date";


    public SQLiteHandler(@Nullable Context context) {
        super(context, "ExpenseDB4", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_EXPENSE_TABLE = "CREATE TABLE " + TABLE_TRANSACTION + "("
                + KEY_TID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_AMOUNT + " INTEGER,"
                + KEY_CAT + " VARCHAR(20),"
                + KEY_ISEXPENSE + " BOOLEAN,"
                + KEY_ISINCOME + " BOOLEAN,"
                + KEY_ISTRANSFER + " BOOLEAN,"
                + KEY_DATE + " DATETIME)";

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
                + KEY_CID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " VARCHAR(20))";


        String CREATE_BUDGET_TABLE = "CREATE TABLE " + TABLE_BUDGET + "("
                + KEY_BID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_BAMOUNT + " INTEGER,"
                + KEY_CAT + " VARCHAR(20),"
                + KEY_BNAME + " VARCHAR(20),"
                + KEY_BACCOUNT + " VARCHAR(10),"
                + KEY_BCURRENCY + " VARCHAR(5),"
                + KEY_BSTARTDATE + " VARCHAR(30),"
                + KEY_BENDDATE + " VARCHAR(30))";

        db.execSQL(CREATE_EXPENSE_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_BUDGET_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Transaction CRUD Operation

    public void addTransaction(TransactionModel transactionModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, transactionModel.amount);
        values.put(KEY_CAT, transactionModel.cat);
        values.put(KEY_ISEXPENSE, transactionModel.isExpense);
        values.put(KEY_ISINCOME, transactionModel.isIncome);
        values.put(KEY_ISTRANSFER, transactionModel.isTransfer);
        values.put(KEY_DATE, transactionModel.date);


        db.insert(TABLE_TRANSACTION, null, values);
        //db.close();

    }

    public void updateTransaction(TransactionModel transaction, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, transaction.amount);
        values.put(KEY_ISEXPENSE, transaction.isExpense);
        values.put(KEY_ISINCOME, transaction.isIncome);
        values.put(KEY_ISTRANSFER, transaction.isTransfer);
        values.put(KEY_DATE, transaction.date);

        db.update(TABLE_TRANSACTION, values, "" + KEY_TID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public TransactionModel getTransaction(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TRANSACTION, new String[]{KEY_TID, KEY_CID,
                        KEY_AMOUNT, KEY_ISEXPENSE, KEY_ISTRANSFER, KEY_ISINCOME, KEY_DATE}, KEY_TID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        TransactionModel transaction = new TransactionModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_CAT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_AMOUNT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ISEXPENSE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ISINCOME)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ISTRANSFER)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATE))
        );

        //db.close();
        return transaction;
    }

    public List<TransactionModel> getAllTransaction() {
        List<TransactionModel> transactionList = new ArrayList<TransactionModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTION + " ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TransactionModel transaction = new TransactionModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_CAT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_AMOUNT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ISTRANSFER)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ISEXPENSE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ISINCOME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATE))
                );
                transactionList.add(transaction);
            } while (cursor.moveToNext());
        }
        db.close();
        return transactionList;
    }

    public List<TransactionModel> getAllTransactionByCat(String column) {
        List<TransactionModel> transactionList = new ArrayList<TransactionModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTION + " WHERE "+ KEY_CAT +" =? ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{column});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TransactionModel transaction = new TransactionModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_CAT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_AMOUNT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ISEXPENSE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ISINCOME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ISTRANSFER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATE))
                );
                transactionList.add(transaction);
            } while (cursor.moveToNext());
        }
        db.close();
        return transactionList;
    }

    public boolean deleteTransaction(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        boolean flag = db.delete(TABLE_TRANSACTION, KEY_TID + "=?", new String[]{String.valueOf(id)}) > 0;

        db.close();
        return flag;
    }

    public int getSum(String key){
        String selectQuery = "SELECT (SUM(" +key+ ")) AS total FROM " + TABLE_TRANSACTION + " ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        } else {
            return 0;
        }
    }

    public int getSumByCat(String sum, String column){
        String selectQuery = "SELECT (SUM(" +sum+ ")) AS total FROM " + TABLE_TRANSACTION + " WHERE "+KEY_CAT+" =? ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{column});
        if (cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        } else {
            return 0;
        }
    }

    public int getSumByDate(String sum, String date){
        String selectQuery = "SELECT (SUM(" +sum+ ")) AS total FROM " + TABLE_TRANSACTION + " WHERE DATE ("+KEY_DATE+") =? ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{date});
        if (cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        } else {
            return 0;
        }
    }

    public int getSumByDateExpense(String sum, String date){
        String selectQuery = "SELECT (SUM(" +sum+ ")) AS total FROM " + TABLE_TRANSACTION + " WHERE DATE ("+KEY_DATE+") =? AND ("+ KEY_ISEXPENSE+ ") = 1 ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{date});
        if (cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        } else {
            return 0;
        }
    }

    public int getSumByDateIncome(String sum, String date){
        String selectQuery = "SELECT (SUM(" +sum+ ")) AS total FROM " + TABLE_TRANSACTION + " WHERE DATE ("+KEY_DATE+") =? AND ("+ KEY_ISINCOME +") = 1 ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{date});
        if (cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        } else {
            return 0;
        }
    }

    //Category CRUD Operation

    public void addCategory(CategoryModel category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, category.name);

        db.insert(TABLE_CATEGORY, null, values);
        //db.close();

    }

    public void updateCategory(CategoryModel category, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, category.name);

        db.update(TABLE_CATEGORY, values, "" + KEY_CID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public CategoryModel getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORY, new String[]{KEY_CID, KEY_NAME}, KEY_CID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        CategoryModel category = new CategoryModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_CID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
        );

        //db.close();
        return category;
    }

    public List<CategoryModel> getAllCategory() {
        List<CategoryModel> categoryList = new ArrayList<CategoryModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY + "ORDER BY" + KEY_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CategoryModel category = new CategoryModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_CID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))
                );
                categoryList.add(category);
            } while (cursor.moveToNext());
        }
        db.close();
        return categoryList;
    }

    public boolean deleteCategory(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        boolean flag = db.delete(TABLE_CATEGORY, KEY_CID + "=?", new String[]{String.valueOf(id)}) > 0;

        db.close();
        return flag;
    }


    //Budget CRUD Operations

    public void addBudget(BudgetModel budgetModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BACCOUNT, budgetModel.account);
        values.put(KEY_CID, budgetModel.category);
        values.put(KEY_BAMOUNT, budgetModel.amount);
        values.put(KEY_BCURRENCY, budgetModel.currency);
        values.put(KEY_BSTARTDATE, budgetModel.start);
        values.put(KEY_BENDDATE, budgetModel.end);
        values.put(KEY_BNAME, budgetModel.name);

        db.insert(TABLE_BUDGET, null, values);
        //db.close();

    }

    public BudgetModel getBudget(int id) {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery = "SELECT * FROM " + TABLE_BUDGET + " WHERE "+KEY_BID+" =? ";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        BudgetModel budgetModel = new BudgetModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_BNAME)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BAMOUNT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_CID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_BACCOUNT)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_BCURRENCY)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_BSTARTDATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_BENDDATE))
        );

        //db.close();
        return budgetModel;
    }

    public List<BudgetModel> getAllBudget() {
        List<BudgetModel> budgetList = new ArrayList<BudgetModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BUDGET + " ORDER BY " + KEY_BENDDATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BudgetModel budgetModel = new BudgetModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_BNAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BAMOUNT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_CID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_BACCOUNT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_BCURRENCY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_BSTARTDATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_BENDDATE))
                );
                budgetList.add(budgetModel);
            } while (cursor.moveToNext());
        }
//        db.close();
        return budgetList;
    }

}
