package com.example.hashtag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    GridView grid;
    ArrayList<FoodCategory> fdcat;

    ImageView profile;

    ImageView cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        grid=(GridView) findViewById(R.id.itemsGV);
        fdcat=new ArrayList<FoodCategory>();

        fdcat.add(new FoodCategory(R.drawable.soup,"Soup",getResources().getColor(R.color.chinesewhite)));
        fdcat.add(new FoodCategory(R.drawable.starters,"Starters",getResources().getColor(R.color.lavender)));
        fdcat.add(new FoodCategory(R.drawable.briyani,"Briyani",getResources().getColor(R.color.palelavender)));
        fdcat.add(new FoodCategory(R.drawable.curry,"Curries & Gravies",getResources().getColor(R.color.champagnepink)));
        fdcat.add(new FoodCategory(R.drawable.kebabs,"Kebabs & Barbeque",getResources().getColor(R.color.chinesewhite)));
        fdcat.add(new FoodCategory(R.drawable.bucketbriyani,"Bucket Briyani",getResources().getColor(R.color.lavender)));
        fdcat.add(new FoodCategory(R.drawable.riceandnoodles,"Rice & Noodles",getResources().getColor(R.color.palelavender)));
        fdcat.add(new FoodCategory(R.drawable.splmeals,"Special Meals",getResources().getColor(R.color.champagnepink)));
        fdcat.add(new FoodCategory(R.drawable.eggitems,"Egg Items",getResources().getColor(R.color.chinesewhite)));
        fdcat.add(new FoodCategory(R.drawable.breadparottas,"Bread & Parottas",getResources().getColor(R.color.lavender)));
        fdcat.add(new FoodCategory(R.drawable.logo,"Add Soon...",getResources().getColor(R.color.palelavender)));
        fdcat.add(new FoodCategory(R.drawable.dosa,"Dosa & Idiyappam",getResources().getColor(R.color.champagnepink)));

        FoodCategoryAdapter adapter = new FoodCategoryAdapter(this, fdcat);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FoodCategory value = adapter.getItem(i);
                //Toast.makeText(getApplicationContext(), value.getData(), Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("Category", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("categoryName", value.getData());
                editor.apply();

                Intent it=new Intent(FoodActivity.this,CategorypageActivity.class);
                startActivity(it);
            }
        });

        profile=findViewById(R.id.userprofile);
        cart=findViewById(R.id.foodactcart);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FoodActivity.this,ProfileActivity.class);
                startActivity(it);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FoodActivity.this, AddtocartActivity.class);
                startActivity(it);
            }
        });
    }
}