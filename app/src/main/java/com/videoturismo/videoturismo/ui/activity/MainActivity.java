package com.videoturismo.videoturismo.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
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
    private FloatingActionButton fab;
    private int iconImage;
    private int imagenGenero;
    private String nombreGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (!verificaConexion(this)) {
            Toast.makeText(getBaseContext(),
                    "Comprueba tu conexión a Internet. Saliendo ... ", Toast.LENGTH_LONG)
                    .show();
        }else {


            fab = (FloatingActionButton) findViewById(R.id.fab);
            rv = (RecyclerView) findViewById(R.id.recycler_view_peliculas);
            rv.setHasFixedSize(false);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);

            rv.setLayoutManager(mLinearLayoutManager);
            mPeliculasAdapter = new PeliculasAdapter(this);
            rv.setAdapter(mPeliculasAdapter);


            imagenGenero = R.drawable.estrenos;
            nombreGenero = "Estrenos";

            call = VideoTurismoAdapter.getApiService().getpeliculasEstrenos();
            call.enqueue(this);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message(nombreGenero, imagenGenero);

                }
            });

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                  this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void message(String genero, int resource){

        // con este tema personalizado evitamos los bordes por defecto
        Dialog customDialog = new Dialog(this,R.style.Theme_Dialog_Translucent);
        //deshabilitamos el título por defecto
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //obligamos al usuario a pulsar los botones para cerrarlo
        customDialog.setCancelable(true);
        //establecemos el contenido de nuestro dialog
        customDialog.setContentView(R.layout.message_custom);
        //asignamos textos e imagenes
        TextView tvGenero = (TextView) customDialog.findViewById(R.id.genero_tv);
        ImageView iconoGenero = (ImageView) customDialog.findViewById(R.id.icono_genero);
        tvGenero.setText(genero);
        iconoGenero.setImageResource(resource);
        customDialog.show();


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
            //usamos el click para poder cambiar el icono del fab
            iconImage = R.drawable.fabaction;
            imagenGenero = R.drawable.accion;
            fab.setImageResource(iconImage);
            nombreGenero ="Accón";

        } else if (id == R.id.nav_drama) {
            call = VideoTurismoAdapter.getApiService().getpeliculasDrama();
            call.enqueue(MainActivity.this);
            //usamos el click para poder cambiar el icono del fab
            iconImage = R.drawable.fabdrama;
            imagenGenero = R.drawable.drama;
            fab.setImageResource(iconImage);
            nombreGenero = "Drama";




        } else if (id == R.id.nav_documental) {
            call = VideoTurismoAdapter.getApiService().getpeliculasSeries();
            call.enqueue(MainActivity.this);
            //usamos el click para poder cambiar el icono del fab
            iconImage = R.drawable.fabdocumentales;
            imagenGenero = R.drawable.documental;
            fab.setImageResource(iconImage);
            nombreGenero = "Documentales";


        } else if (id == R.id.nav_estrenos) {
            call = VideoTurismoAdapter.getApiService().getpeliculasEstrenos();
            call.enqueue(MainActivity.this);
            //usamos el click para poder cambiar el icono del fab
            iconImage = R.drawable.fabestrenos;
            imagenGenero = R.drawable.estrenos;
            fab.setImageResource(iconImage);
            nombreGenero = "Estrenos";


        } else if (id == R.id.nav_infantiles) {
            call = VideoTurismoAdapter.getApiService().getpeliculasInfantil();
            call.enqueue(MainActivity.this);
            //usamos el click para poder cambiar el icono del fab
            iconImage = R.drawable.fabinfantil;
            imagenGenero = R.drawable.infantiles;
            fab.setImageResource(iconImage);
            nombreGenero = "Infantiles";


        } else if (id == R.id.nav_romanticas) {
            call = VideoTurismoAdapter.getApiService().getpeliculasRomanticas();
            call.enqueue(MainActivity.this);
            //usamos el click para poder cambiar el icono del fab
            iconImage = R.drawable.fabromatica;
            imagenGenero = R.drawable.romanticas;
            fab.setImageResource(iconImage);

            nombreGenero= "Romanticas";

        }else if (id == R.id.nav_comedia) {
            call = VideoTurismoAdapter.getApiService().getpeliculasComedia();
            call.enqueue(MainActivity.this);
            //usamos el click para poder cambiar el icono del fab
            iconImage = R.drawable.fabcomedia;
            imagenGenero = R.drawable.comedia;
            fab.setImageResource(iconImage);
            nombreGenero = "Comedia";


        }else if (id == R.id.nav_familiar) {
            call = VideoTurismoAdapter.getApiService().getpeliculasFamiliar();
            call.enqueue(MainActivity.this);
            //usamos el click para poder cambiar el icono del fab
            iconImage = R.drawable.fabfamiliar;
            imagenGenero = R.drawable.familiar;
            fab.setImageResource(iconImage);
            nombreGenero = "Familiar";


        }else if (id == R.id.nav_pop) {
            Intent i = new Intent(MainActivity.this, PlayListActivity.class);
            i.putExtra("Genero","Pop");
            startActivity(i);

        }else if (id == R.id.nav_rock) {
            Intent i = new Intent(MainActivity.this, PlayListActivity.class);
            i.putExtra("Genero","Rock");
            startActivity(i);

        }else if (id == R.id.nav_regionales) {
            Intent i = new Intent(MainActivity.this, PlayListActivity.class);
            i.putExtra("Genero","Regional");
            startActivity(i);

        }else if (id == R.id.nav_clasicas) {
            Intent i = new Intent(MainActivity.this, PlayListActivity.class);
            i.putExtra("Genero","Clasicas");
            startActivity(i);

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
        Toast.makeText(this, "Error Intente lo Más Tarde", Toast.LENGTH_SHORT).show();

    }

    public boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        String name = "";
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                name = redes[i].getExtraInfo();
            }
        }
        if(name.contains("Entreteni")){
            Toast.makeText(MainActivity.this,name
                    , Toast.LENGTH_SHORT)
                    .show();
            bConectado = true;
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
            alertDialog.setTitle("Error de conexión");
            alertDialog.setCancelable(false);
            alertDialog.setMessage(getResources().getString(R.string.mensage_wifi));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Abrir",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS),0);
                        }
                    });
            alertDialog.show();

        }
        return bConectado;
    }
}
