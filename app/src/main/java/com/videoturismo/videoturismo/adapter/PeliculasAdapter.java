package com.videoturismo.videoturismo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.videoturismo.videoturismo.R;
import com.videoturismo.videoturismo.model.Peliculas;

import java.util.ArrayList;

/**
 * Created by CASCENCIO on 12/04/2017.
 */

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.ViewHolder> {
    private ArrayList<Peliculas> mDataSet;
    private Context context;

    // Obtener referencias de los componentes visuales para cada elemento
    // Es decir, referencias de los EditText, TextViews, Buttons
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // en este ejemplo cada elemento consta solo de un título
        public TextView textViewtitulo;
        public ImageView portadaView;
        public VideoView videoViewPelicula;



        public ViewHolder(View v) {
            super(v);
            textViewtitulo = (TextView) v.findViewById(R.id.tvtitulo);
            portadaView  = (ImageView) v.findViewById(R.id.ivportada);
            videoViewPelicula = (VideoView) v.findViewById(R.id.vvpelicula);

        }
    }

    // Este es nuestro constructor (puede variar según lo que queremos mostrar)
    public PeliculasAdapter(Context context) {
        this.context = context;
        mDataSet = new ArrayList<>();
    }

    public void setDataSet(ArrayList<Peliculas> dataSet){
        mDataSet= dataSet;
        notifyDataSetChanged();
    }


    // El layout manager invoca este método
    // para renderizar cada elemento del RecyclerView
    @Override
    public PeliculasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // Creamos una nueva vista
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.peliculas_view, parent, false);

        // Aquí podemos definir tamaños, márgenes, paddings
        // ...

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Este método reemplaza el contenido de cada view,
    // para cada elemento de la lista (nótese el argumento position)
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        // - obtenemos un elemento del dataset según su posición
        // - reemplazamos el contenido de los views según tales datos

        Peliculas pelis = mDataSet.get(i);


        holder.textViewtitulo.setText(pelis.getTitulo());
        Picasso.with(context)
                .load("http://192.168.5.161/streaming"+pelis.getRutaPortada().replaceAll(" ","%20"))
                .fit()
                .into(holder.portadaView);
        holder.videoViewPelicula.setVideoPath("http://192.168.5.161/streaming"+pelis.getRutaPelicula().replaceAll(" ","%20"));
        holder.videoViewPelicula.setMediaController(new MediaController(context));
//        holder.videoViewPelicula.start();

    }

    // Método que define la cantidad de elementos del RecyclerView
    // Puede ser más complejo en RecyclerViews que implementar filtros o búsquedas
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}