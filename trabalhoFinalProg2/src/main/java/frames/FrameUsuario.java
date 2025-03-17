package frames;

import users.Usuario;
import users.UsuarioGratuito;
import users.UsuarioPremium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameUsuario extends JFrame implements ActionListener {

    final JFrame frameUsuario = new JFrame();
    private Menu menu;
    private Usuario usuario;

    private JButton voltar = new JButton();

    public FrameUsuario(Menu menu, Usuario usuario) {
        this.menu = menu;
        this.usuario = usuario;

        // Configuração da janela
        frameUsuario.setTitle("Music Player"); // Título da janela
        frameUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        frameUsuario.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        frameUsuario.setSize(1080,720); // Tamanho padrão caso o programa seja minimizado
        frameUsuario.setLayout(new BorderLayout()); // Responsividade
        frameUsuario.getContentPane().setBackground(new Color(255,255,255));// cor janela

        JPanel padding = new JPanel(new FlowLayout(FlowLayout.LEFT));
        padding.setBackground(Color.GRAY);
        padding.setPreferredSize(new Dimension(padding.getWidth(), 100));

        JPanel painelIcon = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelIcon.setBackground(Color.green);
        painelIcon.setPreferredSize(new Dimension(100,200));

        JPanel painelTextos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelTextos.setBackground(Color.red);
        painelTextos.setPreferredSize(new Dimension(100,400));
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));

        String nomeUsr = this.usuario.getNome();
        String tipoConta = this.usuario.getTipoUsuario();

        JLabel usr = new JLabel("Nome: " + nomeUsr, JLabel.CENTER);
        usr.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteA = usr.getFont().deriveFont(55f);
        usr.setFont(fonteA);
        painelTextos.add(usr);

        JLabel conta = new JLabel("Plano: " + tipoConta, JLabel.CENTER);
        conta.setAlignmentX(Component.CENTER_ALIGNMENT);
        conta.setFont(fonteA);
        painelTextos.add(conta);

        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteT = mainIcon.getFont().deriveFont(80f);
        mainIcon.setFont(fonteT);
        painelIcon.add(mainIcon);

        voltar.setPreferredSize(new Dimension(40,40));
        Font fonteB = voltar.getFont().deriveFont(35f);
        voltar.setFont(fonteB);
        voltar.setFocusable(false);
        voltar.addActionListener(this);
        padding.add(voltar);

        frameUsuario.add(padding, BorderLayout.NORTH);
        frameUsuario.add(painelIcon, BorderLayout.CENTER);
        frameUsuario.add(painelTextos, BorderLayout.SOUTH);

        frameUsuario.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==voltar) {
            frameUsuario.dispose();
            new Menu(menu.getCadastroFrame(), usuario);

        }
    }


}
