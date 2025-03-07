package musicPlayer;

import enums.Generos;
import models.Album;
import models.Artista;
import models.Musica;

import java.util.ArrayList;
import java.util.Objects;

public class Catalogo {
    private ArrayList<Musica> musicas;

    public Catalogo() {
        this.musicas = new ArrayList<Musica>();
    }

    public ArrayList<Musica> getMusicas() {
        return this.musicas;
    }

    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public ArrayList<Musica> buscarMusica(String nome){
        ArrayList<Musica> musicasFiltradas = new ArrayList<Musica>();
        for (Musica musica : this.musicas) {
            if (Objects.equals(musica.getNome(), nome)){
                musicasFiltradas.add(musica);
            }
        }
        return musicasFiltradas;
    }

    public ArrayList<Musica> buscarMusica(Generos genero){
        ArrayList<Musica> musicasFiltradas = new ArrayList<Musica>();
        for (Musica musica : this.musicas) {
            if (musica.getGeneroMusical() == genero){
                musicasFiltradas.add(musica);
            }
        }
        return musicasFiltradas;
    }

    public ArrayList<Musica> buscarMusica(Artista artista){
        ArrayList<Musica> musicasFiltradas = new ArrayList<Musica>();
        for (Musica musica : this.musicas) {
            if (Objects.equals(musica.getArtista(), artista.getNome())){
                musicasFiltradas.add(musica);
            }
        }
        return musicasFiltradas;
    }

    public ArrayList<Musica> buscarMusica(Album album){
        ArrayList<Musica> musicasFiltradas = new ArrayList<Musica>();
        for (Musica musica : this.musicas) {
            if (Objects.equals(musica.getAlbum(), album.getNome())){
                musicasFiltradas.add(musica);
            }
        }
        return musicasFiltradas;
    }



}
