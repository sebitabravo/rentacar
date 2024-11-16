package com.example.rentacar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import modelo.Entrada;

public class EntradaAdapter extends RecyclerView.Adapter<EntradaAdapter.ViewHolder> {
    public Context context;
    @NonNull
    @Override
    public EntradaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Asociamos el layout de item a nuestro adapter
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.entrada_item, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EntradaAdapter.ViewHolder holder, int position) {
        // Asociamos items del layout con datos del adaptador
        // Generamo una referencia a un objeto clase entrada
        Entrada entrada = new Entrada();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder {
    }
}
