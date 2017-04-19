package com.videoturismo.videoturismo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.videoturismo.videoturismo.R;
import com.videoturismo.videoturismo.adapter.PeliculasAdapter;
import com.videoturismo.videoturismo.model.Peliculas;

import java.util.ArrayList;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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


        accion =(ImageView) findViewById(R.id.accion);
        comedia = (ImageView) findViewById(R.id.comedia);
        drama =(ImageView) findViewById(R.id.drama);
        documentales = (ImageView) findViewById(R.id.docs);
        familiar =(ImageView) findViewById(R.id.fam);
        estrenos = (ImageView) findViewById(R.id.estrenos);
        romaticas =(ImageView) findViewById(R.id.romaticas);
        infantil = (ImageView) findViewById(R.id.infantiles);






        accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
                i.putExtra("Genero","Accion");
                startActivity(i);
            }
        });
        comedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
                i.putExtra("Genero","Comedia");
                startActivity(i);
            }
        });
        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
                i.putExtra("Genero","Drama");
                startActivity(i);
            }
        });
        documentales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
                i.putExtra("Genero","Documentales");
                startActivity(i);
            }
        });
        familiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
                i.putExtra("Genero","Familiar");
                startActivity(i);
            }
        });
        estrenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
                i.putExtra("Genero","Estrenos");
                startActivity(i);
            }
        });
        romaticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
                i.putExtra("Genero","Romanticas");
                startActivity(i);
            }
        });
        infantil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerosActivity.class);
                i.putExtra("Genero","Infantiles");
                startActivity(i);
            }
        });




//        rv = (RecyclerView)findViewById(R.id.recycler_view_peliculas);
//        rv.setHasFixedSize(true);
//        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
//
//        rv.setLayoutManager(mLinearLayoutManager);
//        mPeliculasAdapter = new PeliculasAdapter(MainActivity.this);
//        rv.setAdapter(mPeliculasAdapter);
//
//                call = VideoTurismoAdapter.getApiService().getpeliculasEstrenos();
//                call.enqueue(MainActivity.this);
//                call = VideoTurismoAdapter.getApiService().getpeliculasAccion();
//                call.enqueue(MainActivity.this);
//                call = VideoTurismoAdapter.getApiService().getpeliculasComedia();
//                call.enqueue(MainActivity.this);
//                call = VideoTurismoAdapter.getApiService().getpeliculasDrama();
//                call.enqueue(MainActivity.this);
//                call = VideoTurismoAdapter.getApiService().getpeliculasFamiliar();
//                call.enqueue(MainActivity.this);
//                call = VideoTurismoAdapter.getApiService().getpeliculasInfantil();
//                call.enqueue(MainActivity.this);
//                call = VideoTurismoAdapter.getApiService().getpeliculasRomanticas();
//                call.enqueue(MainActivity.this);

//        call = VideoTurismoAdapter.getApiService().getpeliculasSeries();
//        call.enqueue(MainActivity.this);

//        rv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        //startActivity(new Intent(this, GenerosActivity.class));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,VideoActivity.class);
                i.putExtra
                        ("URL","http://192.168.5.161/streaming/Documentales/Dr. Jeff Rocky Mountain Vet-Ep-5- Mile-High Mutt.mp4".replaceAll(" ","%20"));

                startActivity(i);

            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
