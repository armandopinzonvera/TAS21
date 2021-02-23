package com.noFreeGps.tas21.SQLite;

public class Entidad_Ttrack {

    private String tabla_track;
    private String id_track;
    private String fecha;
    private String hora;
    private float longitud;
    private float latitud;
    private int altura;
    private String especie;
    private int densidad;

    public Entidad_Ttrack(String tabla_track, String id_track, String fecha, String hora, float longitud, float latitud, int altura, String especie, int densidad) {
        this.tabla_track = tabla_track;
        this.id_track = id_track;
        this.fecha = fecha;
        this.hora = hora;
        this.longitud = longitud;
        this.latitud = latitud;
        this.altura = altura;
        this.especie = especie;
        this.densidad = densidad;
    }

    public Entidad_Ttrack() {

    }

    public String getTabla_track() {
        return tabla_track;
    }

    public String getId_track() {
        return id_track;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public float getLongitud() {
        return longitud;
    }

    public float getLatitud() {
        return latitud;
    }

    public int getAltura() {
        return altura;
    }

    public String getEspecie() {
        return especie;
    }

    public int getDensidad() {
        return densidad;
    }

    public void setTabla_track(String tabla_track) {
        this.tabla_track = tabla_track;
    }

    public void setId_track(String id_track) {
        this.id_track = id_track;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }
}

