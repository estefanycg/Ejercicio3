package com.example.ejercicio3iparcial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio3iparcial.data.database;
import com.example.ejercicio3iparcial.entidades.Registros;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombres, txtApellidos, txtEdad, txtCorreo, txtDireccion;
    Button btnGuardarCambio;
    FloatingActionButton registroEditar, registroEliminar;
    boolean correcto = false;
    Registros registro;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEdad = findViewById(R.id.txtEdad);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtDireccion = findViewById(R.id.txtDireccion);

        registroEditar = findViewById(R.id.registroEditar);
        registroEliminar = findViewById(R.id.registroEliminar);

        registroEditar.setVisibility(View.INVISIBLE);
        registroEliminar.setVisibility(View.INVISIBLE);

        btnGuardarCambio = findViewById(R.id.btnGuardarCambio);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final database dbRegistros = new database(EditarActivity.this);
        registro = dbRegistros.verRegistro(id);

        if (registro != null) {

            txtNombres.setText(registro.getNombres());
            txtApellidos.setText(registro.getApellidos());
            txtEdad.setText(String.valueOf(registro.getEdad()));
            txtCorreo.setText(registro.getCorreo());
            txtDireccion.setText(registro.getDireccion());

        }

        btnGuardarCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombres.getText().toString().equals("") && !txtApellidos.getText().toString().equals("")
                        && !txtEdad.getText().toString().equals("") && !txtCorreo.getText().toString().equals("")
                        && !txtDireccion.getText().toString().equals("")) {
                    correcto = dbRegistros.editarRegistro(id, txtNombres.getText().toString(), txtApellidos.getText().toString(),
                            Integer.parseInt(txtEdad.getText().toString()), txtCorreo.getText().toString(), txtDireccion.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void verRegistro(){
        Intent intent = new Intent(this, ActivityListaRegistros.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }




}
