package com.example.meteoapp.meteoapp.data.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Envia una petición GET a la API de AEMET
 * La petición de datos meteorologicos podrá ser acerca de
 * datos diarios o horarios (solo se cubren esos dos supuestos
 * en la aplicación).
 *
 * La respuesta es un JSON que incluye un campo "estado" y otro
 * llamado "datos" con la URL a un JSON detallado
 * Ese ultimo JSON se procesa en la clase MeteoApiService.java
 * @author Pablo Cano
 */

public class MeteoApiFirstRequest {

    private String requestUrl;
    private final String API_KEY;

    public MeteoApiFirstRequest() {
       requestUrl = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/";
       API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYWJjYW5mcmFAYWx1LmV" +
                "kdS5ndmEuZXMiLCJqdGkiOiI3MWE5MmUzZC1hNGNjLTQ5NWItYWY2NC03Y2Y2YzEyOTZjM2UiLCJpc3MiO" +
                "iJBRU1FVCIsImlhdCI6MTY2NjY5MzM1NSwidXNlcklkIjoiNzFhOTJlM2QtYTRjYy00OTViLWFmNjQtN2N" +
                "mNmMxMjk2YzNlIiwicm9sZSI6IiJ9.dCkNvDzJ6NNTMPG-JT3RBdgVo1_eKqrowalPxRS8GO0";
    }

    public String sendRequest(boolean diaria, int municipio) throws IOException, JSONException {
        requestUrl += diaria ? "diaria" : "horaria";
        requestUrl += "/" + municipio;
        requestUrl += "?api_key=" + API_KEY;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(requestUrl)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();
        JSONObject jsonObject = new JSONObject(jsonData);

        int statusCode = jsonObject.getInt("estado");
        if (statusCode == 200) {
            return jsonObject.getString("datos");
        }

        Log.e("errorPeticion", "codigo de estado:" + statusCode);
        return null;
    }
}
