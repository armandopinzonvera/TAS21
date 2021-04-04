package com.noFreeGps.tas21.SQLite.interfaces;

import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tespecies;

import java.util.ArrayList;

public interface Dao_Tespecie {

    ArrayList<Entidad_Tespecies> resultadoConsultar(String nombreProyecto);

    boolean addDatoEspecie(Entidad_Tespecies entidadTespecie);
}
