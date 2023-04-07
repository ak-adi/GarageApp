package com.example.garageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.garageapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    GarageAPI garageAPI = RetrofitGarage.getClient().create(GarageAPI.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        carNameAPI();

        binding.buttonSignOut.setOnClickListener(view -> {
            logoutFromFirebase();
        });


    }

    private void logoutFromFirebase() {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(MainActivity.this, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void carNameAPI() {
        Call<GetVehicleNameResponse> call = garageAPI.getAllMakes();
        call.enqueue(new Callback<GetVehicleNameResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetVehicleNameResponse> call, Response<GetVehicleNameResponse> response) {
                GetVehicleNameResponse getVehicleNameResponse = response.body();
                if (response.isSuccessful()) {
                    assert getVehicleNameResponse != null;
                    List<VehicleName> list = getVehicleNameResponse.getResults();
                    setVehicleNameInSpinner(list);
                }
            }

            @Override
            public void onFailure(Call<GetVehicleNameResponse> call, Throwable t) {

            }
        });

    }

    private void setVehicleNameInSpinner(List<VehicleName> list) {
        CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(MainActivity.this,
                android.R.layout.simple_spinner_item, list);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner1.setAdapter(spinnerAdapter);
        binding.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                VehicleName vehicleName = spinnerAdapter.getItem(position);
                getVehicleModelAPI(vehicleName.getMakeId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });
    }

    private void getVehicleModelAPI(int makeId) {
        Call<GetVehicleModelResponse> call = garageAPI.getModelsForMakeId(makeId);
        call.enqueue(new Callback<GetVehicleModelResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetVehicleModelResponse> call, Response<GetVehicleModelResponse> response) {
                GetVehicleModelResponse garage = response.body();
                if (response.isSuccessful()) {
                    List<String> modelNameList = new ArrayList<>();
                    assert garage != null;
                    for (VehicleModel result : garage.getResults()) {
                        modelNameList.add(result.getModelName());
                    }
                    setVehicleModelInSpinner(modelNameList);


                }
            }

            @Override
            public void onFailure(Call<GetVehicleModelResponse> call, Throwable t) {

            }
        });

    }

    private void setVehicleModelInSpinner(List<String> modelNameList) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item, modelNameList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner2.setAdapter(spinnerAdapter);
    }
}