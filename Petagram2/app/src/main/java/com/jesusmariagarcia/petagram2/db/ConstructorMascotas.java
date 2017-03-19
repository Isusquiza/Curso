package com.jesusmariagarcia.petagram2.db;

import android.content.ContentValues;
import android.content.Context;

import com.jesusmariagarcia.petagram2.R;
import com.jesusmariagarcia.petagram2.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 19/3/17.
 */

public class ConstructorMascotas {

    private Context context;
    private static final int LIKE = 1;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos() {

        BaseDatos db = new BaseDatos(context);
        insertarMascotasDummy(db);
        return db.obtenerMascotas();
    }

    public void insertarMascotasDummy(BaseDatos db) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Puppy1");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.puppy1);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Puppy2");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.puppy2);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Puppy3");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.puppy3);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Puppy4");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.puppy4);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Puppy5");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.puppy5);

        db.insertarMascota(contentValues);
    }

    public void insertRating(Mascota mascota) {

        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_RATING_MASCOTAS_VALUE, LIKE);

        db.insertarLikeMascota(contentValues);
    }

    public int obtenerRatingMascota(Mascota mascota) {

        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);

    }

    public ArrayList<Mascota> obtenerTop5() {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerTop5();
    }
}
