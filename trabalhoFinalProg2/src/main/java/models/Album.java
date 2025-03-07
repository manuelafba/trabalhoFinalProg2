package models;

import enums.Generos;

public class Album {
    private final String nome;
    private final String artista;
    private final Generos generoMusical;
    private final Musica[] musicas;
    private int duracaoSegundos; // precisa de duração no álbum?
    private int quantidadeMusicas = 0;

    public Album(String nome, String artista, int duracaoSegundos, Generos generoMusical, int tamanhoAlbum) {
        this.nome = nome;
        this.artista = artista;
        this.generoMusical = generoMusical;
        this.musicas = new Musica[tamanhoAlbum];
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getNome() {
        return this.nome;
    }

    public String getArtista() {
        return this.artista;
    }

    public int getDuracaoSegundos() {
        return this.duracaoSegundos;
    }

    public Generos getGeneroMusical() {
        return this.generoMusical;
    }

    public Musica[] getMusicas() {
        return this.musicas;
    }

    public void adicionarMusica(Musica musica) {
        if (this.quantidadeMusicas < this.musicas.length) {
            this.musicas[quantidadeMusicas] = musica;
            this.quantidadeMusicas++;
        } else {
            throw new IllegalStateException("Álbum já está cheio");
        }
    }

    public void exibirAlbum() {
        System.out.println("Exibindo álbum: " + this.nome);
        for (Musica musica : this.musicas) {
            System.out.println(musica.toString());
        }
    }
}
