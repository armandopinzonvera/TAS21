package com.noFreeGps.tas21.SQLite.entidades;
/***
 * Es el POJO de la tabla especies, en esta clase
 * se hace referencia a los campos como las variables
 * se presenta un constructor con parametros y uno sin
 * ademas de los metodos setter and getter
 *
 * */
public class Entidad_Tespecies {

    int id;
    String especie;
    int densidad;
    String fk_idTrack;
    String fk_IdSProyecto;


  public Entidad_Tespecies(int id, String especie, int densidad, String fk_idTrack, String fk_IdSProyecto) {
        this.id = id;
        this.especie = especie;
        this.densidad = densidad;
        this.fk_idTrack = fk_idTrack;
        this.fk_IdSProyecto = fk_IdSProyecto;
    }
    public Entidad_Tespecies(int id, String especie, int densidad) {
        this.id = id;
        this.especie = especie;
        this.densidad = densidad;

    }


    public Entidad_Tespecies() {
    }

    @Override
    public String toString() {
        return "Entidad_Tespecies{" +
                "id=" + id +
                ", especie='" + especie + '\'' +
                ", densidad=" + densidad +
                ", fk_idTrack='" + fk_idTrack + '\'' +
                ", fk_IdSProyecto='" + fk_IdSProyecto + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFk_idTrack() {

        return fk_idTrack;
    }

    public void setFk_idTrack(String fk_idTrack) {

        this.fk_idTrack = fk_idTrack;
    }

    public String getfk_IdSProyecto() {

        return fk_IdSProyecto;
    }

    public void setfk_IdSProyecto(String fk_IdSProyecto) {

        this.fk_IdSProyecto = fk_IdSProyecto;
    }



}
