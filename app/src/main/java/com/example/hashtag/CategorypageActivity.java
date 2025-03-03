package com.example.hashtag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CategorypageActivity extends AppCompatActivity {

    GridView grid1;

    ArrayList<FoodItemModal> fditm;

    ArrayList<FoodItemModal> fdfilter;

    ImageView imgbg;
    TextView catename;

    ImageView arr,cart;

    DatabaseReference dbRef;

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

        dbRef = FirebaseDatabase.getInstance().getReference();

        grid1=(GridView) findViewById(R.id.itemsGV1);
        fditm=new ArrayList<FoodItemModal>();
        imgbg=findViewById(R.id.bg);
        catename=findViewById(R.id.catname);

        fditm.add(new FoodItemModal(R.drawable.attukalpayasoup,"Attukal Paya Soup","₹120","per 250ml","Soup"));
        fditm.add(new FoodItemModal(R.drawable.hotandsourchickensoup,"Hot & Sour Chicken Soup","₹250","per 250ml","Soup"));
        fditm.add(new FoodItemModal(R.drawable.chickenmilagu,"Chicken Milagu Soup","₹129","per 250ml","Soup"));
        fditm.add(new FoodItemModal(R.drawable.muttonmilagu,"Mutton Milagu Soup","₹179","per 250ml","Soup"));
        fditm.add(new FoodItemModal(R.drawable.sweetcornchicken,"Sweetcorn Chicken Soup","₹161","per 250ml","Soup"));
        fditm.add(new FoodItemModal(R.drawable.sweetcornveg,"Sweetcorn veg Soup","₹123","per 250ml","Soup"));


        fditm.add(new FoodItemModal(R.drawable.muttonliverroast,"Mutton Liver Roast","₹180","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.pichipottachicken,"Pichi Potta Chicken","₹250","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.fishfinger,"Fish Fingers","₹243","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.chickensixfive,"Chicken 65","₹250","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.gopimanchurian,"Gopi Manchurian","₹120","per 100g","Starters"));
        fditm.add(new FoodItemModal(R.drawable.chickenlollipop,"Chicken Lollipop","₹149","per 100g","Starters"));


        fditm.add(new FoodItemModal(R.drawable.chickenbriyani,"Chicken Briyani","₹130","per 1kg","Briyani"));
        fditm.add(new FoodItemModal(R.drawable.muttonbriyani,"Mutton Briyani","₹435","per 1kg","Briyani"));
        fditm.add(new FoodItemModal(R.drawable.eggbriyani,"Egg Briyani","₹95","per 1kg","Briyani"));
        fditm.add(new FoodItemModal(R.drawable.fishbriyani,"Fish Briyani","₹260","per 1kg","Briyani"));
        fditm.add(new FoodItemModal(R.drawable.prawnbriyani,"Prawn Briyani","₹320","per 1kg","Briyani"));
        fditm.add(new FoodItemModal(R.drawable.paneerbriyani,"Paneer Briyani","₹240","per 1kg","Briyani"));


        fditm.add(new FoodItemModal(R.drawable.muttonmasala,"Mutton Masala","₹349","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.chickenvaruvalmasala,"Chicken Varuval Masala","₹249","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.paneerbuttermasala,"Paneer Butter Masala","₹435","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.eggmasala,"Egg Masala","₹100","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.chilliprawns,"Chilli Prawn","₹280","per 1kg","Curries & Gravies"));
        fditm.add(new FoodItemModal(R.drawable.butterchickenmasala,"Butter Chicken Masala","₹120","per 1kg","Curries & Gravies"));


        fditm.add(new FoodItemModal(R.drawable.ksatriyachickenkabeba,"Ksatriya Chicken Kebab","₹200","per 450g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.alfahambarbeque,"Alfaham Barbeque","₹235","per 450g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.barbequechicken,"Barbeque Chicken","₹199","per 250g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.pepperchickenbarbeque,"Pepper Chicken BBQ","₹380","per 450g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.periperiredchicken,"Peri Peri Red Chicken","₹470","per 600g","Kebabs & Barbeque"));
        fditm.add(new FoodItemModal(R.drawable.tandoorichicken,"Tandoori Chicken","₹415","7 piece","Kebabs & Barbeque"));


        fditm.add(new FoodItemModal(R.drawable.bucketchickenbriyani,"Bucket Chicken combo","₹699","per 450g","Bucket Briyani"));
        fditm.add(new FoodItemModal(R.drawable.bucketmuttonbriyani,"Bucket Mutton combo","₹799","per 450g","Bucket Briyani"));
        fditm.add(new FoodItemModal(R.drawable.bucketvegbriyani,"Bucket veg Combo","₹599","per 250g","Bucket Briyani"));
        fditm.add(new FoodItemModal(R.drawable.chickencambowithsixfive,"Chicken Combo with 65","₹399","per 450g","Bucket Briyani"));


        fditm.add(new FoodItemModal(R.drawable.chickenfriedrice,"Chicken Fried Rice","₹125","per 750g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.eggfriedrice,"Egg Fried Rice","₹115","per 750g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.paneerfriedrice,"Paneer Fried Rice","₹115","per 750g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.splcornfriedrice,"# Spl Corn Fried Rice","₹145","per 750g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.chickennoodles,"Chicken Noodles","₹125","per 700g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.eggpasta,"Egg Pasta","₹100","per 500g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.chickenpasta,"Chicken Pasta","₹120","per 500g","Rice & Noodles"));
        fditm.add(new FoodItemModal(R.drawable.eggnoodles,"Egg Noodles","₹110","per 500g","Rice & Noodles"));


        fditm.add(new FoodItemModal(R.drawable.nonvegmeals,"Non-Veg Meals","₹299","1 plate","Special Meals"));
        fditm.add(new FoodItemModal(R.drawable.vegmeals,"Veg Meals","₹249","1 plate","Special Meals"));


        fditm.add(new FoodItemModal(R.drawable.chickenkarrandi,"Chicken Karandi","₹300","1 egg","Egg Items"));
        fditm.add(new FoodItemModal(R.drawable.kalaiki,"Kalaki","₹30","1 piece","Egg Items"));
        fditm.add(new FoodItemModal(R.drawable.plainomelete,"Plain Omelette","₹64","1 piece","Egg Items"));
        fditm.add(new FoodItemModal(R.drawable.masalaomelebet,"Masala Omelette","₹70","1 piece","Egg Items"));


        fditm.add(new FoodItemModal(R.drawable.butterchicken,"Butter Naan","₹26","1 set","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.cyeleonparotta,"Ceylon Egg Parotta","₹132","1 set","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.parotta,"Parotta","₹50","1 set","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.eggkothuparotta,"Egg Kothu Parotta","₹119","per 500g","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.chickenparotto,"Chicken Kothu Parotta","₹129","per 500g","Bread & Parottas"));
        fditm.add(new FoodItemModal(R.drawable.chappathi,"Chapathi","₹50","1 set","Bread & Parottas"));


        fditm.add(new FoodItemModal(R.drawable.chcikenkaridosai,"Chicken Kari Dosai","₹110","1 set","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.idiyappam,"Idiyappam","₹25","1 set","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.appam,"Appam","₹60","1 set","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.kaldosai,"Kal Dosai","₹40","1 set","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.eggdosai,"Egg Dosai","₹60","1 piece","Dosa & Idiyappam"));
        fditm.add(new FoodItemModal(R.drawable.eggappam,"Egg Appam","₹80","1 piece","Dosa & Idiyappam"));


        SharedPreferences sharedPreferences = getSharedPreferences("Category", MODE_PRIVATE);
        String categoryName= sharedPreferences.getString("categoryName", "");
        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();


        catename.setText(categoryName);

        if (categoryName.equals("Soup")) {
            imgbg.setImageResource(R.drawable.soupbg);
        } else if (categoryName.equals("Starters")) {
            imgbg.setImageResource(R.drawable.starterbg);
        } else if (categoryName.equals("Briyani")) {
            imgbg.setImageResource(R.drawable.briyanibg);
        } else if (categoryName.equals("Curries & Gravies")) {
            imgbg.setImageResource(R.drawable.curryandgraviesbg);
        } else if (categoryName.equals("Kebabs & Barbeque")) {
            imgbg.setImageResource(R.drawable.kebebsbg);
        } else if (categoryName.equals("Bucket Briyani")) {
            imgbg.setImageResource(R.drawable.bucketbiryanibg);
        } else if (categoryName.equals("Rice & Noodles")) {
            imgbg.setImageResource(R.drawable.riceandnoodlesbg);
        } else if (categoryName.equals("Special Meals")) {
            imgbg.setImageResource(R.drawable.specialmealsbg);
        } else if (categoryName.equals("Egg Items")) {
            imgbg.setImageResource(R.drawable.eggitemsbg);
        } else if (categoryName.equals("Bread & Parottas")) {
            imgbg.setImageResource(R.drawable.breadandparottabg);
        } else if(categoryName.equals("Dosa & Idiyappam")) {
            imgbg.setImageResource(R.drawable.dosaandidiyappambg);
        }

        arr=findViewById(R.id.commonarrow);
        cart=findViewById(R.id.catpagecart);

        arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CategorypageActivity.this,FoodActivity.class);
                startActivity(it);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CategorypageActivity.this, AddtocartActivity.class);
                startActivity(it);
            }
        });

        fdfilter=new ArrayList<FoodItemModal>();

        for (FoodItemModal food:fditm) {
            if(food.getCategory().toString().equals(categoryName)){
                fdfilter.add(food);
            }
        }

        FoodItemAdapter adapter = new FoodItemAdapter(this,fdfilter);


        grid1.setAdapter(adapter);

        grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FoodItemModal value = adapter.getItem(i);
                Toast.makeText(getApplicationContext(), value.getName(), Toast.LENGTH_SHORT).show();

                String id = dbRef.push().getKey();
                FoodItemModal food = new FoodItemModal(id,value.getImg(),value.getName(),value.getRate(),value.getQuan(),value.getCategory());
                dbRef.child(id).setValue(food);

                Toast.makeText(CategorypageActivity.this, "Added to Cart!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
