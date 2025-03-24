package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscolhaUsuario extends JFrame implements ActionListener {

    final JFrame escolhaUsuario = new JFrame();

    private JButton botaoUsrNormal = new JButton();
    private JButton botaoUsrPremium = new JButton();
    public String escolhaUsr;

    public EscolhaUsuario() {
        // Configuração da janela
        escolhaUsuario.setTitle("Music Player"); // Título da janela
        escolhaUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        escolhaUsuario.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        escolhaUsuario.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        escolhaUsuario.setLayout(new BorderLayout()); // Responsividade
        escolhaUsuario.getContentPane().setBackground(new Color(255, 255, 255)); // Cor da janela

        // Carrega o ícone
        ImageIcon icone = new ImageIcon("src/main/java/assets/icon.jpg");

        // Redimensiona o ícone para 100x100 pixels
        Image imagemRedimensionada = icone.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon iconeRedimensionado = new ImageIcon(imagemRedimensionada);

        // Define o ícone da janela
        escolhaUsuario.setIconImage(iconeRedimensionado.getImage());

        JPanel painelBorda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBorda.setBackground(Color.white);
        painelBorda.setPreferredSize(new Dimension(100, 100));

        JPanel painelLogo = new JPanel();
        painelLogo.setBackground(Color.white);
        painelLogo.setPreferredSize(new Dimension(100, 80));
        painelLogo.setLayout(new BoxLayout(painelLogo, BoxLayout.Y_AXIS));

        JPanel painelSelecao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSelecao.setBackground(Color.white);
        painelSelecao.setPreferredSize(new Dimension(100, 250));

        botaoUsrNormal = new JButton();
        botaoUsrNormal.setPreferredSize(new Dimension(300, 200));
        botaoUsrNormal.setText("Plano Gratuito");
        Font fonteB = botaoUsrNormal.getFont().deriveFont(35f);
        botaoUsrNormal.setFont(fonteB);
        botaoUsrNormal.setFocusable(false);
        botaoUsrNormal.addActionListener(this);
        painelSelecao.add(botaoUsrNormal);

        botaoUsrPremium = new JButton();
        botaoUsrPremium.setPreferredSize(new Dimension(300, 200));
        botaoUsrPremium.setText("Plano Premium");
        botaoUsrPremium.setFont(fonteB);
        botaoUsrPremium.setFocusable(false);
        botaoUsrPremium.addActionListener(this);
        painelSelecao.add(botaoUsrPremium);

        // Label com o ícone redimensionado
        JLabel iconLabel = new JLabel(iconeRedimensionado);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label "Music Player"
        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteT = mainIcon.getFont().deriveFont(80f);
        mainIcon.setFont(fonteT);

        // Adiciona o ícone e a label ao painelLogo
        painelLogo.add(iconLabel);
        painelLogo.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre o ícone e a label
        painelLogo.add(mainIcon);

        JLabel pergunta = new JLabel("Escolha um tipo de plano: ");
        pergunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteP = pergunta.getFont().deriveFont(30f);
        pergunta.setFont(fonteP);
        painelLogo.add(pergunta);

        escolhaUsuario.add(painelBorda, BorderLayout.NORTH);
        escolhaUsuario.add(painelLogo, BorderLayout.CENTER);
        escolhaUsuario.add(painelSelecao, BorderLayout.SOUTH);

        escolhaUsuario.setVisible(true);
    }

    public void setEscolhaUsr(String escolhaUsr) {
        this.escolhaUsr = escolhaUsr;
    }

    public String getEscolhaUsr() {
        return escolhaUsr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoUsrNormal) {
            setEscolhaUsr("Gratuito");
            escolhaUsuario.dispose();
            new Cadastro(this);
        }
        if (e.getSource() == botaoUsrPremium) {
            setEscolhaUsr("Premium");
            escolhaUsuario.dispose();
            new Cadastro(this);
        }
    }
}