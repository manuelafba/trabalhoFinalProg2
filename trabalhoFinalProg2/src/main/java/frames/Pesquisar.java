package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pesquisar extends JFrame implements ActionListener {

    private JFrame pesquisar = new JFrame();

    private JButton botaoVoltar;

    public Pesquisar() {
        pesquisar.setTitle("Pesquisar Músicas"); // Título da janela
        pesquisar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        pesquisar.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        pesquisar.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        pesquisar.setLayout(new BorderLayout()); // Responsividade
        pesquisar.getContentPane().setBackground(new Color(255,255,255)); // cor janela

        JPanel painelVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelVoltar.setBackground(Color.red);
        painelVoltar.setPreferredSize(new Dimension(100,50));

        botaoVoltar = new JButton();
        botaoVoltar.setPreferredSize(new Dimension(30,30));
        botaoVoltar.addActionListener(this);
        painelVoltar.add(botaoVoltar);

        pesquisar.add(painelVoltar);

        pesquisar.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botaoVoltar) {
            pesquisar.dispose();
            TelaPrincipal telaPrincipal = new TelaPrincipal();
        }
    }
}
