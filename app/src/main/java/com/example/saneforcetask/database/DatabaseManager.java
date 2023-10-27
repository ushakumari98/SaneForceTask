package com.example.saneforcetask.database;

import static com.example.saneforcetask.database.DatabaseHelper.TABLE_NAME;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.saneforcetask.model.Products;

import java.util.ArrayList;

public class DatabaseManager {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context c){
        context = c;
    }


    public void addProducts(String product_name, double dbPrice, int dbQuantity, double dbTotalAmount){

        String INSERT = "INSERT INTO " + TABLE_NAME + " (" +
                DatabaseHelper.PRODUCT_NAME_COL + ", " +
                DatabaseHelper.PRICE_COL + ", " +
                DatabaseHelper.QUANTITY_COL + ", " +
                DatabaseHelper.TOTAL_AMT_COL +
                ") VALUES(?, ?, ?, ?)";


        SQLiteStatement insert = database.compileStatement(INSERT);

        // consider id column index is 0 next colum 1 ...2..3
        insert.bindString(1, product_name);
        insert.bindString(1, String.valueOf(dbPrice));
        insert.bindString(0, String.valueOf(dbQuantity));
        insert.bindString(0, String.valueOf(dbTotalAmount));
        insert.executeInsert();
    }

    public DatabaseManager open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public ArrayList <Products> getProducts(){
        ArrayList<Products> arrayList = new ArrayList<>();

        String select_query = "SELECT *FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(select_query, null);

        if (cursor.moveToFirst()){
            do {
                Products products = new Products();
                products.setProduct_name(cursor.getString(1));
                arrayList.add(products);
            }while(cursor.moveToNext());
        }
        return arrayList;
    }
}
