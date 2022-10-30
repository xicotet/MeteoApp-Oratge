package com.example.meteoapp.meteoapp.data.network;

import com.example.meteoapp.meteoapp.data.model.Forecast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MeteoApiClient {
    @GET(".")
    Call<List<Forecast>> getForecast();
}
