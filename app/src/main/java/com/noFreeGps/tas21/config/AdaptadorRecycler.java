package com.noFreeGps.tas21.config;
/**
 *
 * */


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noFreeGps.tas21.R;
import com.noFreeGps.tas21.SQLite.entidades.Entidad_Tespecies;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<Entidad_Tespecies> entidadTespecies;
    private View.OnClickListener listener;

    public AdaptadorRecycler(ArrayList<Entidad_Tespecies> listaEntidadSpc) {
        this.entidadTespecies = listaEntidadSpc;
    }
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recycler, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }
    @Override
    public void onClick(View v) {
            if(listener !=null){
                listener.onClick(v);
            }

    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        Entidad_Tespecies campo = entidadTespecies.get(position);
        holder.tv_rv_nombreProyecto.setText(campo.getfk_IdSProyecto());
        holder.tv_rv_transectos.setText(campo.getFk_idTrack());
        holder.tv_rv_riquesa.setText(campo.getEspecie());
        holder.tv_rv_densidad.setText(Integer.toString(campo.getDensidad()));
    }
    @Override
    public int getItemCount() {
        return entidadTespecies.size();
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
