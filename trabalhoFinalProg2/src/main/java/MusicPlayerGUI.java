import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicPlayerGUI extends JFrame {
    private MusicPlayer musicPlayer;
    private JSlider progressSlider;
    private Timer progressTimer;
    private JButton playPauseButton, skipForwardButton, skipBackwardButton;

    public MusicPlayerGUI(String filePath) {
        musicPlayer = new MusicPlayer(filePath);

        setTitle("Player de Música com JLayer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configura os botões
        playPauseButton = new JButton("Play");
        skipForwardButton = new JButton("Avançar");
        skipBackwardButton = new JButton("Voltar");

        // Ação dos botões
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (musicPlayer.getIsPlaying()) {
                    musicPlayer.pause();
                    playPauseButton.setText("Play");
                } else {
                    musicPlayer.play();
                    playPauseButton.setText("Pause");
                }
            }
        });

        skipForwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.skip(10); // Avança 10 segundos
            }
        });

        skipBackwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.skip(-10); // Volta 10 segundos
            }
        });

        // Barra de progresso
        progressSlider = new JSlider(0, 100, 0);
        progressSlider.setEnabled(false);
        progressSlider.addChangeListener(e -> {
            if (!progressSlider.getValueIsAdjusting()) {
                musicPlayer.seek(progressSlider.getValue());
            }
        });

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(playPauseButton);
        panel.add(skipForwardButton);
        panel.add(skipBackwardButton);
        panel.add(progressSlider);
        add(panel, BorderLayout.CENTER);

        // Atualiza a barra de progresso a cada segundo
        progressTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualiza a posição do progresso da música
                // Esse valor é arbitrário e dependerá do seu código de controle de frames.
                int progress = (int) (musicPlayer.getCurrentFrame() / 44_100);
                progressSlider.setValue(progress);
            }
        });
        progressTimer.start();
    }

    public static void main(String[] args) {
        // Defina o caminho para o arquivo MP3
        String filePath = "src/musics/Coldplay - Yellow (Official Video) - YouTube.mp3";

        SwingUtilities.invokeLater(() -> {
            MusicPlayerGUI gui = new MusicPlayerGUI(filePath);
            gui.setVisible(true);
        });
    }
}
