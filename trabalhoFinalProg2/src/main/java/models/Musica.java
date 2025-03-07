package models;
import enums.Generos;

public class Musica {
    private final String nome;
    private final String artista;
    private final String album;
    private final Generos generoMusical;
    private final String diretorio;
    private final int duracaoSegundos;

    public Musica(String nome, String artista, String album, Generos generoMusical, String diretorio, int duracaoSegundos) {
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

    public String getArtista() {
        return this.artista;
    }

    public String getAlbum() {
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
                " - Artista: " + this.artista +
                " - Álbum: " + this.album +
                " - Gênero: " + this.generoMusical +
                " - Duração: " + this.duracaoSegundos + "s";
    }
}
