package models;
import enumsInterfaces.Generos;
import enumsInterfaces.Reprodutivel;

public class Musica implements Reprodutivel {
    private String nome;
    private String artista;
    private String album;
    private Generos generoMusical;
    private int duracaoSegundos;

    public Musica(String nome, String artista, String album, Generos generoMusical, int duracaoSegundos) {
        this.nome = nome;
        this.artista = artista;
        this.album = album;
        this.generoMusical = generoMusical;
        this.duracaoSegundos = duracaoSegundos;
    }

    public String toString() {
        return "Música: " + this.nome + " - Artista: " + artista + " - Álbum: " + album + " - Gênero: " + generoMusical + " - Duração: " + this.duracaoSegundos + "s";
    }

    @Override
    public void reproduzir() {
        System.out.println("Tocando: " + this.nome + " - " + this.artista + " - " + this.album + " (" + this.duracaoSegundos + ")");
    }

    @Override
    public void pausar() {
        System.out.println("Pausado: " + this.nome);
    }
}
