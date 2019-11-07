package com.example.practica03gonzalojuanluis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class llamada extends AppCompatActivity {


    TextView lblNumero;

    int contadorLlamada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);

        lblNumero = findViewById(R.id.lblNumero);
        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            String numero = getIntent().getExtras().getString("numero");
            lblNumero.setText(numero + "");
        }



    }
    /* FUNCION GONZALO, SOBRE ESCRIBE LA PRIMERA LINEA
    public void guardarNumero(View v){
        // se apertura archivo de preferencias, para guardar datos en memoria
        SharedPreferences llamadas = getSharedPreferences("DatosDeReceptor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = llamadas.edit();
        editor.putString("numero" + contadorLlamada++,lblNumero.getText().toString());
        editor.commit();

        Intent mostrarNumeros = new Intent(this,MainActivity.class);
        startActivity(mostrarNumeros);
        finish();
    }
    */
    /*
    //******** ESTA FUNCION SI POBLA LA LISTVIEW, CREANDO UN NUEVO TAB EN CADA INTERACCION
    public void guardarNumero(View v){
        // se apertura archivo de preferencias, para guardar datos en memoria
        SharedPreferences datos = getSharedPreferences("DatosDeReceptor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos.edit();
        contadorLlamada=datos.getAll().size()+1;
        editor.putString("numero" + contadorLlamada++,lblNumero.getText().toString());
        editor.commit();

        Intent mostrarNumeros = new Intent(this,MainActivity.class);
        mostrarNumeros.putExtra("a","numero"+contadorLlamada);
        startActivity(mostrarNumeros);
        finish();
    }
    */

    //FUNCION QUE EVITA AGREGAR NUMEROS REPETIDOS
    public void guardarNumero(View v) {
        //Nuevo SHARED PREFERENCES QUE ALMACENARA DATOS DE MENSAJE Y NUMERO
        SharedPreferences datos = getSharedPreferences("DatosDeReceptor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos.edit();

        contadorLlamada=datos.getAll().size()+1;

        String value = datos.getString(lblNumero.getText().toString(),null);
        if (value == null) {
            // LA LLAVE NO EXISTE Y SE PROCEDE A AGREGAR
            editor.putString(""+lblNumero.getText().toString(),lblNumero.getText().toString());
            editor.commit();
            Intent mostrarNumeros = new Intent(this, MainActivity.class);
            startActivity(mostrarNumeros);
            finish();
        } else {
            // LA LLAVE EXISTE Y NO SE AGREGA
            Intent mostrarNumeros = new Intent(this, MainActivity.class);
            startActivity(mostrarNumeros);
            finish();
        }


    }




    public void cerrar(View v){

        finish();
    }
}

/*

SharedPreferences datos2 = getSharedPreferences("DatosDeReceptor2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos2.edit();
        editor.clear();
        editor.commit();
 */