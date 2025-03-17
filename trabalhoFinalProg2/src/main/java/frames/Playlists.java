package frames;

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

    private JButton voltar = new JButton();
    private JPanel painelPlaylists = new JPanel();

    public Playlists(Menu menu, Usuario usuario) {
        this.menu = menu;
        this.usuario = usuario;

        // Configuração da janela
        this.setTitle("Music Player"); // Título da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        this.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        this.setLayout(new BorderLayout()); // Responsividade
        this.getContentPane().setBackground(new Color(255,255,255));// cor janela

        JPanel padding = new JPanel(new FlowLayout(FlowLayout.LEFT));
        padding.setBackground(Color.GRAY);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 100));
        painelPlaylists.setLayout(new BoxLayout(painelPlaylists, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(painelPlaylists);

        this.add(padding, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        carregarPlaylists();
        this.setVisible(true);
    }

    private void carregarPlaylists() {
        painelPlaylists.removeAll();
        List<Playlist> playlists = usuario.getPlaylists();

        for(Playlist playlist : playlists) {
            JButton botaoPlaylist = new JButton(playlist.getNome());
            botaoPlaylist.setAlignmentX(Component.CENTER_ALIGNMENT);
            botaoPlaylist.addActionListener(e -> new PlayerFrame(playlist));
            painelPlaylists.add(botaoPlaylist);
        }

        painelPlaylists.revalidate();
        painelPlaylists.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==voltar) {
            dispose();
            new Menu(menu.getCadastroFrame(), usuario);
        }
    }

}
