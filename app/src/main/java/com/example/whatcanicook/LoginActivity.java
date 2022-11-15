package com.example.whatcanicook;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    private TextView register, forgotPassword;
    private FirebaseAuth mauth;
    private EditText mail,password;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.tv_Register);
        mauth=FirebaseAuth.getInstance();
        mail=findViewById(R.id.emailLogin);
        password=findViewById(R.id.passworlogin);
        login_btn=findViewById(R.id.loginUser);
        forgotPassword=findViewById(R.id.tv_forgotPassword);
        getSupportActionBar().hide();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterUserActivity.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

    }

    private void login() {
        String user= mail.getText().toString().trim();
        String pass= password.getText().toString().trim();
        if (user.isEmpty()){
            mail.setError("Email can not be empty..");

        }if (pass.isEmpty()){
            password.setError("Password can not be empty..");
        }
        else
        {
            mauth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        if (mauth.getCurrentUser().isEmailVerified()) {
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Verify your email!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }else
                    {
                        Toast.makeText(LoginActivity.this, "Login Failed!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }
}}