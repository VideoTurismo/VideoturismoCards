package com.videoturismo.videoturismo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv = (RecyclerView)findViewById(R.id.recycler_view_peliculas);
                rv.setHasFixedSize(true);
                LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);

                rv.setLayoutManager(mLinearLayoutManager);
                mPeliculasAdapter = new PeliculasAdapter(MainActivity.this);
                rv.setAdapter(mPeliculasAdapter);

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
                call = VideoTurismoAdapter.getApiService().getpeliculasSeries();
                call.enqueue(MainActivity.this);


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

    }
}
