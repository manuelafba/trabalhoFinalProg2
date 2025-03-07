package models;

public class Album {
    private final String nome;
    private final String artista;
    private final Musica[] musicas;
    private int quantidadeMusicas = 0;

    public Album(String nome, String artista, int tamanhoAlbum) {
        this.nome = nome;
        this.artista = artista;
        this.musicas = new Musica[tamanhoAlbum];
    }

    public String getNome() {
        return this.nome;
    }

    public String getArtista() {
        return this.artista;
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
        for (int i = 0; i < quantidadeMusicas; i++) {
            System.out.println(musicas[i].toString());
        }
    }
}
