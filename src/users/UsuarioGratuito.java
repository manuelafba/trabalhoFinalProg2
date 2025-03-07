package users;

public class UsuarioGratuito extends Usuario {
    private static final int LIMITE_PLAYLISTS = 3;

    public UsuarioGratuito(String nome) {
        super(nome);
    }

    @Override
    public void criarPlaylist(String nomePlaylist) {
        if (playlists.size() >= LIMITE_PLAYLISTS) {
            throw new IllegalStateException("Limite de playlists atingido!");
        } else {
            super.criarPlaylist(nomePlaylist);
        }
    }
}
