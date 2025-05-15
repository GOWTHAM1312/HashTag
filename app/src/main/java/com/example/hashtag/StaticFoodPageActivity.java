package com.example.hashtag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StaticFoodPageActivity extends AppCompatActivity {

    CardView card1, card2, card3, card4, card5, card6;
    CardView card7, card8, card9, card10, card11, card12;

    ImageView profile, cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_staticfoodpage);

        // Edge-to-edge padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find all cards
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);
        card10 = findViewById(R.id.card10);
        card11 = findViewById(R.id.card11);
        card12 = findViewById(R.id.card12);

        // Find profile and cart icons
        profile = findViewById(R.id.staticuserprofile);
        cart = findViewById(R.id.staticfoodaddcart);

        // Shared click listener for cards
        View.OnClickListener listener = v -> {
            String categoryName = "";
            int id = v.getId();
            if (id == R.id.card1) categoryName = "Soup";
            else if (id == R.id.card2) categoryName = "Starters";
            else if (id == R.id.card3) categoryName = "Briyani";
            else if (id == R.id.card4) categoryName = "Curries & Gravies";
            else if (id == R.id.card5) categoryName = "Kebabs & Barbeque";
            else if (id == R.id.card6) categoryName = "Bucket Briyani";
            else if (id == R.id.card7) categoryName = "Rice & Noodles";
            else if (id == R.id.card8) categoryName = "Special Meals";
            else if (id == R.id.card9) categoryName = "Egg Items";
            else if (id == R.id.card10) categoryName = "Bread & Parottas";
            else if (id == R.id.card11) categoryName = "Add Soon...";
            else if (id == R.id.card12) categoryName = "Dosa & Idiyappam";

            SharedPreferences sharedPreferences = getSharedPreferences("Category", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("categoryName", categoryName);
            editor.apply();

            Intent intent = new Intent(StaticFoodPageActivity.this, CategorypageActivity.class);
            startActivity(intent);
        };

        // Attach listener to all cards
        card1.setOnClickListener(listener);
        card2.setOnClickListener(listener);
        card3.setOnClickListener(listener);
        card4.setOnClickListener(listener);
        card5.setOnClickListener(listener);
        card6.setOnClickListener(listener);
        card7.setOnClickListener(listener);
        card8.setOnClickListener(listener);
        card9.setOnClickListener(listener);
        card10.setOnClickListener(listener);
        card11.setOnClickListener(listener);
        card12.setOnClickListener(listener);

        // Profile icon click - open ProfileActivity
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(StaticFoodPageActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        // Cart icon click - open AddtocartActivity
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(StaticFoodPageActivity.this, AddtocartActivity.class);
            startActivity(intent);
        });
    }
}
