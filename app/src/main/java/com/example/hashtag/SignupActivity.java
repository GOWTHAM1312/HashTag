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

public class SignupActivity extends AppCompatActivity {

    EditText username,password, confirmpassword;
    Button but;

    TextView tx;

    CheckBox termsCondsignup;

    public FirebaseAuth fAuth;

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
        fAuth=FirebaseAuth.getInstance();

        username=findViewById(R.id.emailETS);
        password=findViewById(R.id.passwordETS);
        confirmpassword=findViewById(R.id.confirmpasswordETS);
        but=findViewById(R.id.signupbutton);
        tx=findViewById(R.id.signuptext);
        termsCondsignup=findViewById(R.id.termsCBsignup);

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
                else if(!(password.getText().toString().equals(confirmpassword.getText().toString())))
                {
                    confirmpassword.setError("Password doesn't match");
                }else if(!(termsCondsignup.isChecked()))
                {
                    Toast.makeText(getApplicationContext(),"Please accept terms and conditions",Toast.LENGTH_LONG).show();
                }
                else
                {
                    registerNewUser();
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

    private void registerNewUser()
    {
        String email, pass;
        email = username.getText().toString();
        pass = confirmpassword.getText().toString();
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(),"Please enter email!!",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(getApplicationContext(),"Please enter password!!",Toast.LENGTH_LONG).show();
            return;
        }
        fAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Registration successful!",Toast.LENGTH_LONG).show();
                            Intent it=new Intent(SignupActivity.this,MainActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Registration failed!!"+ " Please try again later",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}