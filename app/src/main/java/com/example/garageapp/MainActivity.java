package com.example.garageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.garageapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GarageAPI garageAPI = RetrofitGarage.getClient().create(GarageAPI.class);
        Call<MakeGarage> call = garageAPI.getAllMakes();
        call.enqueue(new Callback<MakeGarage>() {
            @Override
            public void onResponse(Call<MakeGarage> call, Response<MakeGarage> response) {
                Log.i("Response",response.body().toString());
                MakeGarage makeGarage = response.body();
                if (response.isSuccessful()){
                   List<Make> list = makeGarage.getResults();
                   ArrayAdapter<Make> spinnerAdapter = new ArrayAdapter<Make>(MainActivity.this,
                           android.R.layout.simple_spinner_item,list);
                   spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   binding.spinner1.setAdapter(spinnerAdapter);
                }
            }
            @Override
            public void onFailure(Call<MakeGarage> call, Throwable t) {

            }
        });

        binding.buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this,Login.class);
                i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP | i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });


    }


}