package users;

import models.Playlist;

public class UsuarioGratuito extends Usuario {
    private static final int LIMITE_PLAYLISTS = 3;

    public UsuarioGratuito(String nome) {
        super(nome);
    }

    @Override
    public void criarPlaylist(String nomePlaylist) {
        if (super.getPlaylists().size() >= LIMITE_PLAYLISTS) {
            throw new IllegalStateException("Limite de playlists atingido!");
        } else {
            if (nomePlaylist == null) {
                throw new IllegalArgumentException("O nome da playlist não pode ser vazio");
            } else{
                for (Playlist playlist : super.getPlaylists()) {
                    if (playlist.getNome().equals(nomePlaylist)) {
                        throw new IllegalArgumentException("Já existe uma playlist com o nome " + nomePlaylist);
                    }
                }
                Playlist playlist = new Playlist(nomePlaylist);
                super.getPlaylists().add(playlist);
                System.out.println("Playlist " +nomePlaylist + " criada com sucesso!");
            }
        }
    }

    @Override
    public void exibirPlaylists() {
        if (super.getPlaylists().size() >= LIMITE_PLAYLISTS) {
            throw new IllegalStateException("Limite de playlists atingido!");
        } else {
            if (super.getPlaylists().isEmpty()) {
                System.out.println("Nenhuma playlist foi encontrada.");
            } else {
                System.out.println("---- Playlists de " + super.getNome() + " ----");
                for (int i = 0; i <= super.getPlaylists().size() - 1; i++) {
                    System.out.println("- " + super.getPlaylists().get(i).getNome());
                }
            }
        }
    }

    @Override
    public String getTipoUsuario() {
        return "Gratuito";
    }
}
