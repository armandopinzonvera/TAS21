package com.noFreeGps.tas21.SQLite.entidades;
/***
 * Es el POJO de la tabla proyecto, en esta clase
 * se hace referencia a los campos como las variables
 * se presenta un constructor con parametros y uno sin
 * ademas de los metodos setter and getter
 *
 * */

import java.io.Serializable;

public class Entidad_Tproyecto {

    private String nombre_proyecto;


    public Entidad_Tproyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;

    }

    public Entidad_Tproyecto() {
    }

    public String getNombre_proyecto() {

        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }


    @Override
    public String toString() {
        return "Entidad_Tproyecto{" +
                "nombre_proyecto='" + nombre_proyecto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entidad_Tproyecto)) return false;

        Entidad_Tproyecto that = (Entidad_Tproyecto) o;

        return getNombre_proyecto().equals(that.getNombre_proyecto());
    }

    @Override
    public int hashCode() {
        return getNombre_proyecto().hashCode();
    }


}