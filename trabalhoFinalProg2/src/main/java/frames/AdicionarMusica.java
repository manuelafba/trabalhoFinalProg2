package frames;

import models.Musica;
import models.Playlist;
import musicPlayer.Catalogo;
import users.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarMusica extends JFrame implements ActionListener {

    final JFrame adicionarMusica = new JFrame();
    private Menu menu;
    private Usuario usuario;
    private Catalogo catalogo;

    private JTable tabelaMusicas;
    private DefaultTableModel tableModel;
    private JComboBox<String> comboPlaylists;
    private JButton adicionar = new JButton();
    private JButton voltar = new JButton("Voltar");

    public AdicionarMusica(Menu menu, Usuario usuario, Catalogo catalogo) {
        this.menu = menu;
        this.usuario = usuario;
        this.catalogo = catalogo;

        // Configuração da janela
        adicionarMusica.setTitle("Music Player"); // Título da janela
        adicionarMusica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        adicionarMusica.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        adicionarMusica.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        adicionarMusica.setLayout(new BorderLayout()); // Responsividade
        adicionarMusica.getContentPane().setBackground(new Color(255, 255, 255));// cor janela

        ImageIcon icone = new ImageIcon("src/main/java/assets/icon.jpg");
        adicionarMusica.setIconImage(icone.getImage());

        // Painel superior com o botão "Voltar" e a label "Adicionar Músicas"
        JPanel padding = new JPanel(new GridBagLayout()); // Usando GridBagLayout para posicionamento preciso
        padding.setBackground(Color.white);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 70));

        // Configuração do GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10); // Espaçamento entre os componentes

        // Botão "Voltar"
        voltar.setPreferredSize(new Dimension(100, 40));
        Font fonteB = voltar.getFont().deriveFont(20f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);

        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 0; // Linha 0
        gbc.anchor = GridBagConstraints.WEST; // Alinha à esquerda
        padding.add(voltar, gbc);

        // Label "Adicionar Músicas"
        JLabel labelAdicionarMusicas = new JLabel("Adicionar Músicas", JLabel.CENTER);
        labelAdicionarMusicas.setFont(new Font("Arial", Font.BOLD, 30)); // Fonte maior e em negrito
        gbc.gridx = 1; // Coluna 1
        gbc.gridy = 0; // Linha 0
        gbc.weightx = 1.0; // Ocupa o espaço restante
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza a label
        padding.add(labelAdicionarMusicas, gbc);

        // Tabela de músicas
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("Música");
        tableModel.addColumn("Artista");
        tableModel.addColumn("Álbum");
        tableModel.addColumn("Gênero");

        tabelaMusicas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaMusicas);

        // Painel para seleção da playlist e botão de adicionar
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));

        comboPlaylists = new JComboBox<>();
        comboPlaylists.setPreferredSize(new Dimension(200, 30));
        carregarPlaylists();

        adicionar = new JButton("Adicionar à Playlist");
        adicionar.setFocusable(false);
        adicionar.addActionListener(this);

        painelInferior.add(new JLabel("Selecione a playlist: "));
        painelInferior.add(comboPlaylists);
        painelInferior.add(adicionar);

        // Adiciona os componentes à janela
        adicionarMusica.add(padding, BorderLayout.NORTH);
        adicionarMusica.add(scrollPane, BorderLayout.CENTER);
        adicionarMusica.add(painelInferior, BorderLayout.SOUTH);

        // Carrega as músicas no catálogo
        carregarMusicas();

        adicionarMusica.setVisible(true);
    }

    // Carrega as músicas do catálogo na tabela
    private void carregarMusicas() {
        tableModel.setRowCount(0); // Limpa a tabela

        for (Musica musica : catalogo.getMusicas()) {
            tableModel.addRow(new Object[]{
                    musica.getNome(),
                    musica.getArtista().getNome(),
                    musica.getAlbum().getNome(),
                    musica.getGeneroMusical()
            });
        }
    }

    // Carrega as playlists do usuário no combobox
    private void carregarPlaylists() {
        comboPlaylists.removeAllItems(); // Limpa o combobox

        for (Playlist playlist : usuario.getPlaylists()) {
            comboPlaylists.addItem(playlist.getNome());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            adicionarMusica.dispose(); // Fecha a janela atual
            new Menu(menu.getCadastroFrame(), usuario);
        } else if (e.getSource() == adicionar) {
            int linhaSelecionada = tabelaMusicas.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma música para adicionar!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nomePlaylist = (String) comboPlaylists.getSelectedItem();
            if (nomePlaylist == null) {
                JOptionPane.showMessageDialog(this, "Nenhuma playlist selecionada!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtém a música selecionada
            Musica musicaSelecionada = catalogo.getMusicas().get(linhaSelecionada);

            // Obtém a playlist selecionada
            Playlist playlistSelecionada = usuario.pesquisarPlaylist(nomePlaylist);

            if (playlistSelecionada != null) {
                try {
                    playlistSelecionada.adicionarMusica(musicaSelecionada);
                    JOptionPane.showMessageDialog(this, "Música adicionada à playlist: " + nomePlaylist, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Playlist não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}