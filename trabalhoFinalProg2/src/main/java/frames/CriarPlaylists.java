package frames;

import users.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarPlaylists extends JFrame implements ActionListener {

    final JFrame criarPlaylists = new JFrame();
    private Usuario usuario;
    private Menu menu;

    private JButton voltar = new JButton();
    private JButton criar = new JButton("Criar Playlist");
    private JTextField inputNomePlaylist = new JTextField(5);

    public CriarPlaylists(Menu menu, Usuario usuario) {
        this.menu = menu;
        this.usuario = usuario;

        // Configuração da janela
        criarPlaylists.setTitle("Music Player"); // Título da janela
        criarPlaylists.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        criarPlaylists.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        criarPlaylists.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        criarPlaylists.setLayout(new BorderLayout()); // Responsividade
        criarPlaylists.getContentPane().setBackground(new Color(255,255,255));// cor janela

        JPanel padding = new JPanel(new FlowLayout(FlowLayout.LEFT));
        padding.setBackground(Color.GRAY);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 100));

        JPanel painelIcon = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelIcon.setBackground(Color.green);
        painelIcon.setPreferredSize(new Dimension(100,60));

        JPanel painelAdicionar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,20));
        painelAdicionar.setBackground(Color.red);
        painelAdicionar.setPreferredSize(new Dimension(100,500));
        painelAdicionar.setLayout(new BoxLayout(painelAdicionar,BoxLayout.Y_AXIS));

        JPanel texto = new JPanel();
        texto.setPreferredSize(new Dimension(50,-30));

        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteT = mainIcon.getFont().deriveFont(80f);
        mainIcon.setFont(fonteT);
        painelIcon.add(mainIcon);

        voltar.setPreferredSize(new Dimension(40,40));
        Font fonteB = voltar.getFont().deriveFont(35f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);
        padding.add(voltar);

        texto.add(inputNomePlaylist);
        painelAdicionar.add(texto);

        criar.setPreferredSize(new Dimension(300,200));
        Font fonteA = criar.getFont().deriveFont(35f);
        criar.setFont(fonteA);
        criar.setFocusable(false);
        criar.setAlignmentX(Component.CENTER_ALIGNMENT);
        criar.addActionListener(this);
        painelAdicionar.add(criar);

        criarPlaylists.add(padding, BorderLayout.NORTH);
        criarPlaylists.add(painelIcon,BorderLayout.CENTER);
        criarPlaylists.add(painelAdicionar, BorderLayout.SOUTH);

        criarPlaylists.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==voltar) {
            criarPlaylists.dispose();
            new Menu(menu.getCadastroFrame(), usuario);
        }
        if(e.getSource()==criar) {
            String nomePlaylist = inputNomePlaylist.getText().trim();
            if(nomePlaylist.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome da playlist não pode ser vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                usuario.criarPlaylist(nomePlaylist);
                JOptionPane.showMessageDialog(this,"Playlist criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                inputNomePlaylist.setText("");
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
