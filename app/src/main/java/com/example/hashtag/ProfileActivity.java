package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    EditText nameET, phoneET, addressET;
    Button updateBtn, logoutBtn;
    ImageView backarr;

    FirebaseAuth fAuth;
    FirebaseUser user;

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

        nameET = findViewById(R.id.nameET); // Make sure this exists in layout
        phoneET = findViewById(R.id.mobileET);
        addressET = findViewById(R.id.addressET);
        updateBtn = findViewById(R.id.updatebuttton);
        logoutBtn = findViewById(R.id.logoutbutton);
        backarr = findViewById(R.id.arrbackprofile);

        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        updateBtn.setOnClickListener(v -> {
            String name = nameET.getText().toString().trim();
            String phone = phoneET.getText().toString().trim();
            String address = addressET.getText().toString().trim();
            String email = user.getEmail();

            if (name.isEmpty()) {
                nameET.setError("Enter name");
                return;
            }
            if (phone.isEmpty()) {
                phoneET.setError("Enter phone number");
                return;
            }
            if (address.isEmpty()) {
                addressET.setError("Enter address");
                return;
            }

            // Save to Firebase
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("name", name);
            userMap.put("phone", phone);
            userMap.put("address", address);
            userMap.put("email", email);

            userRef.setValue(userMap)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfileActivity.this, StaticFoodPageActivity.class));
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
                    });
        });

        logoutBtn.setOnClickListener(v -> {
            fAuth.signOut();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            finish();
        });

        backarr.setOnClickListener(v -> finish());
    }
}
