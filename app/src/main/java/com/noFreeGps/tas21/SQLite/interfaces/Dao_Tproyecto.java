package com.noFreeGps.tas21.SQLite.interfaces;

public interface Dao_Tproyecto {

    String verificarExiteProyecto(String nombreProyecto);
    void iniciarProyecto(String nombreProyecto, String idTrack);
}
