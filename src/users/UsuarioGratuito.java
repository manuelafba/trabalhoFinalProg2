package users;

public class UsuarioGratuito extends Usuario {
    private static final int LIMITE_PLAYLISTS = 3;

    public UsuarioGratuito(String nome) {
        super(nome);
        this.podeAvancar = false;
    }

    @Override
    public void criarPlaylist(String nomePlaylist) {
        if (playlists.size() >= LIMITE_PLAYLISTS) {
            throw new IllegalStateException("Limite de playlists atingido!");
        } else {
            super.criarPlaylist(nomePlaylist);
        }
    }

    @Override
    public void listarPlaylists() {
        if (playlists.size() >= LIMITE_PLAYLISTS) {
            throw new IllegalStateException("Limite de playlists atingido!");
        } else {
            super.listarPlaylists();
        }
    }
}
