package com.noFreeGps.tas21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolderDatos> {

    ArrayList<String> listDatos;

    public AdaptadorRecycler(ArrayList<String> listDatos) {

        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_recycler, null, false);

        return new ViewHolderDatos(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }
    
    @Override
    public int getItemCount() {

        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tv_rv_datorecycler, tv_rv_transectos, tv_rv_riquesa, tv_rv_densidad;



        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tv_rv_datorecycler = itemView.findViewById(R.id.tv_rv_datorecycler);
            tv_rv_transectos = itemView.findViewById(R.id.tv_rv_transectos);
            tv_rv_riquesa = itemView.findViewById(R.id.tv_rv_riquesa);
            tv_rv_densidad = itemView.findViewById(R.id.tv_rv_densidad);
        }
        public void asignarDatos(String datos) {

            tv_rv_datorecycler.setText(datos);




        }
    }
}
