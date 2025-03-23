package musicPlayer;

import interfaces.Reprodutivel;

import models.Musica;
import models.Playlist;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

// Implementa LineListener para poder lidar com retorno do pause no momento correto em que a música parou
public class MusicPlayer implements Reprodutivel, LineListener {
    // Instância única da classe
    private static MusicPlayer instancia;

    private Playlist playlist;
    private int indiceMusica;
    private Musica musicaAtual;
    private String caminhoMusica;
    private Clip clip;


    private MusicPlayer() {
        this.indiceMusica = 0;
    }

    public static MusicPlayer getInstancia() {
        if (instancia == null){
            instancia = new MusicPlayer();
        }
        return instancia;
    }

    public Musica getMusicaAtual() {
        return musicaAtual;
    }

    public Clip getClip() {
        return clip;
    }

    public void carregarPlaylist(Playlist playlist){
        if (this.clip != null){
            this.clip.close();
            this.clip = null;
        }
        this.playlist = playlist;
        this.indiceMusica = 0;
        if (!playlist.getMusicas().isEmpty()) {
            this.musicaAtual = playlist.getMusicas().get(indiceMusica);
            this.caminhoMusica = this.musicaAtual.getDiretorio();
        }

    }

    @Override
    public void play() {
        if (this.musicaAtual == null && this.caminhoMusica == null) {
            System.out.println("Nenhuma música carregada");
            throw new IllegalArgumentException("Nenhuma música carregada");
        }
        try{
            if (clip == null) {
                // Inicializa o Clip apenas se ele ainda não foi criado
                File file = new File(caminhoMusica);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.addLineListener(this); // Adicionar o LineListener ao Clip para monitorar quando a música termina para passar para a próxima
            }
            clip.start(); // Iniciar a reprodução
            System.out.println("Tocando: " + this.musicaAtual.getNome());

        }  catch(UnsupportedAudioFileException e){
            System.out.println("Tipo do arquivo de áudio não suportado");
        }
        catch(LineUnavailableException e){
            System.out.println("Não foi possível acessar o recurso de áudio");
        }
        catch(IOException e){
            System.out.println("Erro ao processar a música: " + this.musicaAtual.getNome());
        }
    }

    @Override
    public void pause() {
        if (this.clip != null && this.clip.isRunning()){
            this.clip.stop(); // Pausar a reprodução
            System.out.println("Pausado: " + this.musicaAtual.getNome());
        }
    }

    @Override
    public void reiniciar() {
        if (clip != null) {
            clip.setMicrosecondPosition(0); // Reinicia a música
            clip.start(); // Inicia a reprodução novamente
            System.out.println("Reiniciando: " + playlist.getMusicas().get(indiceMusica).getNome());
        } else {
            System.out.println("Nenhuma música carregada.");
        }
    }

    @Override
    public void avancar() {
        if (this.playlist == null || this.playlist.getMusicas().isEmpty()) {
            System.out.println("Playlist está vazia!");
            return;
        }
        // Fecha o Clip atual para liberar recursos
        if (this.clip != null){
            this.clip.close();
            this.clip = null;
        }
        // Avança para a próxima música
        this.indiceMusica = (this.indiceMusica + 1) % this.playlist.getMusicas().size(); // Cálculo para garantir que quando chegar no fim da playlist, o índice volta para 0
        this.musicaAtual = this.playlist.getMusicas().get(indiceMusica);
        this.caminhoMusica = this.musicaAtual.getDiretorio();
        this.play();
        System.out.println("Avançando para a próxima música: " + this.musicaAtual.getNome()); // Reinicia a reprodução com a nova música
    }

    @Override
    public void voltar() {
        if (this.playlist == null || this.playlist.getMusicas().isEmpty()) {
            System.out.println("Playlist está vazia!");
            return;
        }
        // Fecha o Clip atual para liberar recursos
        if (this.clip != null){
            this.clip.close();
            this.clip = null;
        }
        // Volta para a música anterior
        this.indiceMusica = (this.indiceMusica - 1 + this.playlist.getMusicas().size()) % this.playlist.getMusicas().size(); // Cálculo para garantir que quando chegar no fim da playlist, o índice volta para 0
        this.musicaAtual = this.playlist.getMusicas().get(indiceMusica);
        this.caminhoMusica = this.musicaAtual.getDiretorio();
        this.play();
        System.out.println("Voltando para a música anterior: " + this.musicaAtual.getNome()); // Reinicia a reprodução com a nova música
    }

@Override
public void update(LineEvent event) {
    if (event.getType() == LineEvent.Type.STOP) {
        // Verifica se a música terminou naturalmente, compara a posição atual com o comprimento final
        if (this.clip.getMicrosecondPosition() == this.clip.getMicrosecondLength()) {
            this.avancar(); // Avança para a próxima música, somente se ela acabar
        }
    }
}
}