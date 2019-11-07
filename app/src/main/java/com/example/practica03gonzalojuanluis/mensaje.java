package com.example.practica03gonzalojuanluis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class mensaje extends AppCompatActivity {

    int contadorLlamada = 0;
    TextView lblNumero;
    TextView lblMensaje;

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

            String mensaje = getIntent().getExtras().getString("mensaje");
            lblMensaje.setText(mensaje + "");
        }
    }


    public void guardarNumero(View v) {
        //Nuevo SHARED PREFERENCES QUE ALMACENARA DATOS DE MENSAJE Y NUMERO
        SharedPreferences datos2 = getSharedPreferences("DatosDeReceptor2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos2.edit();

        contadorLlamada=datos2.getAll().size()+1;


        String value = datos2.getString(lblNumero.getText().toString(),null);
        if (value == null) {
            // LA LLAVE NO EXISTE Y SE PROCEDE A AGREGAR
            editor.putString(""+lblNumero.getText().toString(),lblNumero.getText().toString());
            editor.commit();
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


    public void cerrar(View v){


        finish();
    }



}
