package com.example.meteoapp;

import android.content.Context;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meteoapp.databinding.FragmentSearchBinding;
import com.example.meteoapp.meteoapp.data.model.ParserMunicipios;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;

    public SearchFragment() {

    }

    /*public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        List<String> pueblosLista = new ArrayList<>();
        ArrayAdapter<String> pueblosAdapter = new ArrayAdapter<>(
                getContext(),
                R.layout.search_suggestion_item,
                R.id.txtSuggestionName,
                pueblosLista
        );

        binding.lvAvailableTown.setAdapter(pueblosAdapter);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                InputStream inputStream = getContext().getAssets().open("codigos_municipios.xml");
                ParserMunicipios parserMunicipios = new ParserMunicipios(inputStream);

                handler.post(() -> {
                    pueblosAdapter.clear();
                    pueblosAdapter.addAll(parserMunicipios.getListaMunicipios());
                });

                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        });
        //executor.shutdown();


        //Para que muestre el teclado cuando recibe focus el SearchView
        binding.svSearchTown.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if(focused){
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                } else {
                    imm.hideSoftInputFromWindow(container.getWindowToken(), 0);
                }
            }
        });

        binding.svSearchTown.requestFocus();

        binding.svSearchTown.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //Cuando se presiona boton de busqueda del teclado
                binding.svSearchTown.clearFocus(); //Quitar el cursor
                if(pueblosLista.contains(query)){
                    pueblosAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pueblosAdapter.getFilter().filter(newText);
                return false;
            }
        });

        binding.lvAvailableTown.setOnItemClickListener((adapterView, view, i, l) -> {
            String nombrePueblo = (String) adapterView.getItemAtPosition(i);
            String codigoMunicipio = ParserMunicipios.getCodigoMunicipio(nombrePueblo);

            Bundle args = new Bundle();
            args.putSerializable("codigoMunicipio", codigoMunicipio);

            NavHostFragment.findNavController(SearchFragment.this)
                    .navigate(R.id.action_searchFragment_to_navigation_home2, args);
        });

        binding.imgbtnClose.setOnClickListener(view -> {
            Log.i("cierra", "cierra coño");
            NavHostFragment.findNavController(SearchFragment.this)
                    .navigate(R.id.action_searchFragment_to_navigation_home2);
        });

        return binding.getRoot();
    }
}