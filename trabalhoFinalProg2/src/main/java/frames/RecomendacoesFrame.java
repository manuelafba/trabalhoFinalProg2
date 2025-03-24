package frames;

import models.Musica;
import musicPlayer.Catalogo;
import musicPlayer.Recomendacoes;
import users.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecomendacoesFrame extends JFrame implements ActionListener {

    private Menu menu;
    private Usuario usuario;
    private Catalogo catalogo;
    private Recomendacoes recomendacoes;

    private JButton voltar;
    private JTable tabelaRecomendacoes;
    private DefaultTableModel tableModel;

    public RecomendacoesFrame(Menu menu, Usuario usuario, Catalogo catalogo) {
        this.menu = menu;
        this.usuario = usuario;
        this.catalogo = catalogo;
        this.recomendacoes = new Recomendacoes(catalogo);

        // Configuração da janela
        this.setTitle("Music Player");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        this.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(255, 255, 255));

        ImageIcon icone = new ImageIcon("src/main/java/assets/icon.jpg");
        this.setIconImage(icone.getImage());

        // Painel superior com o botão "Voltar" e a label "Recomendações"
        JPanel padding = new JPanel(new GridBagLayout()); // Usando GridBagLayout para posicionamento preciso
        padding.setBackground(Color.white);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 70));

        // Configuração do GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10); // Espaçamento entre os componentes

        // Botão "Voltar"
        voltar = new JButton("Voltar");
        voltar.setPreferredSize(new Dimension(100, 40));
        Font fonteB = voltar.getFont().deriveFont(20f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);

        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 0; // Linha 0
        gbc.anchor = GridBagConstraints.WEST; // Alinha à esquerda
        padding.add(voltar, gbc);

        // Label "Recomendações"
        JLabel labelRecomendacoes = new JLabel("Recomendações", JLabel.CENTER);
        labelRecomendacoes.setFont(new Font("Arial", Font.BOLD, 30)); // Fonte maior e em negrito
        gbc.gridx = 1; // Coluna 1
        gbc.gridy = 0; // Linha 0
        gbc.weightx = 1.0; // Ocupa o espaço restante
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza a label
        padding.add(labelRecomendacoes, gbc);

        // Tabela de recomendações
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("Música");
        tableModel.addColumn("Artista");
        tableModel.addColumn("Álbum");

        tabelaRecomendacoes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaRecomendacoes);

        // Adiciona os componentes à janela
        this.add(padding, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        carregarRecomendacoes();
        this.setVisible(true);
    }

    // Carrega as músicas recomendadas na tabela
    private void carregarRecomendacoes() {
        tableModel.setRowCount(0); // Limpa a tabela

        ArrayList<Musica> recomendacoes = this.recomendacoes.recomendarMusicas(usuario);
        for (Musica musica : recomendacoes) {
            tableModel.addRow(new Object[]{
                    musica.getNome(),
                    musica.getArtista().getNome(),
                    musica.getAlbum().getNome()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            this.dispose(); // Fecha a janela atual
            new Menu(menu.getCadastroFrame(), usuario);
        }
    }
}