package com.noFreeGps.tas21.SQLite.entidades;
/***
 * Es el POJO de la tabla especies, en esta clase
 * se hace referencia a los campos como las variables
 * se presenta un constructor con parametros y uno sin
 * ademas de los metodos setter and getter
 *
 * */
public class Entidad_Especies {

    String especie;
    int densidad;

    public Entidad_Especies(String especie, int densidad) {
        this.especie = especie;
        this.densidad = densidad;
    }

    public Entidad_Especies() {
    }

    @Override
    public String toString() {
        return "Entidad_Especies{" +
                "especie='" + especie + '\'' +
                ", densidad=" + densidad +
                '}';
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getDensidad() {
        return densidad;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }
}
