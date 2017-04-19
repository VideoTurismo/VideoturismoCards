package com.videoturismo.videoturismo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.videoturismo.videoturismo.R;
import com.videoturismo.videoturismo.adapter.PeliculasAdapter;
import com.videoturismo.videoturismo.io.VideoTurismoAdapter;
import com.videoturismo.videoturismo.model.Peliculas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerosActivity extends AppCompatActivity implements Callback<ArrayList<Peliculas>> {
    private Call<ArrayList<Peliculas>> call;
    private RecyclerView rv;
    private PeliculasAdapter mPeliculasAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_de_generos);
        String  generoPeli = getIntent().getStringExtra("Genero");
        rv = (RecyclerView)findViewById(R.id.recycler_view_peliculas);
        rv.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(GenerosActivity.this);

        rv.setLayoutManager(mLinearLayoutManager);
        mPeliculasAdapter = new PeliculasAdapter(GenerosActivity.this);
        rv.setAdapter(mPeliculasAdapter);


        switch (generoPeli) {
            case "Accion":
                call = VideoTurismoAdapter.getApiService().getpeliculasAccion();
                call.enqueue(GenerosActivity.this);
                break;
            case "Comedia":
                call = VideoTurismoAdapter.getApiService().getpeliculasComedia();
                call.enqueue(GenerosActivity.this);
                break;
            case "Drama":
                call = VideoTurismoAdapter.getApiService().getpeliculasDrama();
                call.enqueue(GenerosActivity.this);
                break;
            case "Documentales":
                call = VideoTurismoAdapter.getApiService().getpeliculasSeries();
                call.enqueue(GenerosActivity.this);
                break;
            case "Familiar":
                call = VideoTurismoAdapter.getApiService().getpeliculasFamiliar();
                call.enqueue(GenerosActivity.this);
                break;
            case "Estrenos":
                call = VideoTurismoAdapter.getApiService().getpeliculasEstrenos();
                call.enqueue(GenerosActivity.this);
                break;
            case "Romanticas":
                call = VideoTurismoAdapter.getApiService().getpeliculasRomanticas();
                call.enqueue(GenerosActivity.this);
                break;
            case "Infantiles":
                call = VideoTurismoAdapter.getApiService().getpeliculasInfantil();
                call.enqueue(GenerosActivity.this);
                break;
           default:
                break;

        }
    }

    @Override
    public void onResponse(Call<ArrayList<Peliculas>> call, Response<ArrayList<Peliculas>> response) {
        if(response.isSuccessful()){
            ArrayList<Peliculas> pelis = response.body();

            mPeliculasAdapter.setDataSet(pelis);
            Log.d("OnResponse peliculas", "Size of peliculas ="+ pelis.size());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Peliculas>> call, Throwable t) {
        Toast.makeText(this, "Error en la red ", Toast.LENGTH_SHORT).show();

        finish();
    }
}
