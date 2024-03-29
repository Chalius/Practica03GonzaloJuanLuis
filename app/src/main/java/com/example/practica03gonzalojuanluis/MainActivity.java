package com.example.practica03gonzalojuanluis;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.practica03gonzalojuanluis.ui.main.SectionsPagerAdapter;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        FragmentoLlamadas.OnFragmentInteractionListener,
        FragmentoMensajes.OnFragmentInteractionListener,
        FragmentoInformacion.OnFragmentInteractionListener
{


    //test

    int contadorLlamada = 0;
    String numero ;


    private Map<String, ?> elementosGuardados = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        Stetho.initializeWithDefaults(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //__________________________________________
        //                                          |
        // Otorgando permisos a nivel de codigo para llamar
        //__________________________________________|
        final int READ_PHONE_STATE = 123;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE);
        }
        final int READ_CALL_LOG = 456;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, READ_CALL_LOG);
        }

        final int MAKE_PHONE_CALLS = 789;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, MAKE_PHONE_CALLS);
        }

        //__________________________________________
        //                                          |
        // Fin de permisos
        //__________________________________________|


        //__________________________________________
        //                                          |
        // CAPTURANDO DATOS ENVIADOS DESDE RECEPTORLLAMADA
        //__________________________________________|

        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            // obteniendo el número del telefono
            numero = getIntent().getExtras().getString("numero");

            // recuperando datos del shared preferences
            SharedPreferences llamadas = this.getSharedPreferences("DatosDeReceptor", Context.MODE_PRIVATE);
            elementosGuardados = llamadas.getAll();

        }
        //__________________________________________
        //                                          |
        // FIN DE CAPTURA DE DATOS
        //__________________________________________|







        //PETICION DE PERMISOS ANDROID LECTURA SMS Y LLAMADAS+++++

        final int READ_SMS = 0;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, READ_SMS);
        }

        //PERMISO QUE HABILITA LA RECEPCION DE MENSAJES Y CREACION DE LLAMADA.JAVA
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant  = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED){
            String[] permission_list = new String [1];
            permission_list[0]=permission;
            ActivityCompat.requestPermissions(this, permission_list,1);
        }






    }









    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    public void buscar(View v){
/*
        resultado = findViewById(R.id.tv_resultado);
        String nombre = nombre_buscado.getText().toString();
        DataBaseHelper dbh = new DataBaseHelper(getActivity());
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM productos WHERE TRIM(nombre_producto) = '"+nombre.trim()+"'", null);
        if(c.moveToFirst()){
            String cod = c.getString(0);
            String nom = c.getString(1);
            String pre = c.getString(2);
            resultado.setText(cod+" | "+ nom+" | "+pre);
        }else{
            Toast.makeText(getBaseContext(),"Producto no encontrado",Toast.LENGTH_LONG).show();
        }
        db.close();
*/
    }
}