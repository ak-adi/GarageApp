package com.example.garageapp;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("Make_ID")
    double id;

    @SerializedName("Make_Name")
    String name;

    public Result(double id, String name) {
        this.id = id;
        this.name = name;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
