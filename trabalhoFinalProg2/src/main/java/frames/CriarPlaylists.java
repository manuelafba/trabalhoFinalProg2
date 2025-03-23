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

    private JButton voltar = new JButton("Voltar");
    private JButton criar = new JButton("Criar Playlist");
    private JTextField inputNomePlaylist = new JTextField(20); // Aumentei o número de colunas

    public CriarPlaylists(Menu menu, Usuario usuario) {
        this.menu = menu;
        this.usuario = usuario;

        // Configuração da janela
        criarPlaylists.setTitle("Music Player"); // Título da janela
        criarPlaylists.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        criarPlaylists.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        criarPlaylists.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        criarPlaylists.setLayout(new BorderLayout()); // Responsividade
        criarPlaylists.getContentPane().setBackground(Color.WHITE); // Fundo branco

        // Painel superior (NORTH)
        JPanel padding = new JPanel(new FlowLayout(FlowLayout.LEFT));
        padding.setBackground(Color.WHITE); // Fundo branco
        padding.setPreferredSize(new Dimension(padding.getWidth(), 100));

        voltar.setPreferredSize(new Dimension(100, 40));
        Font fonteB = voltar.getFont().deriveFont(20f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);
        padding.add(voltar);

        // Painel central (CENTER)
        JPanel painelCentral = new JPanel(new GridBagLayout()); // Usando GridBagLayout para centralização
        painelCentral.setBackground(Color.WHITE); // Fundo branco
        painelCentral.setPreferredSize(new Dimension(100, 100)); // Reduzi a altura do painel central

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteT = mainIcon.getFont().deriveFont(80f);
        mainIcon.setFont(fonteT);
        painelCentral.add(mainIcon, gbc);

        // Adiciona o JTextField e o JButton logo abaixo da label "Music Player"
        gbc.gridy = 1; // Segunda linha no painel central
        gbc.insets = new Insets(20, 10, 10, 10); // Ajusta o espaçamento superior
        inputNomePlaylist.setPreferredSize(new Dimension(400, 40)); // Aumentei o tamanho do JTextField
        painelCentral.add(inputNomePlaylist, gbc);

        gbc.gridy = 2; // Terceira linha no painel central
        gbc.insets = new Insets(10, 10, 20, 10); // Ajusta o espaçamento inferior
        criar.setPreferredSize(new Dimension(300, 80));
        Font fonteA = criar.getFont().deriveFont(35f);
        criar.setFont(fonteA);
        criar.setFocusable(false);
        criar.setAlignmentX(Component.CENTER_ALIGNMENT);
        criar.addActionListener(this);
        painelCentral.add(criar, gbc);

        // Adiciona os painéis à janela
        criarPlaylists.add(padding, BorderLayout.NORTH);
        criarPlaylists.add(painelCentral, BorderLayout.CENTER);

        criarPlaylists.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            criarPlaylists.dispose();
            new Menu(menu.getCadastroFrame(), usuario);
        }
        if (e.getSource() == criar) {
            String nomePlaylist = inputNomePlaylist.getText().trim();
            if (nomePlaylist.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome da playlist não pode ser vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                usuario.criarPlaylist(nomePlaylist);
                JOptionPane.showMessageDialog(this, "Playlist criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                inputNomePlaylist.setText("");
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}