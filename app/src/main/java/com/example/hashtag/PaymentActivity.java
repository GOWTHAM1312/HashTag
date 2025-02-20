package com.example.hashtag;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentActivity extends AppCompatActivity {

    LinearLayout dchead;
    LinearLayout dcchild;
    ImageView dcarrow;
    LinearLayout nethead;
    LinearLayout netchild;
    ImageView netarrow;
    LinearLayout paypalhead;
    LinearLayout paypalchild;
    ImageView paypalarrow;
    LinearLayout upihead;
    LinearLayout upichild;
    ImageView upiarrow;




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

        dchead=findViewById(R.id.cardpaymenthead);
        dcchild=findViewById(R.id.cardpaymentchild);
        dcarrow=findViewById(R.id.cardarrow);
        nethead=findViewById(R.id.netbankinghead);
        netchild=findViewById(R.id.netbankingchild);
        netarrow=findViewById(R.id.netarrow);
        paypalhead=findViewById(R.id.paypalhead);
        paypalchild=findViewById(R.id.paypalchild);
        paypalarrow=findViewById(R.id.paypalarrow);
        upihead=findViewById(R.id.upihead);
        upichild=findViewById(R.id.upichild);
        upiarrow=findViewById(R.id.upiarrow);

        dchead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dcchild.getVisibility() == View.GONE) {
                    dcchild.setVisibility(View.VISIBLE);
                    dcarrow.setBackgroundResource(R.drawable.baseline_arrow_drop_up_24);
                }
                else {
                    dcchild.setVisibility(View.GONE);
                    dcarrow.setBackgroundResource(R.drawable.baseline_arrow_drop_down_24);
                }
            }
        });

        nethead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (netchild.getVisibility() == View.GONE) {
                    netchild.setVisibility(View.VISIBLE);
                    netarrow.setBackgroundResource(R.drawable.baseline_arrow_drop_up_24);
                }
                else {
                    netchild.setVisibility(View.GONE);
                    netarrow.setBackgroundResource(R.drawable.baseline_arrow_drop_down_24);
                }
            }
        });

        paypalhead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (paypalchild.getVisibility() == View.GONE) {
                    paypalchild.setVisibility(View.VISIBLE);
                    paypalarrow.setBackgroundResource(R.drawable.baseline_arrow_drop_up_24);
                } else {
                    paypalchild.setVisibility(View.GONE);
                    paypalarrow.setBackgroundResource(R.drawable.baseline_arrow_drop_down_24);
                }
            }
        });

        upihead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (upichild.getVisibility() == View.GONE) {
                    upichild.setVisibility(View.VISIBLE);
                    upiarrow.setBackgroundResource(R.drawable.baseline_arrow_drop_up_24);
                }
                else {
                    upichild.setVisibility(View.GONE);
                    upiarrow.setBackgroundResource(R.drawable.baseline_arrow_drop_down_24);
                }
            }
        });

    }
}