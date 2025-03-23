package frames;

import models.Musica;
import models.Playlist;
import users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameUsuario extends JFrame implements ActionListener {

    final JFrame frameUsuario = new JFrame();
    private Menu menu;
    private Usuario usuario;

    private JButton voltar = new JButton("Voltar");

    public FrameUsuario(Menu menu, Usuario usuario) {
        this.menu = menu;
        this.usuario = usuario;

        // Configuração da janela
        frameUsuario.setTitle("Music Player"); // Título da janela
        frameUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        frameUsuario.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        frameUsuario.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        frameUsuario.setLayout(new BorderLayout()); // Responsividade
        frameUsuario.getContentPane().setBackground(new Color(255, 255, 255)); // Fundo branco

        // Painel superior com o botão de voltar
        JPanel padding = new JPanel(new FlowLayout(FlowLayout.LEFT));
        padding.setBackground(Color.WHITE);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 100));

        // Painel central com o ícone "Music Player"
        JPanel painelIcon = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelIcon.setBackground(Color.WHITE); // Fundo branco
        painelIcon.setPreferredSize(new Dimension(100, 200));

        // Painel de textos (informações do usuário, playlists e histórico)
        JPanel painelTextos = new JPanel();
        painelTextos.setBackground(Color.WHITE);
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        painelTextos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Informações do usuário
        String nomeUsr = this.usuario.getNome();
        String tipoConta = this.usuario.getTipoUsuario();

        JLabel usr = new JLabel("Nome: " + nomeUsr, JLabel.CENTER);
        usr.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteLabel = usr.getFont().deriveFont(26f); // Fonte menor para as labels
        usr.setFont(fonteLabel);
        painelTextos.add(usr);

        JLabel conta = new JLabel("Plano: " + tipoConta, JLabel.CENTER);
        conta.setAlignmentX(Component.CENTER_ALIGNMENT);
        conta.setFont(fonteLabel);
        painelTextos.add(conta);

        JLabel playlistsLabel = new JLabel("Playlists:", JLabel.CENTER);
        playlistsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playlistsLabel.setFont(fonteLabel);
        painelTextos.add(playlistsLabel);

        // TextArea para playlists
        JTextArea playlistsTextArea = new JTextArea();
        playlistsTextArea.setEditable(false); // Impede que o usuário escreva
        Font fonteTextArea = playlistsTextArea.getFont().deriveFont(20f); // Fonte maior para os TextArea
        playlistsTextArea.setFont(fonteTextArea);
        playlistsTextArea.setBackground(Color.WHITE);

        if (!usuario.getPlaylists().isEmpty()) {
            for (Playlist playlist : usuario.getPlaylists()) {
                playlistsTextArea.append("Playlist: " + playlist.getNome() + "\n");
                for (Musica musica : playlist.getMusicas()) {
                    playlistsTextArea.append("   - " + musica.toString() + "\n");
                }
            }
        } else {
            playlistsTextArea.append("Nenhuma playlist criada.\n");
        }

        JScrollPane playlistsScrollPane = new JScrollPane(playlistsTextArea);
        playlistsScrollPane.setPreferredSize(new Dimension(800, 200));
        playlistsScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTextos.add(playlistsScrollPane);

        painelTextos.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel historicoLabel = new JLabel("Histórico de Músicas Escutadas:", JLabel.CENTER);
        historicoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        historicoLabel.setFont(fonteLabel);
        painelTextos.add(historicoLabel);

        // TextArea para histórico
        JTextArea historicoTextArea = new JTextArea();
        historicoTextArea.setEditable(false); // Impede que o usuário escreva
        historicoTextArea.setFont(fonteTextArea); // Fonte maior para os TextArea
        historicoTextArea.setBackground(Color.WHITE);

        if (!usuario.getHistoricoMusicasEscutadas().isEmpty()) {
            for (Musica musica : usuario.getHistoricoMusicasEscutadas()) {
                historicoTextArea.append("   - " + musica.toString() + "\n");
            }
        } else {
            historicoTextArea.append("Histórico vazio.\n");
        }

        JScrollPane historicoScrollPane = new JScrollPane(historicoTextArea);
        historicoScrollPane.setPreferredSize(new Dimension(800, 200));
        historicoScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTextos.add(historicoScrollPane);

        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteT = mainIcon.getFont().deriveFont(80f);
        mainIcon.setFont(fonteT);
        painelIcon.add(mainIcon);

        voltar.setPreferredSize(new Dimension(100, 40));
        Font fonteB = voltar.getFont().deriveFont(20f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);
        padding.add(voltar);

        frameUsuario.add(padding, BorderLayout.NORTH);
        frameUsuario.add(painelIcon, BorderLayout.CENTER);
        frameUsuario.add(painelTextos, BorderLayout.SOUTH);

        frameUsuario.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            frameUsuario.dispose();
            new Menu(menu.getCadastroFrame(), usuario);
        }
    }
}