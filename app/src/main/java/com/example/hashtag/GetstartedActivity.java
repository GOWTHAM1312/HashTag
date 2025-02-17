package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class GetstartedActivity extends AppCompatActivity {

    Button but;
    private ViewPager viewPager;
    private LinearLayout dotsLL;
    SliderAdapter adapter;
    private ArrayList<SliderModel> sliderModalArrayList;
    private TextView[] dots;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_getstarted);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        but=findViewById(R.id.getstartedbutton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent it=new Intent(GetstartedActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        viewPager = findViewById(R.id.idViewPager);
        dotsLL = findViewById(R.id.idLLDots);
        sliderModalArrayList = new ArrayList<>();
        sliderModalArrayList.add(new SliderModel(R.drawable.favfood,"All Your Favorurites","Your next meal is just a tap away. Explore menus, place orders, and enjoy personalized features designed for convenience"));
        sliderModalArrayList.add(new SliderModel(R.drawable.fastdel,"Fast Delivery","Experience lightning-fast delivery with our app! Enjoy quick, reliable service, ensuring your favorite meals arrive hot and fresh."));
        sliderModalArrayList.add(new SliderModel(R.drawable.choosefood,"Choose Your Food","Craving something delicious? Easily find your favorite meals with our app—browse menus, discover new dishes, and order with a tap!"));
        adapter = new SliderAdapter(GetstartedActivity.this, sliderModalArrayList);
        viewPager.setAdapter(adapter);
        size = sliderModalArrayList.size();
        addDots(size, 0);
        viewPager.addOnPageChangeListener(viewListener);

    }
    private void addDots(int size, int pos)
    {

        dots = new TextView[size];
        dotsLL.removeAllViews();
        for (int i = 0; i < size; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("•"));
            dots[i].setTextSize(40);
            dots[i].setTextColor(getResources().getColor(R.color.black));
            dotsLL.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[pos].setTextColor(getResources().getColor(R.color.green));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {addDots(size, position);}

        @Override
        public void onPageScrollStateChanged(int state){}
    };
}