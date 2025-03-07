package musicPlayer;

import models.Musica;
import users.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recomendacao {
    private Catalogo catalogo;

    public Recomendacao(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public ArrayList<Musica> recomendarMusicas(Usuario usuario) {
        ArrayList<Musica> recomendacoes = new ArrayList<>();
        List<Musica> historico = usuario.getHistoricoMusicasEscutadas();

        for (Musica musica : historico) {
            recomendacoes.addAll(catalogo.buscarMusica(musica.getGeneroMusical()));
            recomendacoes.addAll(catalogo.buscarMusica(musica.getArtista()));
            recomendacoes.addAll(catalogo.buscarMusica(musica.getAlbum()));
        }

        // Remover duplicatas
        Set<Musica> set = new HashSet<>(recomendacoes);
        recomendacoes.clear();
        recomendacoes.addAll(set);

        return recomendacoes;
    }
}