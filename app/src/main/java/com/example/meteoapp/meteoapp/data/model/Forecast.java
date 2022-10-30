package com.example.meteoapp.meteoapp.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {


    @SerializedName("elaborado")
    @Expose
    private String elaborado;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("id")
    @Expose
    private String idMunicipio;

    @SerializedName("provincia")
    @Expose
    private String provincia;

    @SerializedName("dia")
    @Expose
    private List<Dia> prediccion = null;

    public String getElaborado() {
        return elaborado;
    }

    public void setElaborado(String elaborado) {
        this.elaborado = elaborado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<Dia> getPrediccion() {
        return prediccion;
    }

    public void setPrediccion(List<Dia> prediccion) {
        this.prediccion = prediccion;
    }


    public String getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

}