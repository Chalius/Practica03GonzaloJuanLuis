package com.example.practica03gonzalojuanluis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class mensaje extends AppCompatActivity {

    int contadorLlamada = 0;
    TextView lblNumero;
    TextView lblMensaje;
    private String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        lblNumero = findViewById(R.id.lblNumero);
        lblMensaje = findViewById(R.id.lblMensaje);

        //Muestra informacion en la vista si existen datos en el bundle
        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            String numero = getIntent().getExtras().getString("numero");
            lblNumero.setText(numero + "");

            mensaje = getIntent().getExtras().getString("mensaje");
            lblMensaje.setText(mensaje + "");
        }
    }


    public void guardarNumero(View v) {
        //Nuevo SHARED PREFERENCES QUE ALMACENARA DATOS DE MENSAJE Y NUMERO
        SharedPreferences datos2 = getSharedPreferences("DatosDeReceptor2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos2.edit();

        contadorLlamada=datos2.getAll().size()+1;

        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            mensaje = getIntent().getExtras().getString("mensaje");
        }

        String value = datos2.getString(lblNumero.getText().toString(),null);
        if (value == null) {
            // LA LLAVE NO EXISTE Y SE PROCEDE A AGREGAR
            editor.putString(""+lblNumero.getText().toString(),lblNumero.getText().toString());
            editor.commit();
            insertarPerfil(""+lblNumero.getText().toString(),mensaje);

            Intent mostrarMensajes = new Intent(this, MainActivity.class);
            startActivity(mostrarMensajes);
            finish();
        } else {
            // LA LLAVE EXISTE Y NO SE AGREGA
            Intent mostrarMensajes = new Intent(this, MainActivity.class);
            startActivity(mostrarMensajes);
            finish();
        }


    }


    private void insertarPerfil(String xfec, String xdes){
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.NUMERO, xfec);
        cv.put(DatabaseHelper.MENSAJE, xdes);
        db.insert("registros",DatabaseHelper.NUMERO,cv);
        db.close();
    }






    public void cerrar(View v){


        finish();
    }



}
