package com.example.meteoapp.meteoapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HourlyViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HourlyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is hourly fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}