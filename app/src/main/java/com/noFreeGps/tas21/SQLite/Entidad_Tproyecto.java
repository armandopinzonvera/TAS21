package com.noFreeGps.tas21.SQLite;

public class Entidad_Tproyecto {

    private String tabla_proyecto;
    private String nombre_proyecto;
    private String fk_transecto;

    public Entidad_Tproyecto(String tabla_proyecto, String nombre_proyecto, String fk_transecto) {
        this.tabla_proyecto = tabla_proyecto;
        this.nombre_proyecto = nombre_proyecto;
        this.fk_transecto = fk_transecto;
    }

    public Entidad_Tproyecto() {

    }

    public String getTabla_proyecto() {
        return tabla_proyecto;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public String getFk_transecto() {
        return fk_transecto;
    }

    public void setTabla_proyecto(String tabla_proyecto) {
        this.tabla_proyecto = tabla_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public void setFk_transecto(String fk_transecto) {
        this.fk_transecto = fk_transecto;
    }
}
