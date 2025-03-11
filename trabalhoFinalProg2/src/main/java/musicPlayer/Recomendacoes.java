package musicPlayer;

import models.Musica;
import users.Usuario;

import java.util.ArrayList;

public class Recomendacoes {
    private Catalogo catalogo;

    public Recomendacoes(Catalogo catalogo){
        this.catalogo = catalogo;
    }

    public ArrayList<Musica> recomendarMusica(Usuario usuario){
        ArrayList<Musica> recomendacoes = new ArrayList<>();
        ArrayList<Musica> historicoUsusario = usuario.getHistoricoMusicasEscutadas();

        for (Musica musica : historicoUsusario){
            // Recomenda músicas do mesmo gênero, artista e álbuns escutados
            recomendacoes.addAll(this.catalogo.buscarMusica(musica.getGeneroMusical()));
            recomendacoes.addAll(this.catalogo.buscarMusica(musica.getArtista()));
            recomendacoes.addAll(this.catalogo.buscarMusica(musica.getAlbum()));
        }
        // Remover as que já foram escutadas
        recomendacoes.removeAll(historicoUsusario);
        // Remover repetidas
        ArrayList<Musica> recomendacoesSemRepeticoes = new ArrayList<>();
        for (Musica m: recomendacoes){
            if (!recomendacoesSemRepeticoes.contains(m)){
                recomendacoesSemRepeticoes.add(m);
            }
        }
        return recomendacoesSemRepeticoes;
    }
}
