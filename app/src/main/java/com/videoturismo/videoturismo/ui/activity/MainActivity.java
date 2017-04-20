package com.videoturismo.videoturismo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.videoturismo.videoturismo.R;
import com.videoturismo.videoturismo.adapter.PeliculasAdapter;
import com.videoturismo.videoturismo.io.VideoTurismoAdapter;
import com.videoturismo.videoturismo.model.Peliculas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Callback<ArrayList<Peliculas>> {
    private Call<ArrayList<Peliculas>> call;
    private RecyclerView rv;
    private PeliculasAdapter mPeliculasAdapter;
    private ImageView accion;
    private ImageView comedia;
    private ImageView drama;
    private ImageView documentales;
    private ImageView familiar;
    private ImageView estrenos;
    private ImageView romaticas;
    private ImageView infantil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        rv = (RecyclerView)findViewById(R.id.recycler_view_peliculas);
        rv.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(mLinearLayoutManager);
        mPeliculasAdapter = new PeliculasAdapter(this);
        rv.setAdapter(mPeliculasAdapter);


        call = VideoTurismoAdapter.getApiService().getpeliculasEstrenos();
        call.enqueue(this);
//        accion =(ImageView) findViewById(R.id.accion);
//        comedia = (ImageView) findViewById(R.id.comedia);
//        drama =(ImageView) findViewById(R.id.drama);
//        documentales = (ImageView) findViewById(R.id.docs);
//        familiar =(ImageView) findViewById(R.id.fam);
//        estrenos = (ImageView) findViewById(R.id.estrenos);
//        romaticas =(ImageView) findViewById(R.id.romaticas);
//        infantil = (ImageView) findViewById(R.id.infantiles);
//
//
//
//
//
//
//        accion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
//                i.putExtra("Genero","Accion");
//                startActivity(i);
//            }
//        });
//        comedia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
//                i.putExtra("Genero","Comedia");
//                startActivity(i);
//            }
//        });
//        drama.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
//                i.putExtra("Genero","Drama");
//                startActivity(i);
//            }
//        });
//        documentales.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
//                i.putExtra("Genero","Documentales");
//                startActivity(i);
//            }
//        });
//        familiar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
//                i.putExtra("Genero","Familiar");
//                startActivity(i);
//            }
//        });
//        estrenos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
//                i.putExtra("Genero","Estrenos");
//                startActivity(i);
//            }
//        });
//        romaticas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
//                i.putExtra("Genero","Romanticas");
//                startActivity(i);
//            }
//        });
//        infantil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
//                i.putExtra("Genero","Infantiles");
//                startActivity(i);
//            }
//        });






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_accion) {
            call = VideoTurismoAdapter.getApiService().getpeliculasAccion();
            call.enqueue(MainActivity.this);
        } else if (id == R.id.nav_drama) {
            call = VideoTurismoAdapter.getApiService().getpeliculasDrama();
            call.enqueue(MainActivity.this);

        } else if (id == R.id.nav_documental) {
            call = VideoTurismoAdapter.getApiService().getpeliculasSeries();
            call.enqueue(MainActivity.this);

        } else if (id == R.id.nav_estrenos) {
            call = VideoTurismoAdapter.getApiService().getpeliculasEstrenos();
            call.enqueue(MainActivity.this);

        } else if (id == R.id.nav_infantiles) {
            call = VideoTurismoAdapter.getApiService().getpeliculasInfantil();
            call.enqueue(MainActivity.this);

        } else if (id == R.id.nav_romanticas) {
            call = VideoTurismoAdapter.getApiService().getpeliculasRomanticas();
            call.enqueue(MainActivity.this);

        }else if (id == R.id.nav_comedia) {
            call = VideoTurismoAdapter.getApiService().getpeliculasComedia();
            call.enqueue(MainActivity.this);

        }else if (id == R.id.nav_familiar) {
            call = VideoTurismoAdapter.getApiService().getpeliculasFamiliar();
            call.enqueue(MainActivity.this);

        }else if (id == R.id.nav_pop) {

        }else if (id == R.id.nav_rock) {

        }else if (id == R.id.nav_regionales) {

        }else if (id == R.id.nav_clasicas) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
