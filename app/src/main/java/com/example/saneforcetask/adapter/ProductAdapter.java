package com.example.saneforcetask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saneforcetask.R;
import com.example.saneforcetask.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
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
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText productQuantityTextView;
        private EditText productAddTextView;
        private EditText totalTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            productQuantityTextView = itemView.findViewById(R.id.productQuantityTextView);
            productAddTextView= itemView.findViewById(R.id.productAddTextView);
            totalTextView= itemView.findViewById(R.id.totalTextView);
        }

        public void bind(Product product) {
            productQuantityTextView.setText(product.getName());
            totalTextView.setText("Rate: " + product.getQuantity());
        }
    }
}
