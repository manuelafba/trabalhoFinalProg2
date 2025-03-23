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
        this.setSize(1080, 720);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(255, 255, 255));

        // Painel superior com o botão de voltar
        JPanel padding = new JPanel(new FlowLayout(FlowLayout.LEFT));
        padding.setBackground(Color.GRAY);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 100));

        voltar = new JButton("Voltar");
        voltar.setPreferredSize(new Dimension(100, 40));
        Font fonteB = voltar.getFont().deriveFont(20f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);
        padding.add(voltar);

        // Tabela de recomendações
        tableModel = new DefaultTableModel();
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