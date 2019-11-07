package com.example.practica03gonzalojuanluis;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoMensajes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentoMensajes extends Fragment {

    private OnFragmentInteractionListener mListener;

    //*******DEclaracion de variables globales
    private ListView lstMensajes;
    private String[] elementosLista = null;
    private Map<String, ?> elementosGuardados = null;

    public FragmentoMensajes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_fragmento_mensajes,container,false);


        //***Creacion de lista
        lstMensajes = view.findViewById(R.id.lstMensajes);
        SharedPreferences datos2 = getActivity().getSharedPreferences("DatosDeReceptor2",Context.MODE_PRIVATE);
        elementosGuardados = datos2.getAll();
        elementosLista = elementosGuardados.values().toArray(new String[0]);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, elementosLista);
        lstMensajes.setAdapter(adaptador);

        //**Registrar la lista para el context menu FALTA IMPLEMENTAR EL CONTEXT MENU, SU XML Y ON OPTIONS ITEM SELECTED
        registerForContextMenu(lstMensajes);
        return view;
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.opciones_mensaje, menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int seleccionlista;
        seleccionlista = info.position;
        switch (item.getItemId()) {
            case R.id.enviarMensaje:
                /*
                String numero = ((TextView)info.targetView.findViewById(R.id.lblNumero)).getText().toString();
                mostrarVentanaPersonalizada(numero);
                */
                String numero1 = lstMensajes.getAdapter().getItem(seleccionlista).toString();
                mostrarVentanaPersonalizada(numero1);

                return true;

            case R.id.verMensajes:
                Toast.makeText(getContext(), "Ver Mensajes Por configurar", Toast.LENGTH_LONG).show();

                //______________________________________________
                //                                              |
                // ENVIAR DATOS A FRAGMENTOINFORMACION
                //
                //______________________________________________|
                String numero2 = lstMensajes.getAdapter().getItem(seleccionlista).toString();

                FragmentoInformacion fragInformacion = new FragmentoInformacion();
                Bundle args = new Bundle();
                args.putString("texto", numero2);
                fragInformacion.setArguments(args);
                FragmentTransaction transaccion = getActivity().getSupportFragmentManager().beginTransaction();
                transaccion.replace(R.id.contenedor,fragInformacion);
                transaccion.commit();

                //______________________________________________
                //                                              |
                // FIN DE ENVIAR DATOS A FRAGMENTOINFORMACION
                //
                //______________________________________________|

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void mostrarVentanaPersonalizada(final String numero){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.ventana_personalizada, null);
        dialogBuilder.setView(dialogView);

        final EditText lblMensaje = dialogView.findViewById(R.id.mensaje);

        dialogBuilder.setTitle("Mensaje a " + numero);
        dialogBuilder.setMessage("Ingrese contenido del mensaje");
        dialogBuilder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 123);
                    }
                    Intent envioSMS = new Intent(Intent.ACTION_SEND, Uri.parse("smsto:" + numero));
                    envioSMS.putExtra("sms_body", lblMensaje.getText().toString());
                    startActivity(envioSMS);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
