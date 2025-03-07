package models;

import java.util.ArrayList;

public class Artista {
    private String nome;
    private ArrayList<Album> albuns;

    public Artista(String nome) {
        this.nome = nome;
        this.albuns = new ArrayList<>();
    }
}
