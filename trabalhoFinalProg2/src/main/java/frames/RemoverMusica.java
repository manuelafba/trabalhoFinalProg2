package frames;

import models.Musica;
import models.Playlist;
import users.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoverMusica extends JFrame implements ActionListener {

    private Menu menu;
    private Usuario usuario;

    private JButton voltar, removerMusica;
    private JTable tabelaMusicas;
    private DefaultTableModel tableModel;
    private JComboBox<String> comboPlaylists;

    public RemoverMusica(Menu menu, Usuario usuario) {
        this.menu = menu;
        this.usuario = usuario;

        // Configuração da janela
        this.setTitle("Music Player");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        this.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(255, 255, 255));

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

        // Tabela de músicas
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Música");
        tableModel.addColumn("Artista");
        tableModel.addColumn("Álbum");

        tabelaMusicas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaMusicas);

        // Painel para seleção da playlist e botão de remover
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));

        comboPlaylists = new JComboBox<>();
        comboPlaylists.setPreferredSize(new Dimension(200, 30));
        comboPlaylists.addActionListener(e -> carregarMusicasDaPlaylist());
        carregarPlaylists();

        removerMusica = new JButton("Remover Música");
        removerMusica.setFocusable(false);
        removerMusica.addActionListener(this);

        painelInferior.add(new JLabel("Selecione a playlist: "));
        painelInferior.add(comboPlaylists);
        painelInferior.add(removerMusica);

        // Adiciona os componentes à janela
        this.add(padding, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(painelInferior, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    // Carrega as playlists do usuário no combobox
    private void carregarPlaylists() {
        comboPlaylists.removeAllItems(); // Limpa o combobox

        for (Playlist playlist : usuario.getPlaylists()) {
            comboPlaylists.addItem(playlist.getNome());
        }
    }

    // Carrega as músicas da playlist selecionada na tabela
    private void carregarMusicasDaPlaylist() {
        String nomePlaylist = (String) comboPlaylists.getSelectedItem();
        if (nomePlaylist != null) {
            Playlist playlistSelecionada = usuario.pesquisarPlaylist(nomePlaylist);
            if (playlistSelecionada != null) {
                tableModel.setRowCount(0); // Limpa a tabela

                for (Musica musica : playlistSelecionada.getMusicas()) {
                    tableModel.addRow(new Object[]{
                            musica.getNome(),
                            musica.getArtista().getNome(),
                            musica.getAlbum().getNome()
                    });
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            this.dispose();
            new Menu(menu.getCadastroFrame(), usuario);// Fecha a janela atual
        } else if (e.getSource() == removerMusica) {
            int linhaSelecionada = tabelaMusicas.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma música para remover!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nomePlaylist = (String) comboPlaylists.getSelectedItem();
            if (nomePlaylist == null) {
                JOptionPane.showMessageDialog(this, "Nenhuma playlist selecionada!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtém a playlist selecionada
            Playlist playlistSelecionada = usuario.pesquisarPlaylist(nomePlaylist);

            if (playlistSelecionada != null) {
                // Obtém a música selecionada
                Musica musicaSelecionada = playlistSelecionada.getMusicas().get(linhaSelecionada);

                // Remove a música da playlist
                playlistSelecionada.removerMusica(musicaSelecionada);

                // Atualiza a tabela
                carregarMusicasDaPlaylist();

                JOptionPane.showMessageDialog(this, "Música removida da playlist: " + playlistSelecionada.getNome(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Playlist não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}