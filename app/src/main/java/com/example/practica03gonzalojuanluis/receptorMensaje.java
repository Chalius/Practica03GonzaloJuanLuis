package com.example.practica03gonzalojuanluis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class receptorMensaje extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(context, "mensaje recibido", Toast.LENGTH_LONG).show();


        Bundle b = intent.getExtras();

        if (b != null) {
            Object[] pdus = (Object[]) b.get("pdus");

            SmsMessage[] mensajes = new SmsMessage[pdus.length];

            for (int i = 0; i < mensajes.length; i++) {
                mensajes[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                String idMensaje = mensajes[i].getOriginatingAddress();
                String textoMensaje = mensajes[i].getMessageBody();


                Toast.makeText(context, "remitente: " + idMensaje, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Mensaje: " + textoMensaje, Toast.LENGTH_LONG).show();
            }
        }

    }
}

