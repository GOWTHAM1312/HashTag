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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentActivity extends AppCompatActivity {

    LinearLayout dchead, dcchild, nethead, netchild, paypalhead, paypalchild, upihead, upichild;
    ImageView dcarrow, netarrow, paypalarrow, upiarrow;
    Button proceedCardBtn, proceedPaypalBtn, proceedNetBtn, verifyUpiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);

        // Setup edge-to-edge padding for system bars
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

        // Buttons — make sure these IDs exist in your layout XML
        proceedCardBtn = findViewById(R.id.proceedCardBtn);
        proceedPaypalBtn = findViewById(R.id.proceedPaypalBtn);
        proceedNetBtn = findViewById(R.id.proceedNetBtn); // Optional, only if netbanking proceed button exists
        verifyUpiBtn = findViewById(R.id.verifyUpiBtn);

        // Toggle section visibility on header click
        dchead.setOnClickListener(v -> toggleVisibility(dcchild, dcarrow));
        nethead.setOnClickListener(v -> toggleVisibility(netchild, netarrow));
        paypalhead.setOnClickListener(v -> toggleVisibility(paypalchild, paypalarrow));
        upihead.setOnClickListener(v -> toggleVisibility(upichild, upiarrow));

        // Button click listeners to proceed with payment
        proceedCardBtn.setOnClickListener(v -> proceedPayment());
        proceedPaypalBtn.setOnClickListener(v -> proceedPayment());
        proceedNetBtn.setOnClickListener(v -> proceedPayment());
        verifyUpiBtn.setOnClickListener(v -> {
            // Optional: add UPI verification logic here
            proceedPayment();
        });
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

    private void proceedPayment() {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("cart");
        dbRef.removeValue()
                .addOnSuccessListener(unused -> {
                    Toast.makeText(PaymentActivity.this, "Payment successful! Order placed.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PaymentActivity.this, ThankingActivity.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(PaymentActivity.this, "Payment succeeded but failed to clear cart.", Toast.LENGTH_LONG).show();
                });
    }
}
