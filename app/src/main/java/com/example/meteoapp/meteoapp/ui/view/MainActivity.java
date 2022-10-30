package com.example.meteoapp.meteoapp.ui.view;

import android.os.Bundle;

import com.example.meteoapp.R;
import com.example.meteoapp.meteoapp.data.network.MeteoApiFirstRequest;
import com.example.meteoapp.meteoapp.data.network.MeteoApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.meteoapp.databinding.ActivityMainBinding;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navView;
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        //Lanzar el fragmento correspondiente cuando se selecciona un item del navView
        NavigationUI.setupWithNavController(navView, navController);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
                try {
                    MeteoApiFirstRequest meteoApiService = new MeteoApiFirstRequest();
                    meteoApiService.sendRequest(true, 21021,
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        });

    }

}