package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class LogoActivity extends AppCompatActivity {
    ImageView img;
    FirebaseAuth fAuth;

    @Override
    protected void onStart()
    {
        super.onStart();
        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null) {
            Intent it = new Intent(LogoActivity.this, FoodActivity.class);
            startActivity(it);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        img=(ImageView) findViewById(R.id.arrowIcon);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(LogoActivity.this,GetstartedActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
}