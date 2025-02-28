package com.example.hashtag;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.media.RouteListingPreference;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import androidx.credentials.webauthn.Cbor;

import java.util.ArrayList;
import java.util.List;

public class AddtocartActivity extends AppCompatActivity {

    ImageView arr;

    TextView qtyTV,totalTV;

    ListView list;

    Button pay;

    ArrayList<FoodCartModal> cartitem;

    @SuppressLint("MissingInflatedId")
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

        arr=findViewById(R.id.backarrowcart);

        arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(AddtocartActivity.this,FoodActivity.class);
                startActivity(it);
            }
        });

        qtyTV=(TextView)findViewById(R.id.cartnumofitemTv);
        totalTV=(TextView)findViewById(R.id.totalTv);

        List<FoodCartModal> itemList = new ArrayList<>();
        itemList.add(new FoodCartModal(R.drawable.chickenbriyani,"Briyani",120, "per 100g",1));
        //itemList.add(new FoodCartModal(1, 1,20));
        //itemList.add(new FoodCartModal(2, 1,30));
        //itemList.add(new FoodCartModal(3, 1,40));
        //itemList.add(new FoodCartModal(4, 1,50));
        //itemList.add(new FoodCartModal(5, 1,60));

        ListView listView = findViewById(R.id.cartlist);
        FoodCartAdapter adapter1 = new FoodCartAdapter(this, itemList);
        listView.setAdapter(adapter1);

        int sum=0,qty=0;
        for (FoodCartModal i:itemList)
        {
            qty= qty+i.getPrice();
            sum=sum+i.getNumitems();
        }

        //qtyTV.setText("Quantity: "+qty);
        //otalTV.setText("Total Price: "+sum);

        pay=findViewById(R.id.paymentbut);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AddtocartActivity.this, PaymentActivity.class);
                startActivity(it);
            }
        });

    }
}