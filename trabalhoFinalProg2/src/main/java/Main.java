import frames.EscolhaUsuario;
import models.Musica;
import models.Playlist;
import musicPlayer.Carregar;
import musicPlayer.Catalogo;
import musicPlayer.MusicPlayer;
import musicPlayer.Recomendacoes;
import users.Usuario;
import users.UsuarioGratuito;
import users.UsuarioPremium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;

class Main {
    public static void main(String[] args) {
        new EscolhaUsuario();
        /*
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        // Carrega o catálogo de músicas
        Catalogo catalogo = Carregar.carregarCatalog("src/main/musics");

        // Cria uma instância do MusicPlayer
        MusicPlayer musicPlayer = MusicPlayer.getInstancia();

        // Cria objeto Recomendações
        Recomendacoes recomendacoes = new Recomendacoes(catalogo);

        System.out.println("==========================");
        System.out.println("Bem-vindo ao Music Player");
        System.out.println("==========================");

        Usuario usuario = selecionarUsuario(inputReader);

        int opcao = 0;
        while (opcao != 8) {
            exibirMenuOpcoes();
            try {
                opcao = Integer.parseInt(inputReader.readLine());
            } catch (IOException e) {
                System.out.println("Erro ao ler a entrada: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Digite 1 ou 2.");
            }
            if (opcao < 1 || opcao > 8) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }
            switch (opcao) {
                case 1: // Exibir catálogo
                    catalogo.exibirCatalogo();
                    break;
                case 2: // Criar playlists
                    try {
                        System.out.print("Digite o nome da playlist: ");
                        String nomePlaylist = inputReader.readLine();
                        usuario.criarPlaylist(nomePlaylist);
                    } catch (IOException e) {
                        System.out.println("Erro ao ler a entrada: " + e.getMessage());
                    } catch (IllegalStateException e){
                        System.out.println("Erro ao criar a playlist: " + e.getMessage());
                    }
                    break;
                case 3: // Adicionar música a uma playlist
                    try{
                        System.out.print("Digite o nome da música que deseja adicionar: ");
                        String nomeMusica = inputReader.readLine();
                        ArrayList<Musica> resultadoBusca = catalogo.buscarMusica(nomeMusica);
                        if (resultadoBusca.isEmpty()){
                            System.out.println("Não há músicas que correspondem ao nome buscado.");
                            break;
                        }
                        if (usuario.getPlaylists().isEmpty()){
                            System.out.println("Usuário " + usuario.getNome() + " não possui playlists.");
                            break;
                        }
                        usuario.exibirPlaylists();
                        System.out.print("Escolha uma playlist para adicionar a música: ");
                        String nomePlaylist = inputReader.readLine();
                        Playlist playlist = usuario.pesquisarPlaylist(nomePlaylist);
                        if (playlist == null){
                            System.out.println("Playlist não encontrada para esse usuário.");
                            break;
                        }
                        playlist.adicionarMusica(resultadoBusca.getFirst());
                        break;

                    } catch (IOException e) {
                        System.out.println("Erro ao ler a entrada: " + e.getMessage());
                    }

                case 4: // Remover música de uma playlist
                    try {
                        if (usuario.getPlaylists().isEmpty()){
                            System.out.println("Usuário " + usuario.getNome() + " não possui playlists.");
                            break;
                        }
                        usuario.exibirPlaylists();
                        System.out.print("Escolha uma playlist para remover uma música: ");
                        String nomePlaylist = inputReader.readLine();
                        Playlist playlist = usuario.pesquisarPlaylist(nomePlaylist);
                        if (playlist == null){
                            System.out.println("Playlist " + nomePlaylist + " não encontrada para esse usuário.");
                            break;
                        }
                        if (playlist.getMusicas().isEmpty()){
                            System.out.println("Playlist " + playlist.getNome() + " está vazia.");
                            break;
                        }
                        playlist.exibirMusicas();
                        System.out.print("Digite o nome da música que deseja remover: ");
                        String nomeMusica = inputReader.readLine();
                        Musica musica = playlist.buscarMusica(nomeMusica);
                        if (musica == null){
                            System.out.println("Música " + nomeMusica + " não encontrada na playlist " + playlist.getNome() + ".");
                            break;
                        }
                        playlist.removerMusica(musica);
                        break;
                    } catch (IOException e) {
                        System.out.println("Erro ao ler a entrada: " + e.getMessage());
                    }
                case 5: // Reproduzir uma playlist
                    try{
                        if (usuario.getPlaylists().isEmpty()){
                            System.out.println("Usuário " + usuario.getNome() + " não possui playlists para reproduzir.");
                            break;
                        }
                        usuario.exibirPlaylists();
                        System.out.print("Escolha uma playlist para reproduzir: ");
                        String nomePlaylist = inputReader.readLine();
                        Playlist playlist = usuario.pesquisarPlaylist(nomePlaylist);
                        if (playlist == null){
                            System.out.println("Playlist " + nomePlaylist + " não encontrada para esse usuário.");
                            break;
                        }
                        if (playlist.getMusicas().isEmpty()){
                            System.out.println("Playlist " + playlist.getNome() + " está vazia.");
                            break;
                        }
                        // Carregar playlist no player
                        musicPlayer.carregarPlaylist(playlist);
                        // Loop de controle do player
                        boolean continua = true;
                        while (continua) {
                            System.out.println("\n---- Controles do Music Player ----");
                            System.out.println("[1] Play");
                            System.out.println("[2] Pause");
                            System.out.println("[3] Reiniciar");
                            System.out.println("[4] Avançar");
                            System.out.println("[5] Voltar");
                            System.out.println("[6] Sair");
                            System.out.print("Sua escolha: ");
                            int opcaoPlayer = Integer.parseInt(inputReader.readLine());
                            if (opcaoPlayer < 1 || opcaoPlayer > 6) {
                                System.out.println("Opção inválida. Tente novamente.");
                                continue;
                            }
                            switch (opcaoPlayer) {
                                case 1:
                                    if (musicPlayer.getClip() != null && musicPlayer.getClip().isRunning()) {
                                        System.out.println("Música já está tocando.");
                                    } else {
                                        musicPlayer.play();
                                        usuario.adicionarMusicaHistorico(musicPlayer.getMusicaAtual());
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
                                    if (usuario.getTipoUsuario().equals("Gratuito")){
                                        System.out.println("Usuário gratuito não pode avançar músicas. Faça já o Upgrade de plano para receber essa funcionalidade!");
                                        break;
                                    }
                                    musicPlayer.avancar();
                                    break;
                                case 5:
                                    musicPlayer.voltar();
                                    break;
                                case 6:
                                    continua = false;
                                    System.out.println("Saindo do Music Player...");
                                    break;
                            }
                        }
                        break;
                    } catch (IOException e) {
                        System.out.println("Erro ao ler a entrada: " + e.getMessage());
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro: " + e.getMessage());
                    }
                case 6: // Receber recomendações
                    try{
                        if (usuario.getHistoricoMusicasEscutadas().isEmpty()){
                            System.out.println("Você ainda não escutou músicas para basear as recomendações.");
                            break;
                        }
                        ArrayList<Musica> recomendado = recomendacoes.recomendarMusicas(usuario);
                        System.out.println("---- Recomendações para você ----");
                        for (Musica musica : recomendado) {
                            System.out.println(musica.toString());
                        }
                        break;
                    } catch (Exception e){
                        System.out.println("Ocorreu um erro: " + e.getMessage());
                    }
                case 7: // Ver dados usuário
                    try{
                        System.out.println("---- Dados Usuário ----");
                        System.out.println("Nome: " + usuario.getNome());
                        System.out.println("Tipo: " + usuario.getTipoUsuario());
                        System.out.println("Quantidade de Playlists: " + usuario.getPlaylists().size());
                        if (!usuario.getPlaylists().isEmpty()){
                            System.out.println("Playlists: ");
                            for (Playlist playlist : usuario.getPlaylists()) {
                                System.out.println("Nome Playlist: " + playlist.getNome());
                                for (Musica musica : playlist.getMusicas()){
                                    System.out.println(musica.toString());
                                }
                            }
                        }
                        System.out.println("Histórico de músicas escutadas: ");
                        if (!usuario.getHistoricoMusicasEscutadas().isEmpty()){
                            for (Musica musica : usuario.getHistoricoMusicasEscutadas()) {
                                System.out.println(musica.toString());
                            }
                        } else {
                            System.out.println("Histórico vazio.");
                        }
                    } catch (Exception e){
                        System.out.println("Ocorreu um erro: " + e.getMessage());
                    }

            }
        }
    }

    public static Usuario selecionarUsuario(BufferedReader inputReader){
        Usuario usuario = null;
        String nomeUsuario = "";
        while (Objects.equals(nomeUsuario, "")){
            try {
                System.out.print("Digite seu nome de usuário: ");
                nomeUsuario = inputReader.readLine();
            } catch (IOException e) {
                System.out.println("Erro ao ler a entrada: " + e.getMessage());
            }
        }
        while (usuario == null) {
            try {
                System.out.print("Por favor, selecione seu tipo de usuário. \n[1] Gratuito \n[2] Premium \nSua escolha: ");
                int tipoUsuario = Integer.parseInt(inputReader.readLine());

                switch (tipoUsuario) {
                    case 1:
                        usuario = new UsuarioGratuito(nomeUsuario);
                        break;
                    case 2:
                        usuario = new UsuarioPremium(nomeUsuario);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente Novamente.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler a entrada: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Digite 1 ou 2.");
            }
        }
        System.out.println("Usuário " + usuario.getTipoUsuario() + " " + usuario.getNome() + " criado com sucesso!\n");
        return usuario;
    }

    public static void exibirMenuOpcoes(){
        System.out.println("\n---- Menu Opções ----");
        System.out.println("[1] Exibir catálogo.");
        System.out.println("[2] Criar playlist.");
        System.out.println("[3] Adicionar música em uma playlist.");
        System.out.println("[4] Remover música de uma playlist.");
        System.out.println("[5] Reproduzir uma playlist.");
        System.out.println("[6] Receber recomendações.");
        System.out.println("[7] Ver dados do usuário");
        System.out.println("[8] Sair");
        System.out.print("Sua escolha: ");

         */
    }
}

