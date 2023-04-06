package com.example.garageapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GarageAPI {
    @GET("vehicles/getallmakes?format=json")
    Call<MakeGarage> getAllMakes();

    @GET("vehicles/GetModelsForMakeid/{make_id}?format=json")
    Call<ModelGarage> getModelsForMakeId(@Path("make_id") int makeId);
}
