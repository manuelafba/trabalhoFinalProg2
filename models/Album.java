package models;

import enumsInterfaces.Generos;

import java.util.ArrayList;

public class Album {
    public String nome;
    public String artista;
    public int duracaoSegundos;
    public Generos generoMusical;
    public ArrayList<Musica> musicas;

    public Album(String nome, String artista, int duracaoSegundos, Generos generoMusical) {
        this.nome = nome;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.generoMusical = generoMusical;
        this.musicas = new ArrayList<>();
    }
}
