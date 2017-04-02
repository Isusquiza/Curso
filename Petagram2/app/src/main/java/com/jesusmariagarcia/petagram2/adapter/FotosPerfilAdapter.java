package com.jesusmariagarcia.petagram2.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jesusmariagarcia.petagram2.R;
import com.jesusmariagarcia.petagram2.pojo.FotoPerfil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 12/3/17.
 */

public class FotosPerfilAdapter extends RecyclerView.Adapter<FotosPerfilAdapter.FotoPerfilViewHolder> {

    ArrayList<FotoPerfil> fotosPerfil;
    Activity activity;

    public FotosPerfilAdapter(ArrayList<FotoPerfil> fotosPerfil, Activity activity) {
        this.fotosPerfil = fotosPerfil;
        this.activity = activity;
    }

    @Override
    public FotoPerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil_mascota, parent, false);

        return new FotoPerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FotoPerfilViewHolder fotoPerfilViewHolder, int position) {

        FotoPerfil fotoPerfil = fotosPerfil.get(position);

        Picasso.with(activity)
                .load(fotoPerfil.getFoto())
                .placeholder(R.drawable.puppy1)
                .into(fotoPerfilViewHolder.imgPerfilMascotaCV);
        fotoPerfilViewHolder.tvPerfilRatingMascotaCV.setText(Integer.toString(fotoPerfil.getRating()));
    }

    @Override
    public int getItemCount() {
        return fotosPerfil.size();
    }

    public static class FotoPerfilViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPerfilMascotaCV;
        private TextView tvPerfilRatingMascotaCV;

        public FotoPerfilViewHolder(View itemView) {
            super(itemView);

            imgPerfilMascotaCV = (ImageView)itemView.findViewById(R.id.imgPerfilMascota);
            tvPerfilRatingMascotaCV = (TextView)itemView.findViewById(R.id.tvRatingPerfilMascota);
        }
    }
}
