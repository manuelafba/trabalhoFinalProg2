package frames;

import models.Musica;
import musicPlayer.Catalogo;
import users.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameCatalogo extends JFrame implements ActionListener {

    final JFrame catalogo = new JFrame();
    private JButton voltar = new JButton();
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
        catalogo.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        catalogo.setLayout(new BorderLayout()); // Responsividade
        catalogo.getContentPane().setBackground(new Color(255,255,255));// cor janela

        JPanel padding = new JPanel(new FlowLayout(FlowLayout.LEFT));
        padding.setBackground(Color.GRAY);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 50));

        voltar.setPreferredSize(new Dimension(40,40));
        Font fonteB = voltar.getFont().deriveFont(35f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);
        padding.add(voltar);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Música");
        tableModel.addColumn("Artista");
        tableModel.addColumn("Álbum");
        tableModel.addColumn("Gênero");

        tabelaMsc = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaMsc);

        catalogo.add(scrollPane, BorderLayout.CENTER);
        catalogo.add(padding, BorderLayout.NORTH);

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
        if(e.getSource()==voltar) {
            catalogo.dispose();

        }
    }
}
