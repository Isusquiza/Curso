package com.jesusmariagarcia.petagram2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jesusmariagarcia.petagram2.pojo.Mascota;
import com.jesusmariagarcia.petagram2.R;

import java.util.ArrayList;


/**
 * Created by jesusmgarcia on 4/3/17.
 */

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;

    public MascotaAdapter(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {

        Mascota mascota = mascotas.get(position);

        mascotaViewHolder.imgMascotaCV.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombreMascotaCV.setText(mascota.getNombreMascota());
        mascotaViewHolder.rating = mascota.getRating();
        mascotaViewHolder.tvRatingMascotaCV.setText(Integer.toString(mascotaViewHolder.rating));

        mascotaViewHolder.imgRateBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MascotaViewHolder holder = (MascotaViewHolder) (v.getTag());

                holder.rating++;
                holder.tvRatingMascotaCV.setText(Integer.toString(holder.rating));
            }
        });
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

        private int rating;

        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgMascotaCV = (ImageView)itemView.findViewById(R.id.imgMascota);
            imgRateBone = (ImageButton)itemView.findViewById(R.id.imgRate);
            tvNombreMascotaCV = (TextView)itemView.findViewById(R.id.tvNombreMascota);
            tvRatingMascotaCV = (TextView) itemView.findViewById(R.id.tvRatingMascota);

            rating = 0;

            imgRateBone.setTag(this);
        }
    }
}
