package com.example.whatcanicook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private FirebaseAuth mAuth;
    private Button registerUser;

    private TextView login_Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        registerUser = (Button) findViewById(R.id.registeruser);
        editEmail = (EditText) findViewById(R.id.emailRegister);
        login_Text = (TextView) findViewById((R.id.loginRegister));
        editPassword = (EditText) findViewById(R.id.passwordRegister);


        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        login_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterUserActivity.this, LoginActivity.class));
            }
        });
    }

    private void Register() {
        String user = editEmail.getText().toString().trim();
        String pass = editPassword.getText().toString().trim();
        if (user.isEmpty()) {
            editEmail.setError("Email can not be empty..");

        }
        if (pass.isEmpty()) {
            editPassword.setError("Password can not be empty..");
        } else {
            mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterUserActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(RegisterUserActivity.this, MainActivity.class));

                    } else {
                        Toast.makeText(RegisterUserActivity.this, "Registration Failed!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
}

