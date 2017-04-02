package com.jesusmariagarcia.petagram2.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jesusmariagarcia.petagram2.db.ConstructorMascotas;
import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;
import com.jesusmariagarcia.petagram2.pojo.Mascota;
import com.jesusmariagarcia.petagram2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by jesusmgarcia on 4/3/17.
 */

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    ArrayList<FotoPerfil> mascotas;
    Activity activity;

    public MascotaAdapter(ArrayList<FotoPerfil> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {

        final FotoPerfil mascota = mascotas.get(position);

        //mascotaViewHolder.imgMascotaCV.setImageResource(mascota.getFoto());

        Picasso.with(activity)
                .load(mascota.getFoto())
                .placeholder(R.drawable.puppy1)
                .into(mascotaViewHolder.imgMascotaCV);
        mascotaViewHolder.tvNombreMascotaCV.setText(mascota.getFullName());
        mascotaViewHolder.tvRatingMascotaCV.setText(Integer.toString(mascota.getRating()));

        /*mascotaViewHolder.imgRateBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MascotaViewHolder holder = (MascotaViewHolder) (v.getTag());

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.insertRating(mascota);
                holder.tvRatingMascotaCV.setText(Integer.toString(constructorMascotas.obtenerRatingMascota(mascota)));

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgMascotaCV;
        private ImageButton imgRateBone;
        private TextView tvNombreMascotaCV;
        private TextView tvRatingMascotaCV;


        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgMascotaCV = (ImageView)itemView.findViewById(R.id.imgMascota);
            imgRateBone = (ImageButton)itemView.findViewById(R.id.imgRate);
            tvNombreMascotaCV = (TextView)itemView.findViewById(R.id.tvNombreMascota);
            tvRatingMascotaCV = (TextView) itemView.findViewById(R.id.tvRatingMascota);

            imgRateBone.setTag(this);
        }
    }
}
