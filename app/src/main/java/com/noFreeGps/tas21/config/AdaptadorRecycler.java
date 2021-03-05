package com.noFreeGps.tas21.config;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tproyecto;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolderDatos> {


    ArrayList<Entidad_Tproyecto> listaEntidadTproyecto;

    public AdaptadorRecycler(ArrayList<Entidad_Tproyecto> listaEntidadTproyecto) {
        this.listaEntidadTproyecto = listaEntidadTproyecto;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recycler, null, false);
        return new ViewHolderDatos(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.tv_rv_nombreProyecto.setText(listaEntidadTproyecto.get(position).getNombre_proyecto());
    }
    
    @Override
    public int getItemCount() {

        return listaEntidadTproyecto.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tv_rv_nombreProyecto;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tv_rv_nombreProyecto = itemView.findViewById(R.id.tv_rv_nombreProyecto);

        }

    }
}
