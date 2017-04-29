package com.videoturismo.videoturismo.io;

import com.videoturismo.videoturismo.model.Musica;
import com.videoturismo.videoturismo.model.Peliculas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by CASCENCIO on 12/04/2017.
 */

public interface VideoTurismoService {

    @GET("WebServices/obtenerPelicula.php?nombreGenero=Estrenos")
    Call<ArrayList<Peliculas>> getpeliculasEstrenos();

    @GET("WebServices/obtenerPelicula.php?nombreGenero=Series")
    Call<ArrayList<Peliculas>> getpeliculasSeries();

    @GET("WebServices/obtenerPelicula.php?nombreGenero=Romantica")
    Call<ArrayList<Peliculas>> getpeliculasRomanticas();

    @GET("WebServices/obtenerPelicula.php?nombreGenero=Accion")
    Call<ArrayList<Peliculas>> getpeliculasAccion();

    @GET("WebServices/obtenerPelicula.php?nombreGenero=Drama")
    Call<ArrayList<Peliculas>> getpeliculasDrama();

    @GET("WebServices/obtenerPelicula.php?nombreGenero=Infantil")
    Call<ArrayList<Peliculas>> getpeliculasInfantil();

    @GET("WebServices/obtenerPelicula.php?nombreGenero=Comedia")
    Call<ArrayList<Peliculas>> getpeliculasComedia();

    @GET("WebServices/obtenerPelicula.php?nombreGenero=Familiar")
    Call<ArrayList<Peliculas>> getpeliculasFamiliar();

    @GET("WebServices/obtenerMusica.php?nombreGeneroMusica=Pop")
    Call<ArrayList<Musica>> getMusicaPop();

    @GET("WebServices/obtenerMusica.php?nombreGeneroMusica=Rock")
    Call<ArrayList<Musica>> getMusicaRock();

    @GET("WebServices/obtenerMusica.php?nombreGeneroMusica=Clasicas")
    Call<ArrayList<Musica>> getMusicaClasica();

    @GET("WebServices/obtenerMusica.php?nombreGeneroMusica=Regional")
    Call<ArrayList<Musica>> getMusicaRegional();


}
