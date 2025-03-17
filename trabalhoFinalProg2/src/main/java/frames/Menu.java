package frames;

import users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    final JFrame menu = new JFrame();
    private Cadastro cadastroFrame;
    private Usuario usuario;

    private JButton catalogo = new JButton();
    private JButton criarPlaylist = new JButton();
    private JButton adicionaMscPlaylist = new JButton();
    private JButton removerMscPlaylist = new JButton();
    private JButton reproduzirPlaylist = new JButton();
    private JButton recomendacoes = new JButton();
    private JButton dadosUsr = new JButton();

    public Menu(Cadastro cadastroFrame, Usuario usuario) {
        this.cadastroFrame = cadastroFrame;
        this.usuario = usuario;

        // Configuração da janela
        menu.setTitle("Music Player"); // Título da janela
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        menu.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        menu.setLayout(new BorderLayout()); // Responsividade
        menu.getContentPane().setBackground(new Color(255,255,255));// cor janela

        JPanel padding = new JPanel();
        padding.setBackground(Color.GRAY);
        padding.setPreferredSize(new Dimension(100,150));

        JPanel painelBordaTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBordaTop.setBackground(Color.red);
        painelBordaTop.setPreferredSize(new Dimension(100,300));
        painelBordaTop.setLayout(new BoxLayout(painelBordaTop, BoxLayout.Y_AXIS));

        JPanel menus = new JPanel();
        menus.setLayout(new BoxLayout(menus, BoxLayout.Y_AXIS));

        JPanel painelSelecao1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSelecao1.setBackground(Color.blue);
        painelSelecao1.setPreferredSize(new Dimension(100,150));

        JPanel painelSelecao2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSelecao2.setBackground(Color.yellow);
        painelSelecao2.setPreferredSize(new Dimension(50,280));

        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteT = mainIcon.getFont().deriveFont(80f);
        mainIcon.setFont(fonteT);
        painelBordaTop.add(mainIcon);

        catalogo.setPreferredSize(new Dimension(230,100));
        catalogo.setText("Catálogo");
        Font fonteB = catalogo.getFont().deriveFont(30f);
        catalogo.setFont(fonteB);
        catalogo.setFocusable(false);
        catalogo.addActionListener(this);
        painelSelecao1.add(catalogo);

        criarPlaylist.setPreferredSize(new Dimension(230,100));
        criarPlaylist.setText("<html>Criar<br> Playlist</html>");
        criarPlaylist.setFont(fonteB);
        criarPlaylist.setFocusable(false);
        criarPlaylist.addActionListener(this);
        painelSelecao1.add(criarPlaylist);

        adicionaMscPlaylist.setPreferredSize(new Dimension(230,100));
        adicionaMscPlaylist.setText("<html>Adicionar<br>Músicas</html>");
        adicionaMscPlaylist.setFont(fonteB);
        adicionaMscPlaylist.setFocusable(false);
        adicionaMscPlaylist.addActionListener(this);
        painelSelecao1.add(adicionaMscPlaylist);

        removerMscPlaylist.setPreferredSize(new Dimension(230,100));
        removerMscPlaylist.setText("<html>Remover<br>Músicas</html>");
        removerMscPlaylist.setFont(fonteB);
        removerMscPlaylist.setFocusable(false);
        removerMscPlaylist.addActionListener(this);
        painelSelecao1.add(removerMscPlaylist);

        reproduzirPlaylist.setPreferredSize(new Dimension(230,100));
        reproduzirPlaylist.setText("<html>Reproduzir<br>Playlists</html>");
        reproduzirPlaylist.setFont(fonteB);
        reproduzirPlaylist.setFocusable(false);
        removerMscPlaylist.addActionListener(this);
        painelSelecao2.add(reproduzirPlaylist);

        recomendacoes.setPreferredSize(new Dimension(230,100));
        recomendacoes.setText("<html>Reproduzir<br>Playlists</html>");
        recomendacoes.setFont(fonteB);
        recomendacoes.setFocusable(false);
        recomendacoes.addActionListener(this);
        painelSelecao2.add(recomendacoes);

        dadosUsr.setPreferredSize(new Dimension(230,100));
        dadosUsr.setText("<html>Usuário</html>");
        dadosUsr.setFont(fonteB);
        dadosUsr.setFocusable(false);
        dadosUsr.addActionListener(this);
        painelSelecao2.add(dadosUsr);

        menus.add(painelSelecao1);
        menus.add(painelSelecao2);

        menu.add(padding, BorderLayout.NORTH);
        menu.add(painelBordaTop, BorderLayout.CENTER);
        menu.add(menus, BorderLayout.SOUTH);

        menu.setVisible(true);
    }

    public Cadastro getCadastroFrame() {
        return cadastroFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==catalogo) {
            menu.dispose();
            new FrameCatalogo(this,usuario);
        }
        if(e.getSource()==criarPlaylist) {
            menu.dispose();
            new CriarPlaylists(this,usuario);
        }
        if(e.getSource()==adicionaMscPlaylist) {
            menu.dispose();
        }
        if(e.getSource()==removerMscPlaylist) {
            menu.dispose();
        }
        if(e.getSource()==reproduzirPlaylist) {
            menu.dispose();
            new Playlists(this,usuario);
        }
        if(e.getSource()==recomendacoes) {
            menu.dispose();
        }
        if(e.getSource()==dadosUsr) {
            menu.dispose();
            new FrameUsuario(this, usuario);
        }
    }

}
