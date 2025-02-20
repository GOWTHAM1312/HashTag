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

        fditm.add(new FoodItemModal(R.drawable.attukalpayasoup,"Attukal Paya Soup","₹120","per 250ml","Soups"));
        fditm.add(new FoodItemModal(R.drawable.hotandsourchickensoup,"Hot & Sour Chicken Soup","₹250","per 250ml","Soups"));
        fditm.add(new FoodItemModal(R.drawable.chickenmilagu,"Chicken Milagu Soup","₹129","per 250ml","Soups"));
        fditm.add(new FoodItemModal(R.drawable.muttonmilagu,"Mutton Milagu Soup","₹179","per 250ml","Soups"));
        fditm.add(new FoodItemModal(R.drawable.sweetcornchicken,"Sweetcorn Chicken Soup","₹161","per 250ml","Soups"));
        fditm.add(new FoodItemModal(R.drawable.sweetcornveg,"Sweetcorn veg Soup","₹123","per 250ml","Soups"));


        fditm.add(new FoodItemModal(R.drawable.muttonliverroast,"Mutton Liver Roast","₹180","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.pichipottachicken,"Pichi Potta Chicken","₹250","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.fishfinger,"Fish Fingers","₹243","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.chickensixfive,"Chicken 65","₹250","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.gopimanchurian,"Gopi Manchurian","₹120","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.chickenlollipop,"Chicken Lollipop","₹149","per 100g","Starters"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Chicken Biryani","₹130","per 1kg","Biryani"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Mutton Briyani","₹435","per 1kg","Biryani"));
        fditm.add(new FoodItemModal(R.drawable.eggbriyani,"Egg Biryani","₹95","per 1kg","Biryani"));
        fditm.add(new FoodItemModal(R.drawable.fishbriyani,"Fish Biryani","₹260","per 1kg","Biryani"));
        fditm.add(new FoodItemModal(R.drawable.prawnbriyani,"Prawn Biryani","₹320","per 1kg","Biryani"));
        fditm.add(new FoodItemModal(R.drawable.paneerbriyani,"Paneer Biryani","₹240","per 1kg","Biryani"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Mutton Masala","₹349","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Chicken Varuval Masala","₹249","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.eggbriyani,"Paneer Butter Masala","₹435","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.fishbriyani,"Egg Masala","₹100","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.prawnbriyani,"Chilli Prawn","₹280","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.paneerbriyani,"Butter Chicken Masala","₹120","per 1kg","Curries & Gravies"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Ksatriya Chicken Kebab","₹200","per 450g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Alfaham Barbeque","₹235","per 450g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.eggbriyani,"Barbeque Chicken","₹199","per 250g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.fishbriyani,"Pepper Chicken BBQ","₹380","per 450g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.prawnbriyani,"Peri Peri Red Chicken","₹470","per 600g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.paneerbriyani,"Tandoori Chicken","₹415","7 piece","Kebabs & Barbeque"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Bucket Chicken combo","₹699","per 450g","Bucket Biryani"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Bucket Mutton combo","₹799","per 450g","Bucket Biryani"));
        fditm.add(new FoodItemModal(R.drawable.eggbriyani,"Bucket veg Combo","₹599","per 250g","Bucket Biryani"));
        fditm.add(new FoodItemModal(R.drawable.fishbriyani,"Chicken Combo with 65","₹399","per 450g","Bucket Biryani"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Chicken Fried Rice","₹125","per 750g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Egg Fried Rice","₹115","per 750g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.eggbriyani,"Paneer Fried Rice","₹115","per 750g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.fishbriyani,"# Spl Corn Fried Rice","₹145","per 750g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.prawnbriyani,"Chicken Noodles","₹125","per 700g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.paneerbriyani,"Egg Pasta","₹100","per 500g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.prawnbriyani,"Chicken Pasta","₹120","per 500g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.paneerbriyani,"Egg Noodles","₹110","per 500g","Rice & Noodles"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Non-Veg Meals","₹299","1 plate","Special Meals"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Veg Meals","₹249","1 plate","Special Meals"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Chicken Karandi","₹300","1 egg","Egg Items"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Kalaki","₹30","1 piece","Egg Items"));
        fditm.add(new FoodItemModal(R.drawable.eggbriyani,"Plain Omelette","₹64","1 piece","Egg Items"));
        fditm.add(new FoodItemModal(R.drawable.fishbriyani,"Masala Omelette","₹70","1 piece","Egg Items"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Butter Naan","₹26","1 set","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Ceylon Egg Parotta","₹132","1 set","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.eggbriyani,"Parotta","₹50","1 set","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.fishbriyani,"Egg Kothu Parotta","₹119","per 500g","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.prawnbriyani,"Chicken Kothu Parotta","₹129","per 500g","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.paneerbriyani,"Chapathi","₹50","1 set","Bread & Parottas"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Chicken Kari Dosai","₹110","1 set","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Idiyappam","₹25","1 set","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.eggbriyani,"Appam","₹60","1 set","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.fishbriyani,"Kal Dosai","₹40","1 set","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.prawnbriyani,"Egg Dosai","₹60","1 piece","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.paneerbriyani,"Egg Appam","₹80","1 piece","Dosa & Idiyappam"));

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
