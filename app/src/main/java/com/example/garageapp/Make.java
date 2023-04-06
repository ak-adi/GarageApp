package com.example.garageapp;

import com.google.gson.annotations.SerializedName;

public class Make {

    @SerializedName("Make_ID")
    private int makeId;

    @SerializedName("Make_Name")
    private String makeName;

    public Make(int makeId, String makeName) {
        this.makeId = makeId;
        this.makeName = makeName;
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
}
