package users;

import models.Playlist;
import models.Musica;

import java.util.ArrayList;

public abstract class Usuario {
    private final String nome;
    private ArrayList<Playlist> playlists;
    private ArrayList<Musica> historicoMusicasEscutadas;

    public Usuario(String nome) {
        this.nome = nome;
        this.playlists = new ArrayList<Playlist>();
        this.historicoMusicasEscutadas = new ArrayList<Musica>();
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Playlist> getPlaylists() {
        return this.playlists;
    }

    public ArrayList<Musica> getHistoricoMusicasEscutadas() {
        return this.historicoMusicasEscutadas;
    }

    public Playlist pesquisarPlaylist(String nomePlaylist) {
        for (Playlist playlist : this.playlists) {
            if (playlist.getNome().equalsIgnoreCase(nomePlaylist)) {
                return playlist;
            }
        }
        return null;
    }
    public void adicionarMusicaHistorico(Musica musica) {
        this.historicoMusicasEscutadas.add(musica);
    }

    public abstract void criarPlaylist(String nomePlaylist);

    public abstract void exibirPlaylists();

    public abstract String getTipoUsuario();
}
