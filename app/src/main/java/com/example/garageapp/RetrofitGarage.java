package com.example.garageapp;

import kotlin.jvm.Synchronized;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGarage {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://vpic.nhtsa.dot.gov/api/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}