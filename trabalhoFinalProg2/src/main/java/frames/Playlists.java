package frames;

import models.Musica;
import models.Playlist;
import musicPlayer.PlayerFrame;
import users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Playlists extends JFrame implements ActionListener {

    private Menu menu;
    private Usuario usuario;

    private JButton voltar = new JButton("Voltar");
    private JPanel painelPlaylists = new JPanel();

    public Playlists(Menu menu, Usuario usuario) {
        this.menu = menu;
        this.usuario = usuario;

        // Configuração da janela
        this.setTitle("Music Player"); // Título da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        this.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        this.setLayout(new BorderLayout()); // Responsividade
        this.getContentPane().setBackground(new Color(255, 255, 255)); // cor janela

        ImageIcon icone = new ImageIcon("src/main/java/assets/icon.jpg");
        this.setIconImage(icone.getImage());

        JPanel padding = new JPanel(new FlowLayout(FlowLayout.LEFT));
        padding.setBackground(Color.white);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 70));
        painelPlaylists.setLayout(new BoxLayout(painelPlaylists, BoxLayout.Y_AXIS));

        voltar.setPreferredSize(new Dimension(100, 40));
        Font fonteB = voltar.getFont().deriveFont(20f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);
        padding.add(voltar);

        JScrollPane scrollPane = new JScrollPane(painelPlaylists);

        this.add(padding, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        carregarPlaylists();
        this.setVisible(true);
    }

    private void carregarPlaylists() {
        painelPlaylists.removeAll();
        List<Playlist> playlists = usuario.getPlaylists();

        for (Playlist playlist : playlists) {
            JButton botaoPlaylist = new JButton(playlist.getNome());
            botaoPlaylist.setFocusable(false);
            botaoPlaylist.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Ajustando o tamanho do botão
            botaoPlaylist.setPreferredSize(new Dimension(400, 50)); // Largura maior, altura padrão
            botaoPlaylist.setMaximumSize(new Dimension(400, 50)); // Define o tamanho máximo para evitar expansão

            // Ajustando a fonte (usando a fonte padrão e aumentando o tamanho)
            Font fontePadrao = botaoPlaylist.getFont();
            botaoPlaylist.setFont(fontePadrao.deriveFont(Font.PLAIN, 18)); // Aumenta o tamanho da fonte para 18

            // Adiciona o ActionListener para abrir a playlist e fechar o frame atual
            botaoPlaylist.addActionListener(e -> {
                this.dispose(); // Fecha o frame atual (Playlists)
                new Player(menu, usuario, playlist); // Abre o frame Player
            });

            painelPlaylists.add(botaoPlaylist);
        }

        painelPlaylists.revalidate();
        painelPlaylists.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            this.dispose(); // Fecha o frame atual
            new Menu(menu.getCadastroFrame(), usuario); // Abre o frame Menu
        }
    }
}