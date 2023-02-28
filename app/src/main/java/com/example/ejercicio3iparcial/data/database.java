package com.example.ejercicio3iparcial.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.ejercicio3iparcial.entidades.Registros;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteConnection{

    Context context;

    public database (@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarRegistro(String nombres, String apellidos, int edad, String correo, String direccion ) {

        long id = 0;

        try {
            SQLiteConnection dbHelper = new SQLiteConnection (context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombres", nombres);
            values.put("apellidos", apellidos);
            values.put("edad", edad);
            values.put("correo", correo);
            values.put("direccion", direccion);

            id = db.insert(TABLE_REGISTROS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Registros> mostrarRegistros() {

        SQLiteConnection dbHelper = new SQLiteConnection(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Registros> listaRegistros = new ArrayList<>();
        Registros registros;
        Cursor cursorRegistros;

        cursorRegistros = db.rawQuery("SELECT * FROM " + TABLE_REGISTROS + " ORDER BY nombres ASC", null);

        if (cursorRegistros.moveToFirst()) {
            do {
                registros = new Registros();
                registros.setId(cursorRegistros.getInt(0));
                registros.setNombres(cursorRegistros.getString(1));
                registros.setApellidos(cursorRegistros.getString(2));
                registros.setEdad(cursorRegistros.getInt(3));
                registros.setCorreo(cursorRegistros.getString(4));
                registros.setDireccion(cursorRegistros.getString(5));
                listaRegistros.add(registros);
            } while (cursorRegistros.moveToNext());
        }

        cursorRegistros.close();

        return listaRegistros;
    }

    public Registros verRegistro(int id) {

        SQLiteConnection dbHelper = new SQLiteConnection(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Registros registros = null;
        Cursor cursorRegistros;

        cursorRegistros = db.rawQuery("SELECT * FROM " + TABLE_REGISTROS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorRegistros.moveToFirst()) {
            registros = new Registros();
            registros.setId(cursorRegistros.getInt(0));
            registros.setNombres(cursorRegistros.getString(1));
            registros.setApellidos(cursorRegistros.getString(2));
            registros.setEdad(cursorRegistros.getInt(3));
            registros.setCorreo(cursorRegistros.getString(4));
            registros.setDireccion(cursorRegistros.getString(5));
        }

        cursorRegistros.close();

        return registros;
    }

    public boolean eliminarRegistro(int id) {

        boolean correcto = false;

        SQLiteConnection dbHelper = new SQLiteConnection(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_REGISTROS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }



    public boolean editarRegistro(int id, String nombres, String apellidos, int edad, String correo, String direccion) {

        boolean correcto = false;

        SQLiteConnection dbHelper = new SQLiteConnection(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_REGISTROS + " SET nombres = '" + nombres + "', apellidos = '" + apellidos + "', edad = '" + edad + "', correo = '" + correo + "', direccion = '" + direccion + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }




}

