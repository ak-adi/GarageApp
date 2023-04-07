package com.example.garageapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetVehicleNameResponse {


    @SerializedName("Results")
    private List<VehicleName> results;

    public GetVehicleNameResponse(List<VehicleName> results) {
        this.results = results;
    }

    public List<VehicleName> getResults() {
        return results;
    }

    public void setResults(List<VehicleName> results) {
        this.results = results;
    }
}
