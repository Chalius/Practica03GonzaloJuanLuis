package com.example.practica03gonzalojuanluis.Utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practica03gonzalojuanluis.R;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

	private Context context;
	private Map<String,Integer> mData;
	private List<String> numeros;
	private List<Integer> veces;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	// data is passed into the constructor
	public MyRecyclerViewAdapter(Context context, Map<String,Integer> data) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.mData = data;
		this.numeros = Arrays.asList((String[]) data.keySet().toArray(new String[data.size()]));
		this.veces = Arrays.asList((Integer[]) data.values().toArray(new Integer[data.size()]));
	}

	// inflates the row layout from xml when needed
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = mInflater.inflate(R.layout.layout_item_llamada, parent, false);
		return new ViewHolder(view);
	}

	// binds the data to the TextView in each row
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.tituloLlamada.setText(numeros.get(position));
		holder.veces.setText(String.valueOf(veces.get(position)));
		final String numero = holder.tituloLlamada.getText().toString();

		holder.itemView.findViewById(R.id.item_llamada_llamar).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));
				if (context.checkSelfPermission(Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED) context.startActivity(intentLlamada);
				else {
					Toast.makeText(context, "La app no tiene permisos de hacer llamadas", Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	// total number of rows
	@Override
	public int getItemCount() {
		return mData.size();
	}


	// stores and recycles views as they are scrolled off screen
	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView tituloLlamada;
		TextView veces;

		ViewHolder(View itemView) {
			super(itemView);
			tituloLlamada = itemView.findViewById(R.id.item_llamada_titulo);
			veces = itemView.findViewById(R.id.item_llamada_veces);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
		}
	}

	// convenience method for getting data at click position
	/*
	String getItem(int id) {
		return mData.get(id);
	}
	*/

	// allows clicks events to be caught
	public void setClickListener(ItemClickListener itemClickListener) {
		this.mClickListener = itemClickListener;
	}

	// parent activity will implement this method to respond to click events
	public interface ItemClickListener {
		void onItemClick(View view, int position);
	}
}
