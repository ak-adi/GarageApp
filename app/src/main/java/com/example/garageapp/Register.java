package com.example.garageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.garageapp.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ActivityRegisterBinding binding;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        if(fAuth.getCurrentUser() != null);{
            Intent i = new Intent(Register.this,MainActivity.class);
            startActivity(i);
        }

        binding.buttonRegisterSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.registerEmail.getText().toString().trim();
                String userName = binding.registerUserName.getText().toString().trim();
                String password = binding.registerPassword.getText().toString().trim();
                String number = binding.registerPhone.getText().toString().trim();

                if (TextUtils.isEmpty(userName)){
                    binding.userNameError.setError("Name is Required");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    binding.registerEmailError.setError("Email is Required");
                    return;

                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.registerEmailError.setError("Invalid email");
                    return;
                }
                if(TextUtils.isEmpty(number)){
                    binding.phoneError.setError("Mobile number is required");
                    return;
                }
                if(number.length() < 10){
                    binding.phoneError.setError("Invalid number");
                }
                if (TextUtils.isEmpty(password)) {
                    binding.phoneError.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    binding.phoneError.setError("Password Must be >=6 Characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userId = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userId);
                            Map<String, Object> user = new HashMap<>();
                            user.put("userName", userName);
                            user.put("email", email);
                            user.put("number", number);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess: user profile created for "+ userId);
                                    Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Intent i = new Intent(Register.this,MainActivity.class);
                            i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP | i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(Register.this, "Error!!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }
}