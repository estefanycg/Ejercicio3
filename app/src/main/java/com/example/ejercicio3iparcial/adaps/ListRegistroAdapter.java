package com.example.ejercicio3iparcial.adaps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ejercicio3iparcial.R.layout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ejercicio3iparcial.R;
import com.example.ejercicio3iparcial.ViewActivity;
import com.example.ejercicio3iparcial.entidades.Registros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListRegistroAdapter extends RecyclerView.Adapter<ListRegistroAdapter.RegistroViewHolder> {

    ArrayList<Registros> listaRegistros;
    ArrayList<Registros> listaOriginal;


    public ListRegistroAdapter(ArrayList<Registros> listaRegistros) {
        this.listaRegistros = listaRegistros;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaRegistros);

    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.lista_item_registro, null, false);
        return new RegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        holder.viewNombres.setText(listaRegistros.get(position).getNombres());
        holder.viewApellidos.setText(listaRegistros.get(position).getApellidos());
        holder.viewEdad.setText(String.valueOf(listaRegistros.get(position).getEdad()));
        holder.viewCorreo.setText(listaRegistros.get(position).getCorreo());
        holder.viewDireccion.setText(listaRegistros.get(position).getDireccion());

    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaRegistros.clear();
            listaRegistros.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Registros> collection = listaRegistros.stream()
                        .filter(i -> i.getNombres().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaRegistros.clear();
                listaRegistros.addAll(collection);
            } else {
                for (Registros c : listaOriginal) {
                    if (c.getNombres().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaRegistros.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaRegistros.size();
    }

    public class RegistroViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombres, viewApellidos, viewEdad, viewCorreo, viewDireccion;

        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombres = itemView.findViewById(R.id.viewNombres);
            viewApellidos = itemView.findViewById(R.id.viewApellidos);
            viewEdad = itemView.findViewById(R.id.viewEdad);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("ID", listaRegistros.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }

    }
}
