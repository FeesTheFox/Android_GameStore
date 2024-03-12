package com.example.gamestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.gamestore.databinding.ActivitySecondBinding;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initCart();
    }

    private int calculateTotalPrice(ArrayList<Product> products) {
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    private void initCart() {
        // Получение списка выбранных товаров из предыдущего Activity
        ArrayList<Product> selectedProducts = getIntent().getParcelableArrayListExtra("selectedProducts");

        // Создание адаптера для списка товаров в корзине
        ProductAdapter adapter = new ProductAdapter(this, selectedProducts);

        // Настройка ListView для отображения товаров
        binding.cartListView.setAdapter(adapter);

        // Посчитайте и отобразите общую сумму заказа
        int totalPrice = calculateTotalPrice(selectedProducts);
        binding.totalPriceTextView.setText(getString(R.string.total_price, totalPrice));
    }
}