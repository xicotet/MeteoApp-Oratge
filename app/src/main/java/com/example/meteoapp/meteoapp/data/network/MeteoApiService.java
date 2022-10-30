package com.example.meteoapp.meteoapp.data.network;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeteoApiService {

    private static Retrofit retrofit = null;

    public static Retrofit getService(boolean diaria, int municipio) throws JSONException, IOException {
        MeteoApiFirstRequest firstRequest = new MeteoApiFirstRequest();
        String dataUrl = firstRequest.sendRequest(diaria, municipio);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(dataUrl + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }





}

