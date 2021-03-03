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
    private String fk_id_transecto;

    public Entidad_Tproyecto(String nombre_proyecto, String fk_id_transecto) {
        this.nombre_proyecto = nombre_proyecto;
        this.fk_id_transecto = fk_id_transecto;
    }

    @Override
    public String toString() {
        return "Entidad_Tproyecto{" +
                "nombre_proyecto='" + nombre_proyecto + '\'' +
                ", fk_transecto='" + fk_id_transecto + '\'' +
                '}';
    }

    public Entidad_Tproyecto() {
    }

    public String getNombre_proyecto() {

        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public String getFk_transecto() {

        return fk_id_transecto;
    }
    public void setFk_transecto(String fk_id_transecto) {

        this.fk_id_transecto = fk_id_transecto;
    }
}