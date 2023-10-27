package com.example.saneforcetask.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saneforcetask.R;
import com.example.saneforcetask.model.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private Activity activity;
    private List<Products> productList;

    public ProductAdapter(Context context, ArrayList<Products> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products product = productList.get(position);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.products_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);

        holder.increament.setOnClickListener(view -> {
          String quantity = holder.quantityEditText.getText().toString().trim();

          Integer mQuantity = Integer.parseInt(quantity);
          mQuantity++;

          holder.quantityEditText.setText(String.valueOf(mQuantity));

            /*
             *     Price * Qty
             */

            String price = holder.priceEditTText.getText().toString().trim();

            Integer totalAmount = Integer.parseInt(price ) * mQuantity;

            holder.totalEditText.setText(String.valueOf(totalAmount)); // see whatsapp
        });

        holder.decreament.setOnClickListener(view -> {

            String quantity = holder.quantityEditText.getText().toString().trim();

            if (quantity.equals("0")){
                return;
            }

            Integer mQuantity = Integer.parseInt(quantity);
            mQuantity--;

            holder.quantityEditText.setText(String.valueOf(mQuantity));

        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Spinner spinner;
        private Button increament, decreament;
        private TextView priceEditTText, quantityEditText, totalEditText;

        public ViewHolder(View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.productSpinner);
            increament = itemView.findViewById(R.id.incrementButton);
            priceEditTText = itemView.findViewById(R.id.price);
            quantityEditText = itemView.findViewById(R.id.quantity);
            decreament = itemView.findViewById(R.id.decrementButton);
            totalEditText = itemView.findViewById(R.id.totalEditText);
        }
    }
}
