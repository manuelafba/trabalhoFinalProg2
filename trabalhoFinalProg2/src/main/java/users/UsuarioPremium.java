package users;

public class UsuarioPremium extends Usuario {

    public UsuarioPremium(String nome) {
        super(nome);
        super.setPodeAvancar(true);
    }

    @Override
    public void criarPlaylist(String nomePlaylist) {
        super.criarPlaylist(nomePlaylist);
    }

    @Override
    public void listarPlaylists() {
        super.listarPlaylists();
    }

    @Override
    public String getTipoUsuario() {
        return "Premium";
    }
}
