package users;

public class UsuarioGratuito extends Usuario {
    private static final int LIMITE_PLAYLISTS = 3;

    public UsuarioGratuito(String nome) {
        super(nome);
        super.setPodeAvancar(false);
    }

    @Override
    public void criarPlaylist(String nomePlaylist) {
        if (super.getPlaylists().size() >= LIMITE_PLAYLISTS) {
            throw new IllegalStateException("Limite de playlists atingido!");
        } else {
            super.criarPlaylist(nomePlaylist);
        }
    }

    @Override
    public void listarPlaylists() {
        if (super.getPlaylists().size() >= LIMITE_PLAYLISTS) {
            throw new IllegalStateException("Limite de playlists atingido!");
        } else {
            super.listarPlaylists();
        }
    }

    @Override
    public String getTipoUsuario() {
        return "Gratuito";
    }
}
