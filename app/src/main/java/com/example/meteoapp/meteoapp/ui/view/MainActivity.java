package com.example.meteoapp.meteoapp.ui.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toolbar;

import com.example.meteoapp.R;
import com.example.meteoapp.meteoapp.data.model.Forecast;
import com.example.meteoapp.meteoapp.data.network.MeteoApiClient;
import com.example.meteoapp.meteoapp.data.network.MeteoApiFirstRequest;
import com.example.meteoapp.meteoapp.data.network.MeteoApiService;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.meteoapp.databinding.ActivityMainBinding;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        //MaterialToolbar toolbar = binding.topAppBar;
        //setActionBar((Toolbar) toolbar);

        BottomNavigationView navView = binding.navView;
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        //Lanzar el fragmento correspondiente cuando se selecciona un item del navView
        NavigationUI.setupWithNavController(navView, navController);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
                try {
                    Retrofit retrofit = MeteoApiService.getService(false, 21021);
                    MeteoApiClient meteoApiClient = retrofit.create(MeteoApiClient.class);
                    Call<List<Forecast>> apiClientCall = meteoApiClient.getForecast();
                    Response<List<Forecast>> apiClientExecute = apiClientCall.execute();
                    Forecast forecast = apiClientExecute.body().get(0);
                    Log.i("Respuesta", forecast.toString());


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        });

    }

}