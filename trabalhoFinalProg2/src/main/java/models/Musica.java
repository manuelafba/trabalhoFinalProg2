package models;
import enums.Generos;
import interfaces.Reprodutivel;

public class Musica implements Reprodutivel {
    private final String nome;
    private final String artista;
    private final String album;
    private final Generos generoMusical;
    private final String diretorio;
    private int duracaoSegundos;

    public Musica(String nome, String artista, String album, Generos generoMusical, int duracaoSegundos, String diretorio) {
        this.nome = nome;
        this.artista = artista;
        this.album = album;
        this.generoMusical = generoMusical;
        this.duracaoSegundos = duracaoSegundos;
        this.diretorio = diretorio;
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

    @Override
    public void reproduzir() {
        System.out.println("Tocando: " + this.nome + " - " + this.artista + " - " + this.album + " (" + this.duracaoSegundos + ")");
    }

    @Override
    public void pausar() {
        System.out.println("Pausado: " + this.nome);
    }

    @Override
    public void avancar() {}

    @Override
    public void voltar() {}
}
