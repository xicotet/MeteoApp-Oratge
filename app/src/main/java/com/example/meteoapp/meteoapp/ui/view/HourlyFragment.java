package com.example.meteoapp.meteoapp.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.meteoapp.databinding.FragmentHourlyBinding;
import com.example.meteoapp.meteoapp.ui.viewmodel.HourlyViewModel;

public class HourlyFragment extends Fragment {

    private FragmentHourlyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HourlyViewModel hourlyViewModel =
                new ViewModelProvider(this).get(HourlyViewModel.class);

        binding = FragmentHourlyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.txtHourly;
        hourlyViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}