package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    EditText phonenunber, address;
    Button udbut, logoutbut;
    ImageView backarr;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fAuth = FirebaseAuth.getInstance();

        phonenunber = findViewById(R.id.mobileET);
        address = findViewById(R.id.addressET);
        udbut = findViewById(R.id.updatebuttton);
        logoutbut = findViewById(R.id.logoutbutton);
        backarr = findViewById(R.id.arrbackprofile);

        udbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phonenunber.getText().toString().isEmpty()) {
                    phonenunber.setError("Please Enter your Phone Number");
                } else if (address.getText().toString().isEmpty()) {
                    address.setError("Please Enter your Address");
                } else {
                    Intent it = new Intent(ProfileActivity.this, FoodActivity.class);
                    startActivity(it);
                    finish();  // prevent going back to profile after update
                }
            }
        });

        logoutbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                Intent it = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(it);
                finish();  // close profile activity on logout
            }
        });

        backarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // just close this screen, back to previous
            }
        });
    }
}
