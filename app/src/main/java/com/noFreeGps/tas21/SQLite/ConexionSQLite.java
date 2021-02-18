package com.noFreeGps.tas21.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {

    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL(UtilidadesSQLite.CREAR_TABLA_PROYECTO);
            /*db.execSQL(UtilidadesSQLite.CREAR_TABLA_TRANSECTO);
            db.execSQL(UtilidadesSQLite.CREAR_TABLA_TRACK);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+UtilidadesSQLite.TABLA_PROYECTO);
           /* db.execSQL("DROP TABLE IF EXISTS "+UtilidadesSQLite.TABLA_TRANSECTO);
            db.execSQL("DROP TABLE IF EXISTS "+UtilidadesSQLite.TABLA_TRACK);*/
    }
}
