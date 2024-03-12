package com.example.gamestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamestore.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;

    private ActivityMainBinding binding;

    Button  btnOk;

    View header, footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        header = createHeader("Game Store");
        footer = createFooter("Contact us");

        fillData();

        TextView footerTextView = findViewById(R.id.tvText); // Находим текстовое поле в футере
        boxAdapter = new BoxAdapter(this, products, footerTextView); // Передаем его в адаптер

        ListView lvMain = binding.lvMain;
        lvMain.addHeaderView(header);
        lvMain.setAdapter(boxAdapter);

        Button btnGo = findViewById(R.id.btnGo);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Product> selectedProducts = boxAdapter.getBox();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putParcelableArrayListExtra("selectedProducts", selectedProducts);
                startActivity(intent);
            }
        });
    }

    View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return v;
    }

    View createFooter(String text) {
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        return v;
    }


    private String[] flowerNames = {"Mario", "Sonic", "Zelda", "God of War", "Minecraft", "Terraria", "Ultrakill", "Elden ring",
            "Dark Souls Remastered", "Dark Souls 2", "Dark Souls 3", "BloodBorn", "Sekiro shadow die twice", "Demon Souls", "Lethal Company",
            "EuroTruck Simulator", "The Forest", "Hearts of Iron 4", "Heroes of the Might and Magic 3", "Crusader Kings 2"};
    private int[] prices = {2900, 2100, 3000, 3500, 590, 400, 3000, 1500, 1600, 1781, 2000, 3212, 1099, 480, 2999, 350, 600, 700, 871, 321};
    private int[] images = {R.drawable.mario, R.drawable.sonic, R.drawable.zelda, R.drawable.godofwar, R.drawable.minecraft, R.drawable.terraria,
            R.drawable.ultrakill, R.drawable.ring, R.drawable.darkremaster, R.drawable.dark2, R.drawable.dark3, R.drawable.bloodborn, R.drawable.sekiro,
            R.drawable.demon, R.drawable.lethal, R.drawable.euro, R.drawable.forest, R.drawable.hoi, R.drawable.heroes, R.drawable.king};
    void fillData() {
        for (int i = 0; i < flowerNames.length; i++) {
            products.add(new Product(flowerNames[i], prices[i], images[i], false));
        }
    }




    public void showResult(View v) {
        String result = "Products in cart:";
        for (Product p : boxAdapter.getBox()) {
            if (p.box) {
                result += "\n" + p.name;
            }
        }
        Toast.makeText(this,result, Toast.LENGTH_LONG).show();
    }
}