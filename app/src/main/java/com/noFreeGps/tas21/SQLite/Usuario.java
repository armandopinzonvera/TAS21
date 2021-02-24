package com.noFreeGps.tas21.SQLite;
import java.io.Serializable;

public class Usuario implements Serializable {

    private String nombre_proyecto;
    private String fk_transecto;

    public Usuario(String nombre_proyecto, String fk_transecto) {
        this.nombre_proyecto = nombre_proyecto;
        this.fk_transecto = fk_transecto;
    }

    public Usuario() {
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public String getFk_transecto() {
        return fk_transecto;
    }

    public void setFk_transecto(String fk_transecto) {
        this.fk_transecto = fk_transecto;
    }
}