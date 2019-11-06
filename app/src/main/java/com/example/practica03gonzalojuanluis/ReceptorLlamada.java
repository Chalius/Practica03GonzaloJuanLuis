package com.example.practica03gonzalojuanluis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

public class ReceptorLlamada extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        Toast.makeText(context, "Llamada entrante", Toast.LENGTH_LONG).show();


        // Se extrae información del evento recepcionado
        String estado = "";
        String numero = "";
        Bundle extras = intent.getExtras();
        if (extras != null) {
            estado = extras.getString(TelephonyManager.EXTRA_STATE);
            if (estado.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                numero = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Intent mostrarActividad = new Intent(context,llamada.class);
                mostrarActividad.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mostrarActividad.putExtra("numero",numero);
                context.startActivity(mostrarActividad);
            }
        }



    }






}
