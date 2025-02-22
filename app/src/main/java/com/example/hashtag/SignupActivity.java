package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {

    EditText username,password, confirmpassword;
    Button but;

    TextView tx;

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

        username=findViewById(R.id.emailETS);
        password=findViewById(R.id.passwordETS);
        confirmpassword=findViewById(R.id.confirmpasswordETS);
        but=findViewById(R.id.signupbutton);
        tx=findViewById(R.id.signuptext);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty())
                {
                    username.setError("Please Enter your Username");
                }
                else if (password.getText().toString().isEmpty())
                {
                    password.setError("Please Enter your Password");
                } else if (confirmpassword.getText().toString().isEmpty())
                {
                    confirmpassword.setError("Please Confirm your Password");
                }
                else
                {
                    Intent it=new Intent(SignupActivity.this,FoodActivity.class);
                    startActivity(it);
                    finish();
                    }
            }
        });

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }
}