import frames.EscolhaUsuario;
import musicPlayer.Carregar;
import musicPlayer.Catalogo;
import musicPlayer.MusicPlayer;
import musicPlayer.Recomendacoes;

class Main {
    public static void main(String[] args) {
        try {
            Catalogo catalogo = Carregar.carregarCatalog("src/main/java/assets/musics");
            MusicPlayer musicPlayer = MusicPlayer.getInstancia();
            Recomendacoes recomendacoes = new Recomendacoes(catalogo);
            new EscolhaUsuario();
        } catch (IllegalArgumentException e) {
            System.err.println("Verifique o diretório de músicas passado");
        }
    }
}

