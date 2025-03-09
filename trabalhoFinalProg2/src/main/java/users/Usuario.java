package users;

import models.Playlist;
import models.Musica;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private final String nome;
    private ArrayList<Playlist> playlists;
    private boolean podeAvancar;
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

    public void setPodeAvancar(Boolean podeAvancar) {
        this.podeAvancar = podeAvancar;
    }

    public boolean getPodeAvancar() {
        return this.podeAvancar;
    }

    public ArrayList<Musica> getHistoricoMusicasEscutadas() {
        return this.historicoMusicasEscutadas;
    }

    public void criarPlaylist(String nomePlaylist) {
        if (nomePlaylist == null) {
            throw new IllegalArgumentException("O nome da playlist não pode ser vazio");
        } else {
            Playlist playlist = new Playlist(nomePlaylist);
            this.playlists.add(playlist);
            System.out.println("Playlist " +nomePlaylist + " criada com sucesso!");
        }
    }

    public void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist foi encontrada.");
        } else {
            System.out.println("Playlists de " + this.nome + ":");
            for (int i = 0; i <= playlists.size() - 1; i++) {
                System.out.println(i+ " - " + this.playlists.get(i).getNome());
            }
        }
    }

    public void adicionarMusicaHistorico(Musica musica) {
        this.historicoMusicasEscutadas.add(musica);
    }

    public abstract String getTipoUsuario();
}
