package com.noFreeGps.tas21.SQLite.interfaces;

import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;

public interface Dao_Ttrack {

    boolean addDatoTtrack(Entidad_Ttrack entidadTtrack);

    String verificarNuevoTrack(String idTrack);

    void iniciarTrack(String nombreProyecto, String idTrack);
}
