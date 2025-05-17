package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.text.InputType;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button but;
    TextView tx;
    CheckBox termsCond;
    ImageView eyeToggle;
    boolean isPasswordVisible = false;


    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set padding for edge-to-edge content around system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Bind UI components
        username = findViewById(R.id.emailET);
        password = findViewById(R.id.passwordET);
        but = findViewById(R.id.signinbutton);
        tx = findViewById(R.id.signuptext);
        termsCond = findViewById(R.id.termsCB);
        eyeToggle = findViewById(R.id.eyeToggle);


        // Login button click listener
        but.setOnClickListener(v -> {
            if (username.getText().toString().isEmpty()) {
                username.setError("Please enter your email");
            } else if (password.getText().toString().isEmpty()) {
                password.setError("Please enter your password");
            } else if (!termsCond.isChecked()) {
                Toast.makeText(getApplicationContext(), "Please accept terms and conditions", Toast.LENGTH_LONG).show();
            } else {
                loginUserAccount();
            }
        });

        eyeToggle.setOnClickListener(v -> {
            if (isPasswordVisible) {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                eyeToggle.setImageResource(R.drawable.ic_eye_closed); // your closed eye icon
            } else {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyeToggle.setImageResource(R.drawable.ic_eye_open); // your open eye icon
            }
            password.setSelection(password.getText().length()); // move cursor to end
            isPasswordVisible = !isPasswordVisible;
        });


        // Sign up text click listener - navigate to SignupActivity
        tx.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(it);
            finish();
        });
    }

    private void loginUserAccount() {
        String email = username.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        // Attempt login with Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                        Intent it = new Intent(MainActivity.this, StaticFoodPageActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Login failed! " + (task.getException() != null ? task.getException().getMessage() : ""),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
