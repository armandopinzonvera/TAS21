package com.noFreeGps.tas21.SQLite.interfaces;

import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;

import java.util.ArrayList;

public interface Dao_Tproyecto {

    String verificarExiteProyecto(String nombreProyecto);

    void iniciarProyecto(String nombreProyecto);

    ArrayList<Entidad_Tproyecto> llenarSpinnerProyecto();
}
