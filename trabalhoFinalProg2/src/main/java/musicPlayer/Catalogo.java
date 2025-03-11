package musicPlayer;

import enums.Generos;
import models.Musica;
import models.Artista;
import models.Album;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    // Instância única da classe
    private static Catalogo instance;

    // Lista de músicas
    private ArrayList<Musica> musicas;

    // Construtor privado para evitar instanciação externa
    private Catalogo() {
        this.musicas = new ArrayList<>();
    }

    // Metodo estático para acessar a instância única
    public static Catalogo getInstance() {
        if (instance == null) {
            instance = new Catalogo();
        }
        return instance;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    // Métodos da classe
    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }

    // Metodos sobrecarregados para realizar a busca de músicas por diversos critérios diferentes
    public ArrayList<Musica> buscarMusica(Generos genero) {
        ArrayList<Musica> musicasFiltradas = new ArrayList<>();
        for (Musica musica : this.musicas) {
            if (musica.getGeneroMusical() == genero) {
                musicasFiltradas.add(musica);
            }
        }
        return musicasFiltradas;
    }

    public ArrayList<Musica> buscarMusica(Artista artista) {
        ArrayList<Musica> musicasFiltradas = new ArrayList<>();
        for (Musica musica : this.musicas) {
            if (musica.getArtista().equals(artista)) {
                musicasFiltradas.add(musica);
            }
        }
        return musicasFiltradas;
    }

    public ArrayList<Musica> buscarMusica(Album album) {
        ArrayList<Musica> musicasFiltradas = new ArrayList<>();
        for (Musica musica : this.musicas) {
            if (musica.getAlbum().equals(album)) {
                musicasFiltradas.add(musica);
            }
        }
        return musicasFiltradas;
    }

    public ArrayList<Musica> buscarMusica(String nome) {
        ArrayList<Musica> musicasFiltradas = new ArrayList<>();
        for (Musica musica : this.musicas) {
            if (musica.getNome().equalsIgnoreCase(nome)) { // Verifica se são iguais sem case sensitive
                musicasFiltradas.add(musica);
            }
        }
        return musicasFiltradas;
    }

    public void exibirCatalogo() {
        System.out.println("---- Catálogo de músicas disponíveis ----");
        for (Musica musica : this.musicas) {
            System.out.println(musica.toString());
        }
    }
}