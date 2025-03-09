import models.Playlist;
import musicPlayer.Carregar;
import musicPlayer.Catalogo;
import musicPlayer.MusicPlayer;
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // Carrega o catálogo de músicas
        Catalogo catalogo = Carregar.carregarCatalog("src/main/musics");

        // Cria uma instância do MusicPlayer
        MusicPlayer musicPlayer = MusicPlayer.getInstance();

        // Cria uma playlist e adiciona músicas
        Playlist playlist = new Playlist("Minha Playlist");
        playlist.adicionaMusica(catalogo.getMusicas().get(7));
        playlist.adicionaMusica(catalogo.getMusicas().get(5));

        // Carrega a playlist no MusicPlayer
        musicPlayer.carregarPlaylist(playlist);

        Scanner scanner = new Scanner(System.in);
        // Loop de controle do player
        boolean continua = true;
        while (continua) {
            System.out.println("\n--- Controles do Music Player ---");
            System.out.println("[1] Play");
            System.out.println("[2] Pause");
            System.out.println("[3] Reiniciar");
            System.out.println("[4] Avançar");
            System.out.println("[5] Voltar");
            System.out.println("[6] Sair");
            System.out.print("Sua escolha: ");

            try {
                int opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        if (musicPlayer.getClip() != null && musicPlayer.getClip().isRunning()) {
                            System.out.println("Música já está tocando.");
                        } else {
                            musicPlayer.play();
                        }
                        break;
                    case 2:
                        if (musicPlayer.getClip() == null || !musicPlayer.getClip().isRunning()) {
                            System.out.println("Música já está pausada.");
                        } else {
                            musicPlayer.pause();
                        }
                        break;
                    case 3:
                        musicPlayer.reiniciar();
                        break;
                    case 4:
                        musicPlayer.avancar();
                        break;
                    case 5:
                        musicPlayer.voltar();
                        break;
                    case 6:
                        continua = false;
                        System.out.println("Saindo do Music Player...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        }

        scanner.close(); // Fecha o Scanner ao final do programa
        System.out.println("Programa encerrado.");
    }
}