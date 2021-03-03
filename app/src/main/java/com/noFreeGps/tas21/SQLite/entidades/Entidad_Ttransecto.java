package com.noFreeGps.tas21.SQLite.entidades;
/***
 * Es el POJO de la tabla transecto, en esta clase
 * se hace referencia a los campos como las variables
 * se presenta un constructor con parametros y uno sin,
 * ademas de los metodos setter and getter
 *
 * */
public class Entidad_Ttransecto {

    public String id_transecto;
    public int fk_id_Track;

    public Entidad_Ttransecto(String id_transecto, int fk_id_Track) {
        this.id_transecto = id_transecto;
        this.fk_id_Track = fk_id_Track;
    }

    public Entidad_Ttransecto() {
    }

    @Override
    public String toString() {
        return "Entidad_Ttransecto{" +
                "id_transecto='" + id_transecto + '\'' +
                ", fk_id_Track=" + fk_id_Track +
                '}';
    }

    public String getId_transecto() {
        return id_transecto;
    }

    public void setId_transecto(String id_transecto) {
        this.id_transecto = id_transecto;
    }

    public int getFk_id_Track() {
        return fk_id_Track;
    }

    public void setFk_id_Track(int fk_id_Track) {
        this.fk_id_Track = fk_id_Track;
    }
}
