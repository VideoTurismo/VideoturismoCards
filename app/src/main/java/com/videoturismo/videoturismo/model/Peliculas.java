package com.videoturismo.videoturismo.model;

/**
 * Created by CASCENCIO on 12/04/2017.
 */

public class Peliculas {

    private String titulo;
    private String rutaPelicula;
    private String rutaPortada;
    private String sinopsis;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRutaPelicula() {
        return rutaPelicula;
    }

    public void setRutaPelicula(String rutaPelicula) {
        this.rutaPelicula = rutaPelicula;
    }

    public String getRutaPortada() {
        return rutaPortada;
    }

    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }

    public String getSinopsis() {
        if (sinopsis.isEmpty()) {
            setSinopsis("algo");
            return sinopsis;
        }
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
