package com.example.saneforcetask;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saneforcetask.adapter.ProductAdapter;
import com.example.saneforcetask.database.DatabaseManager;
import com.example.saneforcetask.databinding.ActivityMainBinding;
import com.example.saneforcetask.model.Products;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<Products> productsArrayList;
    private ProductAdapter  productAdapter;
    private SQLiteDatabase database;
    private DatabaseManager databaseManager;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        initOnClick();
        loadProduct();
    }

    private void loadProduct() {
        productsArrayList = new ArrayList<>(databaseManager.getProducts());

        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setItemViewCacheSize(20);
        productAdapter = new ProductAdapter(getApplicationContext(), productsArrayList);
        binding.recyclerView.setAdapter(productAdapter);
    }

    private void initOnClick() {
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dbTextProductName = "Product 1";
                String dbPrice = "0";
                String dbQuantity = "0";
                String dbTotalAmount = "0";

                databaseManager.addProducts(dbTextProductName,dbPrice,dbQuantity,dbTotalAmount);

                Products products = new Products();
                products.setProduct_name(dbTextProductName);
               productsArrayList.add(products);
                productAdapter.notifyDataSetChanged();
                Log.e("add__", "Product added");
            }
        });
    }
}