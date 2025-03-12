package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame implements ActionListener {

    private JFrame telaPrincipal = new JFrame();

    private JButton botaoUser;
    private JButton botaoPlaylist;
    private JButton botaoAlbum;
    private JButton botaoPesquisar;

//    private

    public TelaPrincipal() {

        // Configuração da janela
        telaPrincipal.setTitle("Teste"); // Título da janela
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        telaPrincipal.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        telaPrincipal.setLayout(new BorderLayout()); // Responsividade
        telaPrincipal.getContentPane().setBackground(new Color(255,255,255));// cor janela
        ImageIcon icon = new ImageIcon("src/main/java/assets/icon.jpg");
        telaPrincipal.setIconImage(icon.getImage());

        JPanel painelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelUsuario.setBackground(Color.red);
        painelUsuario.setPreferredSize(new Dimension(100,50));

        JPanel painelLogo = new JPanel();
        painelLogo.setBackground(Color.green);
        painelLogo.setPreferredSize(new Dimension(100,80));

        JPanel painelSelecao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSelecao.setBackground(Color.blue);
        painelSelecao.setPreferredSize(new Dimension(100,350));

        JPanel painelBorda = new JPanel();
        painelBorda.setBackground(Color.yellow);
        painelBorda.setPreferredSize(new Dimension(50,50));

        botaoUser = new JButton();
        botaoUser.setPreferredSize(new Dimension(30,30));
        botaoUser.addActionListener(this);
        painelUsuario.add(botaoUser);

        botaoPlaylist = new JButton();
        botaoPlaylist.setPreferredSize(new Dimension(200,200));
        botaoPlaylist.setText("Playlists");
        Font fonteB = botaoPlaylist.getFont().deriveFont(35f);
        botaoPlaylist.setFont(fonteB);
        botaoPlaylist.setFocusable(false);
        botaoPlaylist.addActionListener(this);

        botaoAlbum = new JButton();
        botaoAlbum.setPreferredSize(new Dimension(200,200));
        botaoAlbum.setText("Álbums");
        botaoAlbum.setFont(fonteB);
        botaoAlbum.addActionListener(this);

        botaoPesquisar = new JButton();
        botaoPesquisar.setPreferredSize(new Dimension(200,200));
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.setFont(fonteB);
        botaoPesquisar.addActionListener(this);
        painelSelecao.add(botaoPlaylist);
        painelSelecao.add(botaoAlbum);
        painelSelecao.add(botaoPesquisar);



        // Configuração - Nome e Ícone do musicPlayer
        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainIcon.setIcon(icon);

        painelLogo.add(mainIcon);

        // Obtendo a fonte atual do JLabel
        Font fonte = mainIcon.getFont();

        // Alterando apenas o tamanho da fonte
        Font novaFonte = fonte.deriveFont(50f); // Tamanho 24
        mainIcon.setFont(novaFonte);

        mainIcon.setHorizontalTextPosition(JLabel.CENTER);
        mainIcon.setVerticalTextPosition(JLabel.BOTTOM);
        mainIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Adicionando labels e panels
        telaPrincipal.add(painelUsuario, BorderLayout.NORTH);
        telaPrincipal.add(painelLogo,BorderLayout.CENTER);
        telaPrincipal.add(painelSelecao, BorderLayout.SOUTH);

        telaPrincipal.setVisible(true); // Permite a visualização da janela

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botaoUser) {
            telaPrincipal.dispose();
            Usuario telaUsuario = new Usuario();
        }
        if(e.getSource()==botaoAlbum) {
            telaPrincipal.dispose();
            Albums telaAlbum = new Albums();
        }
        if(e.getSource()==botaoPesquisar) {
            telaPrincipal.dispose();
            Pesquisar telaPesquisar = new Pesquisar();
        }
        if(e.getSource()==botaoPlaylist) {
            telaPrincipal.dispose();
            Playlists telaPlaylists = new Playlists();
        }
    }
}
