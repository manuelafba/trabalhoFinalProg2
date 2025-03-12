package models;
import enums.Generos;

public class Musica {
    private final String nome;
    private final Artista artista;
    private final Album album;
    private final Generos generoMusical;
    private final String diretorio;
    private final int duracaoSegundos;

    public Musica(String nome, Artista artista, Album album, Generos generoMusical, String diretorio, int duracaoSegundos) {
        this.nome = nome;
        this.artista = artista;
        this.album = album;
        this.generoMusical = generoMusical;
        this.diretorio = diretorio;
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getNome() {
        return this.nome;
    }

    public Artista getArtista() {
        return this.artista;
    }

    public Album getAlbum() {
        return this.album;
    }

    public Generos getGeneroMusical() {
        return this.generoMusical;
    }

    public String getDiretorio() {
        return this.diretorio;
    }

    public int getDuracaoSegundos() {
        return this.duracaoSegundos;
    }

    public String toString() {
        return "Música: " + this.nome +
                " - Artista: " + this.artista.getNome() +
                " - Álbum: " + this.album.getNome() +
                " - Gênero: " + this.generoMusical +
                " - Duração: " + this.duracaoSegundos + "s";
    }
}
