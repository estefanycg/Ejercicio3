package com.example.ejercicio3iparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.ejercicio3iparcial.data.database;
import com.example.ejercicio3iparcial.entidades.Registros;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewActivity extends AppCompatActivity {

    EditText txtNombres, txtApellidos, txtEdad, txtCorreo, txtDireccion;
    Button btnGuardar;
    FloatingActionButton registroEditar, registroEliminar;
    Registros registro;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEdad = findViewById(R.id.txtEdad);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtDireccion = findViewById(R.id.txtDireccion);

        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtEdad.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtDireccion.setEnabled(false);

        registroEditar = findViewById(R.id.registroEditar);
        registroEliminar = findViewById(R.id.registroEliminar);
        btnGuardar = findViewById(R.id.btnGuardarCambio);
        btnGuardar.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final database dbRegistros = new database(ViewActivity.this);
        registro = dbRegistros.verRegistro(id);

        if(registro != null){
            txtNombres.setText(registro.getNombres());
            txtApellidos.setText(registro.getApellidos());
            txtEdad.setText(String.valueOf(registro.getEdad()));
            txtCorreo.setText(registro.getCorreo());
            txtDireccion.setText(registro.getDireccion());
        }

        registroEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        registroEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewActivity.this);
                builder.setMessage("¿Desea eliminar este registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbRegistros.eliminarRegistro(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, ActivityListaRegistros.class);
        startActivity(intent);
    }


}