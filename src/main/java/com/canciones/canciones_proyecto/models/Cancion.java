package com.canciones.canciones_proyecto.models;

public class Cancion {
    private int idCancion;
    private String titulo;
    private String artista;
    private String album;
    private String genero;
    private int anioLanzamiento;
    private int usuarioId;
    private String usuarioCorreo;

    public Cancion(int idCancion, String titulo, String artista, String album, String genero, int anioLanzamiento, int usuarioId) {
        this.idCancion = idCancion;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.anioLanzamiento = anioLanzamiento;
        this.usuarioId = usuarioId;
    }

    public Cancion() {
        this.titulo = "";
        this.artista = "";
        this.album = "";
        this.genero = "";
    }

    public int getIdCancion() {
        return idCancion;
    }
    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }
    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }
    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }
}
