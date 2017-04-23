package com.jesusmariagarcia.petagram2.db;

/**
 * Created by jesusmgarcia on 19/3/17.
 */


public final class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTAS = "mascota";

    public static final String TABLE_MASCOTAS_ID        = "id";
    public static final String TABLE_MASCOTAS_NOMBRE    = "nombre";
    public static final String TABLE_MASCOTAS_FOTO      = "foto";

    public static final String TABLE_RATING_MASCOTAS = "mascota_rating";

    public static final String TABLE_RATING_MASCOTAS_ID         = "id";
    public static final String TABLE_RATING_MASCOTAS_ID_MASCOTA = "id_mascota";
    public static final String TABLE_RATING_MASCOTAS_VALUE      = "rating";

}
