package frames;

import users.Usuario;
import users.UsuarioGratuito;
import users.UsuarioPremium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cadastro extends JFrame implements ActionListener {

    final JFrame cadastro = new JFrame();
    private JButton prosseguir = new JButton();
    public JTextField inputNome = new JTextField(5);
    private EscolhaUsuario escolhaUsuarioFrame;

    public Cadastro(EscolhaUsuario escolhaUsuarioFrame) {
        this.escolhaUsuarioFrame = escolhaUsuarioFrame;

        // Configuração da janela
        cadastro.setTitle("Music Player"); // Título da janela
        cadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        cadastro.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        cadastro.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        cadastro.setLayout(new BorderLayout()); // Responsividade
        cadastro.getContentPane().setBackground(new Color(255,255,255));// cor janela

        JPanel painelTop = new JPanel();
        painelTop.setBackground(Color.red);
        painelTop.setPreferredSize(new Dimension(100,200));
        painelTop.setLayout(new BoxLayout(painelTop, BoxLayout.Y_AXIS));

        JPanel painelTexto = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelTexto.setBackground(Color.green);
        painelTexto.setPreferredSize(new Dimension(100,80));
        painelTexto.setLayout(new BoxLayout(painelTexto, BoxLayout.Y_AXIS));

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(Color.blue);
        painelBotao.setPreferredSize(new Dimension(100,250));

        JPanel texto = new JPanel();
        texto.setPreferredSize(new Dimension(300,50));

        inputNome.setPreferredSize(new Dimension(100,30));
        texto.add(inputNome);
        painelTexto.add(texto, Component.CENTER_ALIGNMENT);

        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteT = mainIcon.getFont().deriveFont(80f);
        mainIcon.setFont(fonteT);
        painelTop.add(mainIcon);

        JLabel descricao = new JLabel("Insira aqui seu nome: ", JLabel.CENTER);
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteA = descricao.getFont().deriveFont(35f);
        descricao.setFont(fonteA);
        painelTop.add(descricao);

        prosseguir.setPreferredSize(new Dimension(300,80));
        prosseguir.setText("Prosseguir");
        Font fonteB = prosseguir.getFont().deriveFont(35f);
        prosseguir.setFont(fonteB);
        prosseguir.setFocusable(false);
        prosseguir.addActionListener(this);
        painelBotao.add(prosseguir);

        cadastro.add(painelTop, BorderLayout.NORTH);
        cadastro.add(painelTexto, BorderLayout.CENTER);
        cadastro.add(painelBotao, BorderLayout.SOUTH);

        cadastro.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==prosseguir) {
            String nomeUsuario = inputNome.getText().trim();
            Usuario usuario;

            if(nomeUsuario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O seu nome não pode ser vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(escolhaUsuarioFrame.getEscolhaUsr().equals("Comum")) {
                usuario = new UsuarioGratuito(nomeUsuario);
            } else {
                usuario = new UsuarioPremium(nomeUsuario);
            }

            cadastro.dispose();
            new Menu(this, usuario);
        }
    }

}
