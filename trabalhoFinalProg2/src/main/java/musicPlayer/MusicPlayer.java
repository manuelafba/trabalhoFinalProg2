package musicPlayer;

import interfaces.Reprodutivel;
import models.Musica;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MusicPlayer implements Reprodutivel {
    private Musica musicaAtual;
    private boolean estaTocando;
    private Player player; // Player da biblioteca JLayer
    private Thread playerThread; // Thread para tocar a música

    public MusicPlayer() {
        this.estaTocando = false;
    }

    public Musica getMusicaAtual() {
        return this.musicaAtual;
    }

    public boolean isEstaTocando() {
        return this.estaTocando;
    }

    public void setMusicaAtual(Musica musicaAtual) {
        this.musicaAtual = musicaAtual;
    }

    public void setEstaTocando(boolean estaTocando) {
        this.estaTocando = estaTocando;
    }

    @Override
    public void play() {
        if (musicaAtual != null) {
            try {
                // Cria um FileInputStream para o arquivo MP3
                FileInputStream fileInputStream = new FileInputStream(musicaAtual.getDiretorio());


                // Inicia uma nova thread para tocar a música
                this.playerThread = new Thread(() -> {
                    try {
                        this.player = new Player(fileInputStream);
                        this.estaTocando = true;
                        System.out.println("Tocando: " + musicaAtual.getNome());
                        player.play(); // Inicia a reprodução
                    } catch (JavaLayerException e) {
                        System.out.println("Erro ao reproduzir a música: " + e.getMessage());
                    } finally {
                        this.estaTocando = false;
                    }
                });

                playerThread.start(); // Inicia a thread de reprodução

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo de música não encontrado: " + musicaAtual.getDiretorio());
            }
        } else {
            System.out.println("Nenhuma música carregada.");
        }
    }

    @Override
    public void pause() {
        if (this.estaTocando && this.player != null) {
            this.player.close(); // Para a reprodução
            this.estaTocando = false;
            System.out.println("Pausado: " + this.musicaAtual.getNome());
        } else {
            System.out.println("A música não está tocando.");
        }
    }

    @Override
    public void avancar() {
        // Implementar lógica para avançar para a próxima música
        System.out.println("Avançando para a próxima música...");
    }

    @Override
    public void voltar() {
        // Implementar lógica para voltar para a música anterior
        System.out.println("Voltando para a música anterior...");
    }
}