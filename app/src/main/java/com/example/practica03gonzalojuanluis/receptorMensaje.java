package com.example.practica03gonzalojuanluis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.provider.Telephony;

import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class receptorMensaje extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        //Toast.makeText(context, "mensaje entrante", Toast.LENGTH_LONG).show();

        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                String senderNumber = "";
                String message = "";
                for (int i = 0; i < pdus.length; i++) {
                    //extrae el texto del sms 160 caracteres como maximo
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    //extrae el numero de remintente y lo almacena en senderNumber
                    senderNumber = sms.getOriginatingAddress();
                    //extrae el cuerpo del mensaje
                    message = sms.getDisplayMessageBody();
                    Toast.makeText(context, "De: " + senderNumber + " Mensaje: " + message, Toast.LENGTH_LONG).show();
                }

                //ENVIAR LOS DATOS AL INTENT
                Intent mostrarActividad = new Intent(context, mensaje.class);
                mostrarActividad.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mostrarActividad.putExtra("numero", senderNumber);
                mostrarActividad.putExtra("mensaje", message);
                context.startActivity(mostrarActividad);
            }
        }
    }
}

