package com.videoturismo.videoturismo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.videoturismo.videoturismo.R;
import com.videoturismo.videoturismo.model.Musica;

import java.util.ArrayList;

/**
 * Created by JFCC on 25/04/2017.
 */

public class MusicaAdapter extends RecyclerView.Adapter<MusicaAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Musica> mDataSet;
    final public ArrayList<String> pathCanciones = new ArrayList<>();
    private Context context;
    //final static MediaPlayer mp = new MediaPlayer();
    String tituloMain = "";
    private View.OnClickListener listener;


    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
    }

    }


    // Obtener referencias de los componentes visuales para cada elemento
    // Es decir, referencias de los EditText, TextViews, Buttons
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // en este ejemplo cada elemento consta solo de un título
        public TextView tvtitulo;
        public TextView tvArtista;
        public TextView tvAlbum;

        public ViewHolder(View v) {
            super(v);
            tvtitulo = (TextView) v.findViewById(R.id.tituloCancion);
            tvArtista = (TextView)v.findViewById(R.id.artista);
            tvAlbum = (TextView) v.findViewById(R.id.album);
        }
    }
    // Este es nuestro constructor (puede variar según lo que queremos mostrar)
    public MusicaAdapter(Context context) {
        this.context = context;

        mDataSet = new ArrayList<>();
    }
    // se utiliza en el onresponse del activity q implementa para cargar los datos que llegaron
    public void setDataSet(ArrayList<Musica> dataSet) {
        mDataSet = dataSet;
        notifyDataSetChanged();
    }
    // El layout manager invoca este método
    // para renderizar cada elemento del RecyclerView
    @Override
    public MusicaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // Creamos una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.musica_view, parent, false);

        // Aquí podemos definir tamaños, márgenes, paddings
        // ...

        v.setOnClickListener(this);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Este método reemplaza el contenido de cada view,
    // para cada elemento de la lista (nótese el argumento position)
    @Override
    public void onBindViewHolder(final MusicaAdapter.ViewHolder holder, final int i) {
        // - obtenemos un elemento del dataset según su posición
        // - reemplazamos el contenido de los views según tales datos

        final Musica musica = mDataSet.get(i);


        holder.tvtitulo.setText(musica.getTitulo());
        holder.tvArtista.setText(musica.getArtista());
        holder.tvAlbum.setText(musica.getAlbum());

        pathCanciones.add("http://192.168.5.161/streaming"+musica.getRutaCancion());

    }

    // Método que define la cantidad de elementos del RecyclerView
    // Puede ser más complejo en RecyclerViews que implementar filtros o búsquedas
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public String getTitulo(){
        String t = tituloMain;

        return t;
    }
}
