package com.noFreeGps.tas21.config;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Ttrack;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolderDatos> {

    ArrayList<Entidad_Ttrack> listaEntidadTrack;




    public AdaptadorRecycler(ArrayList<Entidad_Ttrack> listaEntidadTrack) {
        this.listaEntidadTrack = listaEntidadTrack;
    }
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recycler, null, false);
        return new ViewHolderDatos(view);
    }
    int cantidad;
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        Entidad_Ttrack campo = listaEntidadTrack.get(position);
       // cantidad =campo.getFk_IdTProyecto().length();
        holder.tv_rv_nombreProyecto.setText(campo.getFk_IdTProyecto());
        holder.tv_rv_transectos.setText(campo.getId_track());


    }
    
    @Override
    public int getItemCount() {

        return listaEntidadTrack.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tv_rv_nombreProyecto, tv_rv_transectos, tv_rv_riquesa, tv_rv_densidad;



        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tv_rv_nombreProyecto = itemView.findViewById(R.id.tv_rv_nombreProyecto);
            tv_rv_transectos = itemView.findViewById(R.id.tv_rv_transectos);
            tv_rv_riquesa = itemView.findViewById(R.id.tv_rv_riquesa);
            tv_rv_densidad = itemView.findViewById(R.id.tv_rv_densidad);
        }

    }
}
