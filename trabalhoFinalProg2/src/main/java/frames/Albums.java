package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Albums extends JFrame implements ActionListener {

    private JFrame albums = new JFrame();

    private JButton botaoVoltar;

    public Albums() {
        albums.setTitle("Meus Álbums"); // Título da janela
        albums.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        albums.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        albums.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        albums.setLayout(new BorderLayout()); // Responsividade
        albums.getContentPane().setBackground(new Color(255,255,255)); // cor janela

        JPanel painelVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelVoltar.setBackground(Color.red);
        painelVoltar.setPreferredSize(new Dimension(100,50));

        botaoVoltar = new JButton();
        botaoVoltar.setPreferredSize(new Dimension(30,30));
        botaoVoltar.addActionListener(this);
        painelVoltar.add(botaoVoltar);

        albums.add(painelVoltar);

        albums.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botaoVoltar) {
            albums.dispose();
            TelaPrincipal telaPrincipal = new TelaPrincipal();
        }
    }
}
