package com.noFreeGps.tas21.SQLite.entidades;

import java.io.Serializable;

/***
 * Es el POJO de la tabla transecto, en esta clase
 * se hace referencia a los campos como las variables
 * se presenta un constructor con parametros y uno sin,
 * ademas de los metodos setter and getter
 *
 * */
public class Entidad_Ttransecto implements Serializable {

    public String id_transecto;
    public String fk_nombreProyecto;

    public Entidad_Ttransecto(String id_transecto, String fk_nombreProyecto) {
        this.id_transecto = id_transecto;
        this.fk_nombreProyecto = fk_nombreProyecto;
    }

    public Entidad_Ttransecto() {
    }

    @Override
    public String toString() {
        return "Entidad_Ttransecto{" +
                "id_transecto='" + id_transecto + '\'' +
                ", fk_id_Track=" + fk_nombreProyecto +
                '}';
    }

    public String getId_transecto() {
        return id_transecto;
    }

    public void setId_transecto(String id_transecto) {
        this.id_transecto = id_transecto;
    }

    public String getFk_nombreProyecto() {

        return fk_nombreProyecto;
    }

    public void setFk_nombreProyecto(String fk_nombreProyecto) {
        this.fk_nombreProyecto = fk_nombreProyecto;
    }
}
