package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Usuario extends JFrame implements ActionListener {

    private JFrame usuario = new JFrame();

    private JButton botaoVoltar;

    public Usuario() {
        usuario.setTitle("Sua Conta"); // Título da janela
        usuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        usuario.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        usuario.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        usuario.setLayout(new BorderLayout()); // Responsividade
        usuario.getContentPane().setBackground(new Color(255,255,255)); // cor janela

        JPanel painelVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelVoltar.setBackground(Color.red);
        painelVoltar.setPreferredSize(new Dimension(100,50));

        botaoVoltar = new JButton();
        botaoVoltar.setPreferredSize(new Dimension(30,30));
        botaoVoltar.addActionListener(this);
        painelVoltar.add(botaoVoltar);

        usuario.add(painelVoltar);

        usuario.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botaoVoltar) {
            usuario.dispose();
            TelaPrincipal telaPrincipal = new TelaPrincipal();
        }
    }
}
