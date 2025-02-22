package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddtocartActivity extends AppCompatActivity {

    ImageView arr;

    ListView list;

    ArrayList<FoodCartModal> cartitem;

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

        cartitem=new ArrayList<FoodCartModal>();
        cartitem.add(new FoodCartModal(R.drawable.chickenbriyani,"Chicken Briyani","₹130","per 1kg",2));
        cartitem.add(new FoodCartModal(R.drawable.muttonbriyani,"Mutton Briyani","₹435","per 1kg",3));

        FoodCartAdapter adapter = new FoodCartAdapter(this,cartitem);

        list = (ListView) findViewById(R.id.cartlist);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FoodCartModal value = adapter.getItem(i);
                Toast.makeText(getApplicationContext(), value.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}