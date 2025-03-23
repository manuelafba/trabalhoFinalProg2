package frames;

import models.Musica;
import models.Playlist;
import musicPlayer.MusicPlayer;
import users.Usuario;
import users.UsuarioGratuito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends JFrame implements ActionListener {

    private Menu menu;
    private Usuario usuario;
    private Playlist playlist;

    private JButton voltar, tocarMusica;
    private JButton pause, avancar, voltarMusica, reiniciar;
    private JTable tabelaMusicas;
    private DefaultTableModel tableModel;
    private JLabel musicaAtualLabel;

    public Player(Menu menu, Usuario usuario, Playlist playlist) {
        this.menu = menu;
        this.usuario = usuario;
        this.playlist = playlist;

        // Configuração da janela
        this.setTitle("Music Player"); // Título da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        this.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        this.setLayout(new BorderLayout()); // Responsividade
        this.getContentPane().setBackground(new Color(255, 255, 255)); // Cor da janela

        ImageIcon icone = new ImageIcon("src/main/java/assets/icon.jpg");
        this.setIconImage(icone.getImage());

        // Painel superior com o botão de voltar
        JPanel padding = new JPanel(new FlowLayout(FlowLayout.LEFT));
        padding.setBackground(Color.white);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 70));

        voltar = new JButton("Voltar");
        voltar.setPreferredSize(new Dimension(100, 40));
        Font fonteB = voltar.getFont().deriveFont(20f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);
        padding.add(voltar);

        // Tabela de músicas (não editável)
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };
        tableModel.addColumn("Música");
        tableModel.addColumn("Artista");
        tableModel.addColumn("Álbum");

        tabelaMusicas = new JTable(tableModel);
        tabelaMusicas.setFocusable(false); // Impede que a tabela seja focada
        JScrollPane scrollPane = new JScrollPane(tabelaMusicas);

        // Rótulo para exibir a música atual
        musicaAtualLabel = new JLabel("Nenhuma música tocando", JLabel.CENTER);
        musicaAtualLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Painel de controles de reprodução
        JPanel controlesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        pause = new JButton("Play");
        pause.setFocusable(false);
        pause.addActionListener(this);

        avancar = new JButton("Avançar");
        avancar.setFocusable(false);
        avancar.addActionListener(this);

        voltarMusica = new JButton("Voltar");
        voltarMusica.setFocusable(false);
        voltarMusica.addActionListener(this);

        reiniciar = new JButton("Reiniciar");
        reiniciar.setFocusable(false);
        reiniciar.addActionListener(this);

        if (usuario instanceof UsuarioGratuito) {
            avancar.setEnabled(false); // Desabilita o botão "Avançar" para usuários gratuitos
        }

        controlesPanel.add(voltarMusica);
        controlesPanel.add(pause);
        controlesPanel.add(avancar);
        controlesPanel.add(reiniciar);

        // Botão para tocar a playlist
        tocarMusica = new JButton("Tocar Playlist");
        tocarMusica.setFocusable(false);
        tocarMusica.addActionListener(this);

        // Painel inferior com o botão "Tocar Playlist" e controles de reprodução
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(tocarMusica, BorderLayout.NORTH);
        bottomPanel.add(controlesPanel, BorderLayout.CENTER);

        // Adiciona os componentes à janela
        this.add(padding, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(musicaAtualLabel, BorderLayout.SOUTH);
        this.add(bottomPanel, BorderLayout.SOUTH);

        carregarMusicas();
        atualizarEstadoPause(); // Atualiza o estado do botão "Pause" ao abrir a janela
        this.setVisible(true);
    }

    private void carregarMusicas() {
        tableModel.setRowCount(0);

        for (Musica musica : playlist.getMusicas()) {
            tableModel.addRow(new Object[]{
                    musica.getNome(),
                    musica.getArtista().getNome(),
                    musica.getAlbum().getNome()
            });
        }
    }

    private void atualizarMusicaAtual() {
        MusicPlayer player = MusicPlayer.getInstancia();
        Musica musicaAtual = player.getMusicaAtual();
        usuario.adicionarMusicaHistorico(musicaAtual);
        if (musicaAtual != null) {
            musicaAtualLabel.setText("Tocando agora: " + musicaAtual.getNome());
        } else {
            musicaAtualLabel.setText("Nenhuma música tocando");
        }
    }

    private void atualizarEstadoPause() {
        MusicPlayer player = MusicPlayer.getInstancia();
        if (player.getClip() != null && player.getClip().isRunning()) {
            pause.setText("Pause"); // Se estiver tocando, o botão deve mostrar "Pause"
        } else {
            pause.setText("Play"); // Se estiver pausado ou parado, o botão deve mostrar "Play"
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MusicPlayer player = MusicPlayer.getInstancia();

        if (e.getSource() == voltar) {
            this.dispose();
            new Playlists(menu, usuario);
        } else if (e.getSource() == tocarMusica) {
            try {
                // Carrega a playlist no MusicPlayer
                player.carregarPlaylist(playlist);

                // Inicia a reprodução da playlist a partir da primeira música
                player.play();

                // Atualiza o rótulo da música atual
                atualizarMusicaAtual();

                // Altera o texto do botão para "Pause"
                pause.setText("Pause");

                JOptionPane.showMessageDialog(this, "Tocando playlist: " + playlist.getNome(), "Tocando Playlist", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }


        } else if (e.getSource() == pause) {
            if (player.getClip() != null && player.getClip().isRunning()) {
                player.pause();
                pause.setText("Play");
            } else {
                player.play();
                pause.setText("Pause");
            }
            atualizarMusicaAtual();
        } else if (e.getSource() == avancar) {
            player.avancar();
            atualizarMusicaAtual();
        } else if (e.getSource() == voltarMusica) {
            player.voltar();
            atualizarMusicaAtual();
        } else if (e.getSource() == reiniciar) {
            player.reiniciar();
            atualizarMusicaAtual();
        }
    }
}