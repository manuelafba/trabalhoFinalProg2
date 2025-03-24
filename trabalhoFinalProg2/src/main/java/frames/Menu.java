package frames;

import musicPlayer.Catalogo;
import users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    final JFrame menu = new JFrame();
    private Cadastro cadastroFrame;
    private Usuario usuario;
    private Catalogo catalogoC;

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
        this.catalogoC = Catalogo.getInstancia();

        // Configuração da janela
        menu.setTitle("Music Player"); // Título da janela
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        menu.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        menu.setLayout(new BorderLayout()); // Responsividade
        menu.getContentPane().setBackground(new Color(255, 255, 255));// cor janela

        // Carrega o ícone usando o caminho absoluto
        ImageIcon icone = new ImageIcon("src/main/java/assets/icon.jpg");

        // Redimensiona o ícone para 200x200 pixels
        Image imagemRedimensionada = icone.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon iconeRedimensionado = new ImageIcon(imagemRedimensionada);

        // Define o ícone da janela
        menu.setIconImage(iconeRedimensionado.getImage());

        // Painel de padding para abaixar os elementos
        JPanel padding = new JPanel();
        padding.setBackground(Color.white);
        padding.setPreferredSize(new Dimension(100, 100)); // Espaço no topo

        // Painel superior com o ícone e o título
        JPanel painelBordaTop = new JPanel();
        painelBordaTop.setBackground(Color.white);
        painelBordaTop.setPreferredSize(new Dimension(100, 400)); // Aumentei a altura para acomodar o ícone e o label
        painelBordaTop.setLayout(new BoxLayout(painelBordaTop, BoxLayout.Y_AXIS));

        // Label com o ícone redimensionado
        JLabel iconLabel = new JLabel(iconeRedimensionado);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelBordaTop.add(Box.createVerticalGlue()); // Adiciona espaço flexível no topo
        painelBordaTop.add(iconLabel);

        // Label "Music Player"
        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteT = mainIcon.getFont().deriveFont(80f);
        mainIcon.setFont(fonteT);
        painelBordaTop.add(mainIcon);
        painelBordaTop.add(Box.createVerticalGlue()); // Adiciona espaço flexível na base

        // Painel principal para os botões
        JPanel menus = new JPanel();
        menus.setLayout(new BoxLayout(menus, BoxLayout.Y_AXIS));

        // Painel para os botões da primeira linha
        JPanel painelSelecao1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSelecao1.setBackground(Color.white);
        painelSelecao1.setPreferredSize(new Dimension(100, 150));

        // Painel para os botões da segunda linha
        JPanel painelSelecao2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSelecao2.setBackground(Color.white);
        painelSelecao2.setPreferredSize(new Dimension(100, 150));

        // Botão Catálogo
        catalogo.setPreferredSize(new Dimension(230, 100));
        catalogo.setText("Catálogo");
        Font fonteB = catalogo.getFont().deriveFont(30f);
        catalogo.setFont(fonteB);
        catalogo.setFocusable(false);
        catalogo.addActionListener(this);
        painelSelecao1.add(catalogo);

        // Botão Criar Playlist
        criarPlaylist.setPreferredSize(new Dimension(230, 100));
        criarPlaylist.setText("<html>Criar<br> Playlist</html>");
        criarPlaylist.setFont(fonteB);
        criarPlaylist.setFocusable(false);
        criarPlaylist.addActionListener(this);
        painelSelecao1.add(criarPlaylist);

        // Botão Adicionar Músicas
        adicionaMscPlaylist.setPreferredSize(new Dimension(230, 100));
        adicionaMscPlaylist.setText("<html>Adicionar<br>Músicas</html>");
        adicionaMscPlaylist.setFont(fonteB);
        adicionaMscPlaylist.setFocusable(false);
        adicionaMscPlaylist.addActionListener(this);
        painelSelecao1.add(adicionaMscPlaylist);

        // Botão Remover Músicas
        removerMscPlaylist.setPreferredSize(new Dimension(230, 100));
        removerMscPlaylist.setText("<html>Remover<br>Músicas</html>");
        removerMscPlaylist.setFont(fonteB);
        removerMscPlaylist.setFocusable(false);
        removerMscPlaylist.addActionListener(this);
        painelSelecao1.add(removerMscPlaylist);

        // Botão Reproduzir Playlists
        reproduzirPlaylist.setPreferredSize(new Dimension(230, 100));
        reproduzirPlaylist.setText("<html>Reproduzir<br>Playlists</html>");
        reproduzirPlaylist.setFont(fonteB);
        reproduzirPlaylist.setFocusable(false);
        reproduzirPlaylist.addActionListener(this);
        painelSelecao2.add(reproduzirPlaylist);

        // Botão Recomendações
        recomendacoes.setPreferredSize(new Dimension(270, 100));
        recomendacoes.setText("<html>Recomendações</html>");
        recomendacoes.setFont(fonteB);
        recomendacoes.setFocusable(false);
        recomendacoes.addActionListener(this);
        painelSelecao2.add(recomendacoes);

        // Botão Usuário
        dadosUsr.setPreferredSize(new Dimension(230, 100));
        dadosUsr.setText("<html>Usuário</html>");
        dadosUsr.setFont(fonteB);
        dadosUsr.setFocusable(false);
        dadosUsr.addActionListener(this);
        painelSelecao2.add(dadosUsr);

        // Adiciona os painéis de seleção ao painel principal
        menus.add(painelSelecao1);
        menus.add(painelSelecao2);

        // Adiciona os painéis à janela
        menu.add(padding, BorderLayout.NORTH); // Espaço no topo para abaixar os elementos
        menu.add(painelBordaTop, BorderLayout.CENTER); // Ícone e label "Music Player"
        menu.add(menus, BorderLayout.SOUTH); // Painel com os botões

        menu.setVisible(true);
    }

    public Cadastro getCadastroFrame() {
        return cadastroFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == catalogo) {
            menu.dispose();
            new FrameCatalogo(this, usuario);
        }
        if (e.getSource() == criarPlaylist) {
            menu.dispose();
            new CriarPlaylists(this, usuario);
        }
        if (e.getSource() == adicionaMscPlaylist) {
            menu.dispose();
            new AdicionarMusica(this, usuario, catalogoC);
        }
        if (e.getSource() == removerMscPlaylist) {
            menu.dispose();
            new RemoverMusica(this, usuario);
        }
        if (e.getSource() == reproduzirPlaylist) {
            menu.dispose();
            new Playlists(this, usuario);
        }
        if (e.getSource() == recomendacoes) {
            menu.dispose();
            new RecomendacoesFrame(this, usuario, catalogoC);
        }
        if (e.getSource() == dadosUsr) {
            menu.dispose();
            new FrameUsuario(this, usuario);
        }
    }
}