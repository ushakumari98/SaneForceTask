package com.example.saneforcetask.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "product_database";
    static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "products";
    public static final String ID_COL = "id";
    public static final String PRODUCT_NAME_COL = "product_name";
    public static final String PRICE_COL = "price";
    public static final String QUANTITY_COL = "quantity";
    public static final String TOTAL_AMT_COL = "total_amt";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRODUCT_NAME_COL + " TEXT, "
            + PRICE_COL + " TEXT, "
            + QUANTITY_COL + " TEXT, "
            + TOTAL_AMT_COL + " TEXT)";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db
        );
    }
}
