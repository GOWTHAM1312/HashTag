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

public class SignupActivity extends AppCompatActivity {

    private EditText username, password, confirmpassword;
    private Button signupButton;
    private TextView signinText;
    private CheckBox termsCheckbox;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase Auth
        fAuth = FirebaseAuth.getInstance();

        // Bind UI elements
        username = findViewById(R.id.emailETS);
        password = findViewById(R.id.passwordETS);
        confirmpassword = findViewById(R.id.confirmpasswordETS);
        signupButton = findViewById(R.id.signupbutton);
        signinText = findViewById(R.id.signuptext);
        termsCheckbox = findViewById(R.id.termsCBsignup);

        // Sign Up button click
        signupButton.setOnClickListener(v -> {
            String email = username.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String confirmPass = confirmpassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                username.setError("Please enter your email");
            } else if (TextUtils.isEmpty(pass)) {
                password.setError("Please enter your password");
            } else if (TextUtils.isEmpty(confirmPass)) {
                confirmpassword.setError("Please confirm your password");
            } else if (!pass.equals(confirmPass)) {
                confirmpassword.setError("Passwords do not match");
            } else if (!termsCheckbox.isChecked()) {
                Toast.makeText(getApplicationContext(), "Please accept terms and conditions", Toast.LENGTH_LONG).show();
            } else {
                registerNewUser(email, pass);
            }
        });

        // Already have account? Sign in
        signinText.setOnClickListener(v -> {
            Intent it = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(it);
            finish();
        });
    }

    private void registerNewUser(String email, String pass) {
        fAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        finish();
                    } else {
                        String errorMessage = (task.getException() != null)
                                ? task.getException().getMessage()
                                : "Registration failed! Please try again.";
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
    }
}
