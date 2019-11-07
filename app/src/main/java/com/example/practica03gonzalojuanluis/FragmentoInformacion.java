package com.example.practica03gonzalojuanluis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoInformacion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentoInformacion extends Fragment {

    private OnFragmentInteractionListener mListener;

    public FragmentoInformacion() {
        // Required empty public constructor
    }
    DatabaseHelper dbh;
    TextView tv_mensajeRecibido;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_informacion, container, false);
        String recibido="";

        //______________________________________________
        //                                              |
        // CODIGO PARA PODER RECIBIR DATOS DESDE FRAGMENTOMENSAJE
        //
        //______________________________________________|

        tv_mensajeRecibido = view.findViewById(R.id.tv_mensajeRecibido);
        if(getArguments()!=null) {
            recibido = getArguments().getString("texto");
            tv_mensajeRecibido.setText(recibido);
        }
        //______________________________________________
        //                                              |
        // fin de codigo para poder recibir datos desde fragmentomensaje
        //
        //______________________________________________|



        //______________________________________________
        //                                              |
        // ACCESO A SQLITE
        //
        //______________________________________________|
/*
        String nombre = recibido;

        if(dbh == null){
            dbh = new DatabaseHelper(getActivity());
        }

        //dbh = new DataBaseHelper(getActivity());
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM registros WHERE TRIM(numero) = '"+nombre.trim()+"'", null);
        if(c.moveToFirst()){
            String cod = c.getString(0);
            String nom = c.getString(1);
            String pre = c.getString(2);
            //resultado.setText(cod+" | "+ nom+" | "+pre);
        }else{
            //Toast.makeText(getBaseContext(),"Producto no encontrado",Toast.LENGTH_LONG).show();
        }
        dbh.close();
*/
        //______________________________________________
        //                                              |
        // fin de codigo para poder recibir datos desde fragmentomensaje
        //
        //______________________________________________|



        return view;
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
