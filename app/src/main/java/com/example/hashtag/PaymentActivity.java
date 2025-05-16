package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    LinearLayout dchead, dcchild, nethead, netchild, paypalhead, paypalchild, upihead, upichild;
    ImageView dcarrow, netarrow, paypalarrow, upiarrow;
    Button proceedCardBtn, proceedPaypalBtn, proceedNetBtn, verifyUpiBtn;

    private ArrayList<FoodCartModal> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find views
        dchead = findViewById(R.id.cardpaymenthead);
        dcchild = findViewById(R.id.cardpaymentchild);
        dcarrow = findViewById(R.id.cardarrow);
        nethead = findViewById(R.id.netbankinghead);
        netchild = findViewById(R.id.netbankingchild);
        netarrow = findViewById(R.id.netarrow);
        paypalhead = findViewById(R.id.paypalhead);
        paypalchild = findViewById(R.id.paypalchild);
        paypalarrow = findViewById(R.id.paypalarrow);
        upihead = findViewById(R.id.upihead);
        upichild = findViewById(R.id.upichild);
        upiarrow = findViewById(R.id.upiarrow);

        proceedCardBtn = findViewById(R.id.proceedCardBtn);
        proceedPaypalBtn = findViewById(R.id.proceedPaypalBtn);
        proceedNetBtn = findViewById(R.id.proceedNetBtn);
        verifyUpiBtn = findViewById(R.id.verifyUpiBtn);

        // Get cart data
        cartItems = getIntent().getParcelableArrayListExtra("cartItems");
        if (cartItems == null) cartItems = new ArrayList<>();

        // Set toggles
        dchead.setOnClickListener(v -> toggleVisibility(dcchild, dcarrow));
        nethead.setOnClickListener(v -> toggleVisibility(netchild, netarrow));
        paypalhead.setOnClickListener(v -> toggleVisibility(paypalchild, paypalarrow));
        upihead.setOnClickListener(v -> toggleVisibility(upichild, upiarrow));

        // Payment action buttons
        proceedCardBtn.setOnClickListener(v -> placeOrder("Card"));
        proceedPaypalBtn.setOnClickListener(v -> placeOrder("PayPal"));
        proceedNetBtn.setOnClickListener(v -> placeOrder("Net Banking"));
        verifyUpiBtn.setOnClickListener(v -> placeOrder("UPI"));
    }

    private void toggleVisibility(LinearLayout childLayout, ImageView arrow) {
        if (childLayout.getVisibility() == View.GONE) {
            childLayout.setVisibility(View.VISIBLE);
            arrow.setImageResource(R.drawable.baseline_arrow_drop_up_24);
        } else {
            childLayout.setVisibility(View.GONE);
            arrow.setImageResource(R.drawable.baseline_arrow_drop_down_24);
        }
    }

    private void placeOrder(String paymentMethod) {
        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(uid);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String userName = snapshot.child("name").getValue(String.class);
                String address = snapshot.child("address").getValue(String.class);

                if (userName == null || userName.trim().isEmpty()) userName = "Unknown";
                if (address == null || address.trim().isEmpty()) address = "Not provided";

                DatabaseReference orderRef = FirebaseDatabase.getInstance()
                        .getReference("Orders")
                        .child(userName)
                        .push();

                HashMap<String, Object> orderMap = new HashMap<>();
                ArrayList<HashMap<String, Object>> itemList = new ArrayList<>();
                int total = 0;

                for (FoodCartModal item : cartItems) {
                    HashMap<String, Object> i = new HashMap<>();
                    i.put("name", item.getName());
                    i.put("quantity", item.getNumitems());
                    i.put("totalPrice", item.getNumitems() * item.getPrice());
                    total += item.getNumitems() * item.getPrice();
                    itemList.add(i);
                }

                orderMap.put("items", itemList);
                orderMap.put("totalAmount", total);
                orderMap.put("paymentMethod", paymentMethod);
                orderMap.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                orderMap.put("status", "Pending");
                orderMap.put("address", address);

                orderRef.setValue(orderMap)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(PaymentActivity.this, "Order placed!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PaymentActivity.this, ThankingActivity.class));
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(PaymentActivity.this, "Order failed to save.", Toast.LENGTH_SHORT).show();
                        });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(PaymentActivity.this, "Error fetching profile data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
