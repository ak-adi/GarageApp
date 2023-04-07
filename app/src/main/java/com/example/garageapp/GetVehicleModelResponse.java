package com.example.garageapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetVehicleModelResponse {

    @SerializedName("Results")
    private List<VehicleModel> results;

    public GetVehicleModelResponse(List<VehicleModel> results) {
        this.results = results;
    }

    public List<VehicleModel> getResults() {
        return results;
    }

    public void setResults(List<VehicleModel> results) {
        this.results = results;
    }
}
