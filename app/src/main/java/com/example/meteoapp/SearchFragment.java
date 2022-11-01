package com.example.meteoapp;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meteoapp.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        String[] pueblos = {"Betxi", "Cordoba", "Bilbao", "Betera", "Corda", "Pontones",
            "Alicante", "Naquera"};
        List<String> pueblosLista = new ArrayList<String>(Arrays.asList(pueblos));
        ArrayAdapter<String> pueblosAdapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.search_suggestion_item,
                R.id.txtSuggestionName,
                pueblosLista
        );

        binding.lvAvailableTown.setAdapter(pueblosAdapter);

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
                    Log.i("submit", query);
                    pueblosAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("hola", newText);
                pueblosAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return binding.getRoot();
    }
}