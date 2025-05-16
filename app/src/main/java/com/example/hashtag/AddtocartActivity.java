package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddtocartActivity extends AppCompatActivity {
    ImageView backArrow;
    TextView qtyTV, totalTV;
    ListView listView;
    Button paymentBtn;

    ArrayList<FoodCartModal> cartItems;
    FoodCartAdapter adapter;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addtocart);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbRef = FirebaseDatabase.getInstance().getReference("cart");

        backArrow = findViewById(R.id.backarrowcart);
        qtyTV = findViewById(R.id.quantityTotalTv);
        totalTV = findViewById(R.id.totalTv);
        listView = findViewById(R.id.cartlist);
        paymentBtn = findViewById(R.id.paymentbut);

        cartItems = new ArrayList<>();

        backArrow.setOnClickListener(v -> finish());

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                cartItems.clear();
                for (DataSnapshot itemSnap : snapshot.getChildren()) {
                    String name = itemSnap.child("name").getValue(String.class);
                    String rateStr = itemSnap.child("rate").getValue(String.class);
                    String imgStr = itemSnap.child("img").getValue().toString();
                    int price = Integer.parseInt(rateStr.replaceAll("[^\\d]", ""));
                    int img = Integer.parseInt(imgStr);

                    cartItems.add(new FoodCartModal(name, price, 1, img));
                }
                adapter = new FoodCartAdapter(AddtocartActivity.this, cartItems);
                listView.setAdapter(adapter);
                updateTotalViews();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(AddtocartActivity.this, "Failed to load cart", Toast.LENGTH_SHORT).show();
            }
        });

        paymentBtn.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Your cart is empty. Please add items before paying.", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent it = new Intent(AddtocartActivity.this, PaymentActivity.class);
            it.putParcelableArrayListExtra("cartItems", new ArrayList<Parcelable>(cartItems));
            startActivity(it);
        });
    }

    public void updateTotalViews() {
        int totalQty = 0;
        int totalPrice = 0;

        for (FoodCartModal item : cartItems) {
            totalQty += item.getNumitems();
            totalPrice += item.getNumitems() * item.getPrice();
        }

        qtyTV.setText("Quantity: " + totalQty);
        totalTV.setText("₹" + totalPrice);
    }

}
