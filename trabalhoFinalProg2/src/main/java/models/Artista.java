package models;

import java.util.ArrayList;

public class Artista {
    private final String nome;
    private ArrayList<Album> albuns;

    public Artista(String nome) {
        this.nome = nome;
        this.albuns = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Album> getAlbuns() {
        return this.albuns;
    }

    public void adicionarAlbum(Album album) {
        this.albuns.add(album);
    }

    public void exibirAlbuns() {
        System.out.println("Exibindo álbuns do artista: " + this.nome);
        for (Album album : this.albuns) {
            System.out.println(album.getNome());
        }
    }
}


