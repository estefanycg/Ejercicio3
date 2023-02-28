package com.example.ejercicio3iparcial;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio3iparcial.data.database;

public class ActivityRegistro extends AppCompatActivity {

    EditText nombres, apellidos, edad, correo, direccion;
    Button btnGuardar, btnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombres = (EditText) findViewById(R.id.txtNombres);
        apellidos = (EditText) findViewById(R.id.txtApellidos);
        edad = (EditText) findViewById(R.id.txtEdad);
        correo = (EditText) findViewById(R.id.txtCorreo);
        direccion = (EditText) findViewById(R.id.txtDireccion);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnLista = (Button) findViewById(R.id.btnLista);

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityListaRegistros.class);
                startActivity(intent);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(!nombres.getText().toString().equals("") && !apellidos.getText().toString().equals("") &&
                        !edad.getText().equals("") && !correo.getText().equals("") && !correo.getText().equals("")) {

                    database dbRegistros = new database(ActivityRegistro.this);
                    long id = dbRegistros.insertarRegistro(nombres.getText().toString(), apellidos.getText().toString(),
                            Integer.parseInt(edad.getText().toString()), correo.getText().toString(), direccion.getText().toString());

                    if (id > 0) {
                        Toast.makeText(ActivityRegistro.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(ActivityRegistro.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ActivityRegistro.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void limpiar() {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");

    }

}