package com.example.practica03gonzalojuanluis;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practica03gonzalojuanluis.Utils.MyRecyclerViewAdapter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoLlamadas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentoLlamadas extends Fragment {

    private OnFragmentInteractionListener mListener;

    //*******DEclaracion de variables globales
    //private ListView lstLlamadas;
    //**probar

    private String[] elementosLista = null;
    private Map<String, ?> elementosGuardados = null;
    private Map<String,?> elementosNoDuplicados = null;

    private RecyclerView miRecyclerView = null;
    private MyRecyclerViewAdapter myRecyclerViewAdapter = null;

    public FragmentoLlamadas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_llamadas,container,false);


        //***Creacion de lista
        //lstLlamadas = view.findViewById(R.id.lstLlamadas);

        //*************Recepcion de datos en shared preferences***********
        SharedPreferences datos = getActivity().getSharedPreferences("DatosDeReceptor",Context.MODE_PRIVATE);
        elementosGuardados = datos.getAll();
        elementosNoDuplicados = datos.getAll();


        Collection<Object> list = (Collection<Object>) elementosNoDuplicados.values(); //se remueven los duplicados de la lista
        for(Iterator<Object> itr = list.iterator(); itr.hasNext();)
        {
            if(Collections.frequency(list, itr.next())>1)
            {
                itr.remove();
            }
        }

        Map <String,Integer> items = new HashMap<>();


        for (Map.Entry<String,?> entry : elementosNoDuplicados.entrySet()){
            items.put(entry.getValue().toString(),0);
        }

        for (Map.Entry<String,?> entry : elementosGuardados.entrySet()){               // for elementos repetidos
            if (elementosNoDuplicados.containsValue(entry.getValue().toString())){       // if si encuentra en no repetidos
                int count = 0;
                for (Map.Entry<String,Integer> itemEntry : items.entrySet()){
                    if (entry.getValue().toString().equals(itemEntry.getKey())) count = itemEntry.getValue()+1;
                }
                items.put(entry.getValue().toString(),count);

            }
        }

        //elementosLista = (String[])elementosGuardados.values().toArray(new String[elementosGuardados.size()]);
        //String[] elementosLista2 = (String[])items.keySet().toArray(new String[items.size()]);
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,elementosLista2);
        //lstLlamadas.setAdapter(adaptador);

        miRecyclerView = view.findViewById(R.id.lstLlamadas);

        miRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getContext(),items);
        //myRecyclerViewAdapter.setClickListener();
        miRecyclerView.setAdapter(myRecyclerViewAdapter);



        setHasOptionsMenu(true);

        //**Registrar la lista para el context menu
        //registerForContextMenu(lstLlamadas);



        return view;
    }


    /*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);

        return true;
    }

    @Override
    public boolean OnCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu) ;
        final MenuItem item = menu.findItem(R.id.forecastID);
    }

    */







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
