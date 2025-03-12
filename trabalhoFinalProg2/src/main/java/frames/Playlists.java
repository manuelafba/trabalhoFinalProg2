package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Playlists extends JFrame implements ActionListener {

    private JFrame playlists = new JFrame();

    private JButton botaoVoltar;

    public Playlists() {
        playlists.setTitle("Minhas Playlists"); // Título da janela
        playlists.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        playlists.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        playlists.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        playlists.setLayout(new BorderLayout()); // Responsividade
        playlists.getContentPane().setBackground(new Color(255,255,255)); // cor janela

        JPanel painelVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelVoltar.setBackground(Color.red);
        painelVoltar.setPreferredSize(new Dimension(100,50));

        botaoVoltar = new JButton();
        botaoVoltar.setPreferredSize(new Dimension(30,30));
        botaoVoltar.addActionListener(this);
        painelVoltar.add(botaoVoltar);

        playlists.add(painelVoltar);

        playlists.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botaoVoltar) {
            playlists.dispose();
            TelaPrincipal telaPrincipal = new TelaPrincipal();
        }
    }
}
