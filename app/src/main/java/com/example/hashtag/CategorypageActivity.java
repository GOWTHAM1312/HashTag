package com.example.hashtag;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CategorypageActivity extends AppCompatActivity {

    GridView grid1;

    ArrayList<FoodItemModal> fditm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categorypage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        grid1=(GridView) findViewById(R.id.itemsGV1);
        fditm=new ArrayList<FoodItemModal>();

        fditm.add(new FoodItemModal(R.drawable.attukalpayasoup,"Attukalpayasoup","₹120","per 250ml"));
        fditm.add(new FoodItemModal(R.drawable.hotandsourchickensoup,"Hot & Sour Chicken Soup","₹250","per 250ml"));
        fditm.add(new FoodItemModal(R.drawable.chickenmilagu,"Chicken Milagu Soup","₹129","per 250ml"));
        fditm.add(new FoodItemModal(R.drawable.muttonmilagu,"Mutton Milagu Soup","₹179","per 250ml"));
        fditm.add(new FoodItemModal(R.drawable.sweetcornchicken,"Sweetcorn Chicken Soup","₹161","per 250ml"));
        fditm.add(new FoodItemModal(R.drawable.sweetcornveg,"Sweetcorn veg Soup","₹123","per 250ml"));

        FoodItemAdapter adapter = new FoodItemAdapter(this,fditm);
        grid1.setAdapter(adapter);

        grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FoodItemModal value = adapter.getItem(i);
                Toast.makeText(getApplicationContext(), value.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}