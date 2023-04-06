package com.example.garageapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelGarage {

    @SerializedName("Count")
    private int count;

    @SerializedName("Message")
    private String message;

    @SerializedName("Results")
    private List<Model> results;

    public ModelGarage(int count, String message, List<Model> results) {
        this.count = count;
        this.message = message;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Model> getResults() {
        return results;
    }

    public void setResults(List<Model> results) {
        this.results = results;
    }
}
