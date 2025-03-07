import javazoom.jl.decoder.Bitstream;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MusicPlayer {
    private String filePath;
    private Player player;
    private long totalFrames;
    private long currentFrame;
    private Thread playerThread;
    private boolean isPlaying = false;
    private boolean isPaused = false;

    public MusicPlayer(String filePath) {
        this.filePath = filePath;
        try {
            // Obtendo o número total de frames do arquivo MP3 para calcular a duração
            Bitstream bitstream = new Bitstream(new FileInputStream(filePath));
            totalFrames = bitstream.readFrame().max_number_of_frames((int) totalFrames); // Total frames in audio
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getCurrentFrame(){
        return this.currentFrame;
    }

    public boolean getIsPlaying(){
        return this.isPlaying;
    }

    public void play() {
        // Inicia ou retoma a reprodução se estiver pausada
        if (isPaused) {
            isPaused = false;
            startPlayerThread();
        }
        // Se a música não estiver tocando, inicia a reprodução
        else if (!isPlaying) {
            startPlayerThread();
        }
    }

    public void pause() {
        if (player != null) {
            player.close();
            isPaused = true;
            isPlaying = false;
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
        }
        isPlaying = false;
        currentFrame = 0;
    }

    public void seek(long frame) {
        currentFrame = frame;
        stop();
        startPlayerThread();
    }

    public void skip(long seconds) {
        currentFrame += seconds * 44_100; // 44,100 frames por segundo para MP3
        seek(currentFrame);
    }

    private void startPlayerThread() {
        isPlaying = true;
        playerThread = new Thread(() -> {
            try (FileInputStream fis = new FileInputStream(filePath);
                 BufferedInputStream bis = new BufferedInputStream(fis)) {

                player = new Player(bis);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            isPlaying = false;
        });
        playerThread.start();
    }
}
