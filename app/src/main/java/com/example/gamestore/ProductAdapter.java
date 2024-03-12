package com.example.gamestore;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private ArrayList<Product> productList;

    public ProductAdapter(Context context, ArrayList<Product> productList) {
        super(context, 0, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        Product currentProduct = getItem(position);

        TextView productNameTextView = listItemView.findViewById(R.id.cartProductNameTextView);
        TextView productPriceTextView = listItemView.findViewById(R.id.cartProductPriceTextView);

        if (currentProduct != null) {
            productNameTextView.setText(currentProduct.getName());
            productPriceTextView.setText(String.valueOf(currentProduct.getPrice()));
        }

        return listItemView;
    }
}