package com.example.meteoapp.meteoapp.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.meteoapp.databinding.FragmentDailyBinding;
import com.example.meteoapp.meteoapp.ui.viewmodel.DailyViewModel;

public class DailyFragment extends Fragment {

    private FragmentDailyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DailyViewModel dailyViewModel =
                new ViewModelProvider(this).get(DailyViewModel.class);

        binding = FragmentDailyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.txtDaily;
        dailyViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}