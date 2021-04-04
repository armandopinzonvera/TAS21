package com.noFreeGps.tas21.SQLite.interfaces;

import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;

import java.util.ArrayList;

public interface Dao_Ttrack {

    boolean addDatoTtrack(Entidad_Ttrack entidadTtrack);

    String verificarNuevoTrack(String idTrack);

    void iniciarTrack(String nombreProyecto, String idTrack);

    ArrayList<Entidad_Ttrack> resultadoconsultarTrack(String nombreProyecto);
}
