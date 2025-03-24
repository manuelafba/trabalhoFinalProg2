package frames;

import models.Musica;
import musicPlayer.Carregar;
import musicPlayer.Catalogo;
import users.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameCatalogo extends JFrame implements ActionListener {

    final JFrame catalogo = new JFrame();
    private JButton voltar = new JButton("Voltar");
    private JTable tabelaMsc;
    private DefaultTableModel tableModel;
    private Menu menu;
    private Usuario usuario;

    public FrameCatalogo(Menu menu, Usuario usuario) {
        this.menu = menu;
        this.usuario = usuario;

        // Configuração da janela
        catalogo.setTitle("Music Player"); // Título da janela
        catalogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        catalogo.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        catalogo.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        catalogo.setLayout(new BorderLayout()); // Responsividade
        catalogo.getContentPane().setBackground(new Color(255, 255, 255));// cor janela

        ImageIcon icone = new ImageIcon("src/main/java/assets/icon.jpg");
        catalogo.setIconImage(icone.getImage());

        // Painel superior com o botão "Voltar" e a label "Catálogo"
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

        // Label "Catálogo"
        JLabel labelCatalogo = new JLabel("Catálogo", JLabel.CENTER);
        labelCatalogo.setFont(new Font("Arial", Font.BOLD, 30)); // Fonte maior e em negrito
        gbc.gridx = 1; // Coluna 1
        gbc.gridy = 0; // Linha 0
        gbc.weightx = 1.0; // Ocupa o espaço restante
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza a label
        padding.add(labelCatalogo, gbc);

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

        tabelaMsc = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaMsc);

        // Adiciona os componentes à janela
        catalogo.add(scrollPane, BorderLayout.CENTER);
        catalogo.add(padding, BorderLayout.NORTH);

        // Carrega as músicas no catálogo
        carregarMusicas();

        catalogo.setVisible(true);
    }

    private void carregarMusicas() {
        Catalogo catalogo = Catalogo.getInstancia();
        tableModel.setRowCount(0); // Limpa a tabela antes de carregar

        if (catalogo.getMusicas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma música disponível no catálogo!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Musica musica : catalogo.getMusicas()) {
                tableModel.addRow(new Object[]{
                        musica.getNome(),
                        musica.getArtista().getNome(),
                        musica.getAlbum().getNome(),
                        musica.getGeneroMusical().toString()
                });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            catalogo.dispose();
            new Menu(menu.getCadastroFrame(), usuario);
        }
    }
}