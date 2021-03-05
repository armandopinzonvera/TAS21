package com.noFreeGps.tas21.SQLite.entidades;
/***
 * Es el POJO de la tabla track, en esta clase
 * se hace referencia a los campos como las variables
 * se presenta un constructor con parametros y uno sin,
 * ademas de los metodos setter and getter
 *
 * */
public class Entidad_Ttrack {

    public int id_track;
    private String fecha;
    private String hora;
    private float longitud;
    private float latitud;
    private int altura;
    private String fk_idTransecto;


    public Entidad_Ttrack( /*int id_track, */String fecha, String hora, float longitud, float latitud, int altura, String fk_idTransecto) {

       /* this.id_track = id_track;*/
        this.fecha = fecha;
        this.hora = hora;
        this.longitud = longitud;
        this.latitud = latitud;
        this.altura = altura;
        this.fk_idTransecto = fk_idTransecto;
    }

    public Entidad_Ttrack() {

    }

    @Override
    public String toString() {
        return "Entidad_Ttrack{" +
                "id_track=" + id_track +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", longitud=" + longitud +
                ", latitud=" + latitud +
                ", altura=" + altura +
                '}';
    }

    public int getId_track() {

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

    public String getFk_idTransecto(){

        return fk_idTransecto;
    }
 ///////////////////////////////////////////////////////

    public void setId_track(int id_track) {

        this.id_track = id_track;
    }

    public void setFecha(String fecha) {

        this.fecha = fecha;
    }

    public void setHora(String hora) {

        this.hora = hora;
    }

    public void setLongitud(float longitud){
    this.longitud = longitud;
    }

    public void setLatitud(float latitud) {

        this.latitud = latitud;
    }

    public void setAltura(int altura) {

        this.altura = altura;
    }

}

