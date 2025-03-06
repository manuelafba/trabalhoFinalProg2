package users;

import models.Playlist;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private String nome;
    protected boolean podeAvancar;
    protected List<Playlist> playlists;

    public Usuario(String nome) {
        this.nome = nome;
        this.playlists = new ArrayList<>();
    }

    public void criarPlaylist(String nomePlaylist) {
        if (nomePlaylist == null) {
            throw new IllegalArgumentException("O nome da playlist não pode ser vazio");
        } else {
            Playlist playlist = new Playlist(nomePlaylist);
            playlists.add(playlist);
            System.out.println("Playlist " +nomePlaylist + " criada com sucesso!");
        }
    }

    public void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist foi encontrada.");
        } else {
            System.out.println("Playlists de " + this.nome + ":");
            for (int i = 0; i <= playlists.size() - 1; i++) {
                System.out.println(+i+ " - " + playlists.get(i).getNome());
            }
        }
    }
}
