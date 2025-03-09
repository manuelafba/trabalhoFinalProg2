package users;

import models.Playlist;

public class UsuarioPremium extends Usuario {

    public UsuarioPremium(String nome) {
        super(nome);
        super.setPodeAvancar(true);
    }

    @Override
    public void criarPlaylist(String nomePlaylist) {
        if (nomePlaylist == null) {
            throw new IllegalArgumentException("O nome da playlist não pode ser vazio");
        } else {
            Playlist playlist = new Playlist(nomePlaylist);
            super.getPlaylists().add(playlist);
            System.out.println("Playlist " +nomePlaylist + " criada com sucesso!");
        };
    }

    @Override
    public void listarPlaylists() {
        if (super.getPlaylists().isEmpty()) {
            System.out.println("Nenhuma playlist foi encontrada.");
        } else {
            System.out.println("---- Playlists de " + super.getNome() + " ----");
            for (int i = 0; i <= super.getPlaylists().size() - 1; i++) {
                System.out.println("- " + super.getPlaylists().get(i).getNome());
            }
        }
    }

    @Override
    public String getTipoUsuario() {
        return "Premium";
    }
}
