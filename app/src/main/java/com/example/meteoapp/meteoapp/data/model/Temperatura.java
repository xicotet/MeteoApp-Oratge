package com.example.meteoapp.meteoapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temperatura {

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("periodo")
    @Expose
    private String periodo;

    @SerializedName("maxima")
    @Expose
    private Integer maxima;

    @SerializedName("minima")
    @Expose
    private Integer minima;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getMaxima() {
        return maxima;
    }

    public void setMaxima(Integer maxima) {
        this.maxima = maxima;
    }

    public Integer getMinima() {
        return minima;
    }

    public void setMinima(Integer minima) {
        this.minima = minima;
    }
}