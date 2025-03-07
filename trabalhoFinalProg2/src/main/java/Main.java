import models.Artista;
import models.Musica;
import musicPlayer.Catalogo;
import musicPlayer.Loader;

public class Main {
    public static void main(String[] args) {
        String diretorioMusicas = "src/main/musics";
        Catalogo catalogo = Loader.carregarCatalogo(diretorioMusicas);

        // Exibe as músicas carregadas
        for (Musica musica : catalogo.getMusicas()) {
            System.out.println(musica.toString());
        }

        

    }
}