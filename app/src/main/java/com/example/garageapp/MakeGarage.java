package com.example.garageapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MakeGarage {

    @SerializedName("Count")
    private int count;

    @SerializedName("Message")
    private String message;

    @SerializedName("Results")
    private List<Make> results;

    public MakeGarage(int count, String message, List<Make> results) {
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

    public List<Make> getResults() {
        return results;
    }

    public void setResults(List<Make> results) {
        this.results = results;
    }
}
