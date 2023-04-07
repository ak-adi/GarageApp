package com.example.garageapp;

import com.google.gson.annotations.SerializedName;

public class VehicleModel {

    @SerializedName("Make_ID")
    private int makeId;

    @SerializedName("Make_Name")
    private String makeName;

    @SerializedName("Model_ID")
    private int modelId;

    @SerializedName("Model_Name")
    private String modelName;

    @SerializedName("Model_Years")
    private String modelYears;

    public VehicleModel(int makeId, String makeName, int modelId, String modelName, String modelYears) {
        this.makeId = makeId;
        this.makeName = makeName;
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelYears = modelYears;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelYears() {
        return modelYears;
    }

    public void setModelYears(String modelYears) {
        this.modelYears = modelYears;
    }
}
