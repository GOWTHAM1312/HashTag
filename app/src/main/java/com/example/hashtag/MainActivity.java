package com.example.hashtag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button but;
    TextView tx;

    CheckBox termsCond;

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth=FirebaseAuth.getInstance();

        username=findViewById(R.id.emailET);
        password=findViewById(R.id.passwordET);
        but=findViewById(R.id.signinbutton);
        tx=findViewById(R.id.signuptext);
        termsCond=findViewById(R.id.termsCB);

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
                }
                else if(!(termsCond.isChecked()))
                {
                    Toast.makeText(getApplicationContext(),"Please accept terms and conditions",Toast.LENGTH_LONG).show();
                }
                else
                {
                    loginUserAccount();
                }
            }
        });

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this, SignupActivity.class);
                startActivity(it);
            }
        });
    }
    private void loginUserAccount()
    {
        String email, pass;
        email = username.getText().toString();
        pass = password.getText().toString();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(),"Please enter email!!",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(),"Please enter password!!",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Login successful!!",Toast.LENGTH_LONG).show();
                                    Intent it=new Intent(MainActivity.this,FoodActivity.class);
                                    startActivity(it);
                                    finish();
                                }

                                else {
                                    Toast.makeText(getApplicationContext(),"Login failed!!",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
    }
}