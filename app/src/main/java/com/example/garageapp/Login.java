package com.example.garageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.garageapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fAuth= FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            Intent i = new Intent(Login.this,MainActivity.class);
            i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP | i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }

        binding.buttonLogin.setOnClickListener((View v) ->{

            String email = binding.loginEmail.getText().toString().trim();
            String password = binding.loginPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){

                binding.loginEmailError.setError("Email is Required");
                return;

            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.loginEmailError.setError("Invalid email");
                return;

            }
            if(TextUtils.isEmpty(password)){
                binding.loginPasswordError.setError("Password is Required");
                return;

            }
            if(password.length()<6){
                binding.loginPasswordError.setError("Password must be greater than 6");
                return;

            }

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this,MainActivity.class);
                        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP | i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(Login.this, "Error!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }

}