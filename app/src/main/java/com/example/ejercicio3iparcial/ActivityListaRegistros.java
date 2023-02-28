package com.example.ejercicio3iparcial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.ejercicio3iparcial.data.database;
import com.example.ejercicio3iparcial.entidades.Registros;
import com.example.ejercicio3iparcial.adaps.ListRegistroAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.ejercicio3iparcial.R.*;

import java.util.ArrayList;

public class ActivityListaRegistros extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaRegistros;
    ArrayList<Registros> listaArrayRegistros;
    FloatingActionButton registroNuevo;
    ListRegistroAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_lista_registros);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaRegistros = findViewById(id.listaRegistros);
        registroNuevo = findViewById(R.id.registroNuevo);
        listaRegistros.setLayoutManager(new LinearLayoutManager(this));

        database dbRegistros = new database(ActivityListaRegistros.this);

        listaArrayRegistros = new ArrayList<>();

        adapter = new ListRegistroAdapter(dbRegistros.mostrarRegistros());
        listaRegistros.setAdapter(adapter);

        registroNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, ActivityRegistro.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }


}