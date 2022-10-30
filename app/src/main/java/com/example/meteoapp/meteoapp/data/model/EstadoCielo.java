package com.example.meteoapp.meteoapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstadoCielo {

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("periodo")
    @Expose
    private String periodo;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
