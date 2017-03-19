package com.jesusmariagarcia.petagram2.db;

import android.content.Context;
import com.jesusmariagarcia.petagram2.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 19/3/17.
 */

public class ConstructorTop5Mascotas {

    private Context context;
    private static final int LIKE = 1;

    public ConstructorTop5Mascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerTop5() {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerTop5();
    }
}
