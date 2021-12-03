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

    public static String TABLE_GOAL = "GoalTable";

    public static String KEY_GID = "gid";
    public static String KEY_GNAME = "gname";
    public static String KEY_GAMOUNT = "gamount";
    public static String KEY_GNOTE = "gnote";
    public static String KEY_GDATE = "gdate";
    public static String KEY_GALREADY_SAVED = "already_saved";
    public static String KEY_GSTATUS = "status";

    public static String TABLE_DEBT = "DebtTable";

    public static String KEY_DID = "did";
    public static String KEY_DNAME = "dname";
    public static String KEY_DAMOUNT = "damount";
    public static String KEY_DDES = "ddescription";
    public static String KEY_DACCOUNT = "daccount";
    public static String KEY_DDATE = "ddate";
    public static String KEY_DDUE = "dduedate";
    public static String KEY_DTYPE = "dtype";
    public static String KEY_DISACTIVE = "disactive";

    public static String TABLE_SHOPPING = "ShoppingTable";

    public static String KEY_SID = "sid";
    public static String KEY_SNAME = "sname";
    public static String KEY_SAMOUNT = "samount";
    public static String KEY_SITEM = "sitem";


    public static String TABLE_SHOPPING_ITEM = "ItemTable";

    public static String KEY_IID = "iid";
    public static String KEY_INAME = "iname";
    public static String KEY_IAMOUNT = "iamount";
    public static String KEY_ICHECKED = "checked";

    public SQLiteHandler(@Nullable Context context) {
        super(context, "ExpenseDB6", null, 1);
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

        String CREATE_GOAL_TABLE = "CREATE TABLE " + TABLE_GOAL + "("
                + KEY_GID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_GAMOUNT + " INTEGER,"
                + KEY_GALREADY_SAVED + " INTEGER,"
                + KEY_GNAME + " VARCHAR(20),"
                + KEY_GSTATUS + " VARCHAR(10),"
                + KEY_GNOTE + " VARCHAR(20),"
                + KEY_GDATE + " DATETIME)";

        String CREATE_DEBT_TABLE = "CREATE TABLE " + TABLE_DEBT + "("
                + KEY_DID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_DAMOUNT + " INTEGER,"
                + KEY_DNAME + " VARCHAR(20),"
                + KEY_DDES + " VARCHAR(20),"
                + KEY_DACCOUNT + " VARCHAR(10),"
                + KEY_DTYPE + " VARCHAR(10),"
                + KEY_DDUE + " DATETIME,"
                + KEY_DISACTIVE + " INTEGER,"
                + KEY_DDATE + " DATETIME)";

        String CREATE_SHOPPING_TABLE = "CREATE TABLE " + TABLE_SHOPPING + "("
                + KEY_SID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SAMOUNT + " INTEGER,"
                + KEY_SITEM + " INTEGER,"
                + KEY_SNAME + " VARCHAR(20) )";

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_SHOPPING_ITEM + "("
                + KEY_IID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_IAMOUNT + " INTEGER,"
                + KEY_SID + " INTEGER,"
                + KEY_ICHECKED + " INTEGER,"
                + KEY_INAME + " VARCHAR(20),"
                + " FOREIGN KEY ("+ KEY_SID +") REFERENCES "+ TABLE_SHOPPING_ITEM +" ("+ KEY_SID +") ON UPDATE CASCADE ON DELETE CASCADE)";

        db.execSQL(CREATE_EXPENSE_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_BUDGET_TABLE);
        db.execSQL(CREATE_GOAL_TABLE);
        db.execSQL(CREATE_DEBT_TABLE);
        db.execSQL(CREATE_SHOPPING_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);

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
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTION + " WHERE " + KEY_CAT + " =? ORDER BY " + KEY_DATE;

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

    public int getSum(String key) {
        String selectQuery = "SELECT (SUM(" + key + ")) AS total FROM " + TABLE_TRANSACTION + " ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        } else {
            return 0;
        }
    }

    public int getSumByCat(String sum, String column) {
        String selectQuery = "SELECT (SUM(" + sum + ")) AS total FROM " + TABLE_TRANSACTION + " WHERE " + KEY_CAT + " =? ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{column});
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        } else {
            return 0;
        }
    }

    public int getSumByDate(String sum, String date) {
        String selectQuery = "SELECT (SUM(" + sum + ")) AS total FROM " + TABLE_TRANSACTION + " WHERE DATE (" + KEY_DATE + ") =? ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{date});
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        } else {
            return 0;
        }
    }

    public int getSumByDateExpense(String sum, String date) {
        String selectQuery = "SELECT (SUM(" + sum + ")) AS total FROM " + TABLE_TRANSACTION + " WHERE DATE (" + KEY_DATE + ") =? AND (" + KEY_ISEXPENSE + ") = 1 ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{date});
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        } else {
            return 0;
        }
    }

    public int getSumByDateIncome(String sum, String date) {
        String selectQuery = "SELECT (SUM(" + sum + ")) AS total FROM " + TABLE_TRANSACTION + " WHERE DATE (" + KEY_DATE + ") =? AND (" + KEY_ISINCOME + ") = 1 ORDER BY " + KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{date});
        if (cursor.moveToFirst()) {
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
        values.put(KEY_CAT, budgetModel.category);
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


        String selectQuery = "SELECT * FROM " + TABLE_BUDGET + " WHERE " + KEY_BID + " =? ";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        BudgetModel budgetModel = new BudgetModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_BNAME)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BAMOUNT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_CAT)),
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
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BudgetModel budgetModel = new BudgetModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_BNAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BAMOUNT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_CAT)),
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

    public boolean deleteBudget(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        boolean flag = db.delete(TABLE_BUDGET, KEY_BID + "=?", new String[]{String.valueOf(id)}) > 0;

        db.close();
        return flag;
    }

    public boolean updateBudget(BudgetModel budgetModel, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BACCOUNT, budgetModel.account);
        values.put(KEY_CAT, budgetModel.category);
        values.put(KEY_BAMOUNT, budgetModel.amount);
        values.put(KEY_BCURRENCY, budgetModel.currency);
        values.put(KEY_BSTARTDATE, budgetModel.start);
        values.put(KEY_BENDDATE, budgetModel.end);
        values.put(KEY_BNAME, budgetModel.name);

        if (db.update(TABLE_BUDGET, values, "" + KEY_BID + " = ?", new String[]{String.valueOf(id)}) >= 0) {
            db.close();
            return true;
        } else {
            return false;
        }
    }

    // Gaol CRUD

    public boolean addGoal(GoalModel goalModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GAMOUNT, goalModel.amount);
        values.put(KEY_GDATE, goalModel.date);
        values.put(KEY_GNAME, goalModel.name);
        values.put(KEY_GALREADY_SAVED, goalModel.alreadySaved);
        values.put(KEY_GNOTE, goalModel.note);
        values.put(KEY_GSTATUS, goalModel.status);


        if (db.insert(TABLE_GOAL, null, values) > 0) {
            return true;
        } else {
            return false;
        }
        //db.close();

    }

    public List<GoalModel> getAllGoal(String column) {
        List<GoalModel> goalList = new ArrayList<GoalModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GOAL + " WHERE " + KEY_GSTATUS + " =?  ORDER BY " + KEY_GDATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{column});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GoalModel goal = new GoalModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_GID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_GNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_GNOTE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_GDATE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_GAMOUNT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_GALREADY_SAVED)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_GSTATUS))
                );
                goalList.add(goal);
            } while (cursor.moveToNext());
        }
        db.close();
        return goalList;
    }

    public GoalModel getGoal(int id) {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery = "SELECT * FROM " + TABLE_GOAL + " WHERE " + KEY_GID + " =? ";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        GoalModel goal = new GoalModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_GID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_GNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_GNOTE)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_GDATE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_GAMOUNT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_GALREADY_SAVED)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_GSTATUS))
        );

        //db.close();
        return goal;
    }

    public boolean deleteGoal(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        boolean flag = db.delete(TABLE_GOAL, KEY_GID + "=?", new String[]{String.valueOf(id)}) > 0;

        db.close();
        return flag;
    }

    public boolean updateGoal(GoalModel goalModel, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GAMOUNT, goalModel.amount);
        values.put(KEY_GDATE, goalModel.date);
        values.put(KEY_GNAME, goalModel.name);
        values.put(KEY_GALREADY_SAVED, goalModel.alreadySaved);
        values.put(KEY_GNOTE, goalModel.note);
        values.put(KEY_GSTATUS, goalModel.status);

        if (db.update(TABLE_GOAL, values, "" + KEY_GID + " = ?", new String[]{String.valueOf(id)}) >= 0) {
            db.close();
            return true;
        } else {
            return false;
        }
    }

    // Debt CRUD

    public boolean addDebt(DebtModel debtModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DNAME, debtModel.name);
        values.put(KEY_DDES, debtModel.description);
        values.put(KEY_DACCOUNT, debtModel.account);
        values.put(KEY_DDATE, debtModel.date);
        values.put(KEY_DDUE, debtModel.duedate);
        values.put(KEY_DAMOUNT, debtModel.amount);
        values.put(KEY_DTYPE, debtModel.type);
        values.put(KEY_DISACTIVE, debtModel.isActive);


        if (db.insert(TABLE_DEBT, null, values) > 0) {
            return true;
        } else {
            return false;
        }
        //db.close();

    }

    public DebtModel getDebt(int id) {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery = "SELECT * FROM " + TABLE_DEBT + " WHERE " + KEY_DID + " =? ";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        DebtModel debt = new DebtModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_DID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DDES)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DACCOUNT)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DDATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DDUE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_DAMOUNT)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DTYPE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_DISACTIVE))
        );

        //db.close();
        return debt;
    }

    public List<DebtModel> getAllDebt(String column1, String column2) {
        List<DebtModel> debtList = new ArrayList<DebtModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DEBT + " WHERE " + KEY_DTYPE + " =? AND " + KEY_DISACTIVE + " =?  ORDER BY " + KEY_DDATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{column1, column2});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DebtModel debt = new DebtModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_DID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_DNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_DDES)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_DACCOUNT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_DDATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_DDUE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_DAMOUNT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_DTYPE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_DISACTIVE))
                );
                debtList.add(debt);
            } while (cursor.moveToNext());
        }
        db.close();
        return debtList;
    }

    public boolean deleteDebt(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        boolean flag = db.delete(TABLE_DEBT, KEY_DID + "=?", new String[]{String.valueOf(id)}) > 0;

        db.close();
        return flag;
    }

    public boolean updateDebt(DebtModel debtModel, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DNAME, debtModel.name);
        values.put(KEY_DDES, debtModel.description);
        values.put(KEY_DACCOUNT, debtModel.account);
        values.put(KEY_DDATE, debtModel.date);
        values.put(KEY_DDUE, debtModel.duedate);
        values.put(KEY_DAMOUNT, debtModel.amount);
        values.put(KEY_DTYPE, debtModel.type);
        values.put(KEY_DISACTIVE, debtModel.isActive);

        if (db.update(TABLE_DEBT, values, "" + KEY_DID + " = ?", new String[]{String.valueOf(id)}) >= 0) {
            db.close();
            return true;
        } else {
            return false;
        }
    }

    //Shopping list CRUD

    public boolean addShopping(ShoppingListModel shoppingListModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SNAME, shoppingListModel.name);
        values.put(KEY_SAMOUNT, shoppingListModel.amount);
        values.put(KEY_SITEM, shoppingListModel.totalitem);


        if (db.insert(TABLE_SHOPPING, null, values) > 0) {
            return true;
        } else {
            return false;
        }
        //db.close();

    }

    public ShoppingListModel getShopping(int id) {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery = "SELECT * FROM " + TABLE_SHOPPING + " WHERE " + KEY_SID + " =? ";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        ShoppingListModel shopping = new ShoppingListModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_SNAME)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SITEM)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SAMOUNT))
        );

        //db.close();
        return shopping;
    }

    public List<ShoppingListModel> getAllShoppingList() {
        List<ShoppingListModel> shoppingList = new ArrayList<ShoppingListModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SHOPPING + " ORDER BY " + KEY_SID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ShoppingListModel shopping = new ShoppingListModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_SNAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SITEM)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SAMOUNT))
                );
                shoppingList.add(shopping);
            } while (cursor.moveToNext());
        }
        db.close();
        return shoppingList;
    }

    public boolean deleteShopping(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        boolean flag = db.delete(TABLE_SHOPPING, KEY_SID + "=?", new String[]{String.valueOf(id)}) > 0;

        db.close();
        return flag;
    }

    public boolean updateShopping(ShoppingListModel shoppingListModel, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SNAME, shoppingListModel.name);
        values.put(KEY_SAMOUNT, shoppingListModel.amount);
        values.put(KEY_SITEM, shoppingListModel.totalitem);

        if (db.update(TABLE_SHOPPING, values, "" + KEY_SID + " = ?", new String[]{String.valueOf(id)}) >= 0) {
            db.close();
            return true;
        } else {
            return false;
        }
    }

    //Shopping item CRUD

    public boolean addItem(ShoppingItemModel shoppingItemModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INAME, shoppingItemModel.name);
        values.put(KEY_IAMOUNT, shoppingItemModel.amount);
        values.put(KEY_SID, shoppingItemModel.sid);


        if (db.insert(TABLE_SHOPPING_ITEM, null, values) > 0) {
            return true;
        } else {
            return false;
        }
        //db.close();

    }

    public ShoppingItemModel getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery = "SELECT * FROM " + TABLE_SHOPPING_ITEM + " WHERE " + KEY_IID + " =? ";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        ShoppingItemModel shoppingItemModel = new ShoppingItemModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_IID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_INAME)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_IAMOUNT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ICHECKED))
        );

        //db.close();
        return shoppingItemModel;
    }

    public List<ShoppingItemModel> getAllItem(String column1) {
        List<ShoppingItemModel> shoppingList = new ArrayList<ShoppingItemModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SHOPPING_ITEM + " WHERE " + KEY_SID + " =? ORDER BY " + KEY_IID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{column1});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ShoppingItemModel shoppingItemModel = new ShoppingItemModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_IID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_INAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_IAMOUNT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SID)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ICHECKED))
                );
                shoppingList.add(shoppingItemModel);
            } while (cursor.moveToNext());
        }
        db.close();
        return shoppingList;
    }

    public boolean deleteItem(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        boolean flag = db.delete(TABLE_SHOPPING_ITEM, KEY_IID + "=?", new String[]{String.valueOf(id)}) > 0;

        db.close();
        return flag;
    }

    public boolean updateShopping(ShoppingItemModel shoppingItemModel, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INAME, shoppingItemModel.name);
        values.put(KEY_IAMOUNT, shoppingItemModel.amount);
        values.put(KEY_SID, shoppingItemModel.sid);

        if (db.update(TABLE_SHOPPING_ITEM, values, "" + KEY_IID + " = ?", new String[]{String.valueOf(id)}) >= 0) {
            db.close();
            return true;
        } else {
            return false;
        }
    }

}
