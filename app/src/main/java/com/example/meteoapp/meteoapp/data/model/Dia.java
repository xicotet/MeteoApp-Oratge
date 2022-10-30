package com.example.meteoapp.meteoapp.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dia {

    @SerializedName("estadoCielo")
    @Expose
    private List<EstadoCielo> estadoCielo = null;

    @SerializedName("probPrecipitacion")
    @Expose
    private List<ProbPrecipitacion> probPrecipitacion = null;

    @SerializedName("temperatura")
    @Expose
    private List<Temperatura> temperatura = null;

    @SerializedName("humedadRelativa")
    @Expose
    private List<HumedadRelativa> humedadRelativa = null;

    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("orto")
    @Expose
    private String orto;
    @SerializedName("ocaso")
    @Expose
    private String ocaso;

    @SerializedName("uvMax")
    @Expose
    private Integer uvMax;

    public List<EstadoCielo> getEstadoCielo() {
        return estadoCielo;
    }

    public void setEstadoCielo(List<EstadoCielo> estadoCielo) {
        this.estadoCielo = estadoCielo;
    }

    public List<ProbPrecipitacion> getProbPrecipitacion() {
        return probPrecipitacion;
    }

    public void setProbPrecipitacion(List<ProbPrecipitacion> probPrecipitacion) {
        this.probPrecipitacion = probPrecipitacion;
    }

    public List<Temperatura> getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(List<Temperatura> temperatura) {
        this.temperatura = temperatura;
    }

    public List<HumedadRelativa> getHumedadRelativa() {
        return humedadRelativa;
    }

    public void setHumedadRelativa(List<HumedadRelativa> humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getOrto() {
        return orto;
    }

    public void setOrto(String orto) {
        this.orto = orto;
    }

    public String getOcaso() {
        return ocaso;
    }

    public void setOcaso(String ocaso) {
        this.ocaso = ocaso;
    }

    public Integer getUvMax() {
        return uvMax;
    }

    public void setUvMax(Integer uvMax) {
        this.uvMax = uvMax;
    }
}
