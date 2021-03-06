package com.noFreeGps.tas21.SQLite.entidades;
/***
 * Es el POJO de la tabla track, en esta clase
 * se hace referencia a los campos como las variables
 * se presenta un constructor con parametros y uno sin,
 * ademas de los metodos setter and getter
 *
 * */
public class Entidad_Ttrack {

    public String id_track;
    private String fecha;
    private String hora;
    private String longitud;
    private String latitud;
    private String altura;
    private String fk_IdTProyecto;


    public Entidad_Ttrack(String id_track, String fecha, String hora, String longitud, String latitud, String altura, String fk_IdTProyecto) {
        this.id_track = id_track;
        this.fecha = fecha;
        this.hora = hora;
        this.longitud = longitud;
        this.latitud = latitud;
        this.altura = altura;
        this.fk_IdTProyecto = fk_IdTProyecto;
    }

    public Entidad_Ttrack() {

    }

    @Override
    public String toString() {
        return "Entidad_Ttrack{" +
                "id_track='" + id_track + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", longitud=" + longitud +
                ", latitud=" + latitud +
                ", altura=" + altura +
                ", fk_IdTProyecto='" + fk_IdTProyecto + '\'' +
                '}';
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

    public String getLongitud() {
        return longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getAltura() {
        return altura;
    }

    public String getFk_IdTProyecto(){

        return fk_IdTProyecto;
    }
 ///////////////////////////////////////////////////////


    public void setId_track(String id_track)
    {
        this.id_track = id_track;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setLongitud(String longitud){
    this.longitud = longitud;
    }

    public void setLatitud(String latitud)
    {
        this.latitud = latitud;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setFk_IdTProyecto(String fk_IdTProyecto) {
        this.fk_IdTProyecto = fk_IdTProyecto;
    }
}

