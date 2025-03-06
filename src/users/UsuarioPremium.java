package users;

public class UsuarioPremium extends Usuario {

    public UsuarioPremium(String nome) {
        super(nome);
        this.podeAvancar = true;
    }

    @Override
    public void criarPlaylist(String nomePlaylist) {
        super.criarPlaylist(nomePlaylist);
    }

    @Override
    public void listarPlaylists() {
        super.listarPlaylists();
    }
}
