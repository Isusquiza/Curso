package com.jesusmariagarcia.petagram2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jesusmariagarcia.petagram2.pojo.Mascota;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by jesusmgarcia on 19/3/17.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + " INTEGER" +
                ")";

        String queryCrearTablaRating = "CREATE TABLE " + ConstantesBaseDatos.TABLE_RATING_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_RATING_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_RATING_MASCOTAS_VALUE + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaRating);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_RATING_MASCOTAS);

        onCreate(db);
    }

    public ArrayList<Mascota> obtenerMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM "+ ConstantesBaseDatos.TABLE_MASCOTAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext())
        {
            Mascota mascotaActual = new Mascota();

            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombreMascota(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryRating = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_RATING_MASCOTAS_VALUE + ") as rating" +
                    " FROM " + ConstantesBaseDatos.TABLE_RATING_MASCOTAS +
                    " WHERE " + ConstantesBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA + "=" + mascotaActual.getId();

            Cursor registrosRating = db.rawQuery(queryRating, null);
            if(registrosRating.moveToNext())
                mascotaActual.setRating(registrosRating.getInt(0));
            else
                mascotaActual.setRating(0);

            mascotas.add(mascotaActual);

        }

        db.close();

        return mascotas;
    }

    public ArrayList<Mascota> obtenerTop5() {
        ArrayList<Mascota> mascotasTop5 = new ArrayList<>();
        ArrayList<Mascota> mascotasBaseDatos = new ArrayList<>();
        ArrayList<Integer> ratingList = new ArrayList<>();

        int Rating = 0;
        String query = "SELECT * FROM "+ ConstantesBaseDatos.TABLE_MASCOTAS;

        //
        // Obtengo todos los registros
        //

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext())
        {
            Mascota mascotaActual = new Mascota();

            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombreMascota(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            int id = registros.getInt(0);

            //
            // Obtengo el numero total de likes
            //

            String queryRating = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_RATING_MASCOTAS_VALUE + ") as rating" +
                    " FROM " + ConstantesBaseDatos.TABLE_RATING_MASCOTAS +
                    " WHERE " + ConstantesBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA + "=" + id;

            Cursor registrosRating = db.rawQuery(queryRating, null);
            if(registrosRating.moveToNext())
                mascotaActual.setRating(registrosRating.getInt(0));
            else
                mascotaActual.setRating(0);

            mascotasBaseDatos.add(mascotaActual);

            Rating = mascotaActual.getRating();

            ratingList.add(Rating);

        }

        //
        // Busco en la lista de mascotas de la base de datos las 5 mascotas con más Likes
        //

        for(int element = 0; element < 5; element++) {
            Integer i = Collections.max(ratingList);
            int index = ratingList.indexOf(i);

            mascotasTop5.add(mascotasBaseDatos.get(index));

            ratingList.remove(index);
            mascotasBaseDatos.remove(index);

        }

        db.close();

        return mascotasTop5;
    }

    public void insertarMascota(ContentValues contentValues) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();

    }

    public void insertarLikeMascota(ContentValues contentValues) {

        SQLiteDatabase db = this.getWritableDatabase();
        db. insert(ConstantesBaseDatos.TABLE_RATING_MASCOTAS, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota) {
        int likes = 0;

        String query = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_RATING_MASCOTAS_VALUE + ")" +
                " FROM " + ConstantesBaseDatos.TABLE_RATING_MASCOTAS +
                " WHERE " + ConstantesBaseDatos.TABLE_RATING_MASCOTAS_ID_MASCOTA + "=" + mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext()) {
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }
}
