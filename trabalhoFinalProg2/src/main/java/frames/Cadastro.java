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
    public JTextField inputNome = new JTextField(20); // Aumentei o número de colunas
    private EscolhaUsuario escolhaUsuarioFrame;

    public Cadastro(EscolhaUsuario escolhaUsuarioFrame) {
        this.escolhaUsuarioFrame = escolhaUsuarioFrame;

        // Configuração da janela
        cadastro.setTitle("Music Player"); // Título da janela
        cadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        cadastro.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicializa o programa em tela cheia
        cadastro.setSize(1080, 720); // Tamanho padrão caso o programa seja minimizado
        cadastro.setLayout(new BorderLayout()); // Responsividade
        cadastro.getContentPane().setBackground(new Color(255, 255, 255));// cor janela

        // Painel superior (NORTH)
        JPanel painelTop = new JPanel(new BorderLayout()); // Usando BorderLayout para o painel principal
        painelTop.setBackground(Color.white);
        painelTop.setPreferredSize(new Dimension(100, 300)); // Aumentei a altura

        // Painel interno para centralizar as labels
        JPanel painelInterno = new JPanel(new GridBagLayout()); // Usando GridBagLayout para centralização
        painelInterno.setOpaque(false); // Fundo transparente

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        JLabel mainIcon = new JLabel("Music Player", JLabel.CENTER);
        mainIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteT = mainIcon.getFont().deriveFont(80f);
        mainIcon.setFont(fonteT);
        painelInterno.add(mainIcon, gbc);

        gbc.gridy = 1; // Próxima linha
        JLabel descricao = new JLabel("Insira aqui seu nome: ", JLabel.CENTER);
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fonteA = descricao.getFont().deriveFont(35f);
        descricao.setFont(fonteA);
        painelInterno.add(descricao, gbc);

        painelTop.add(painelInterno, BorderLayout.CENTER); // Adiciona o painel interno ao painelTop

        // Painel central (CENTER)
        JPanel painelTexto = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelTexto.setBackground(Color.white);
        painelTexto.setPreferredSize(new Dimension(100, 100));

        inputNome.setPreferredSize(new Dimension(400, 40)); // Aumentei o tamanho do JTextField
        painelTexto.add(inputNome);

        // Painel inferior (SOUTH)
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(Color.white);
        painelBotao.setPreferredSize(new Dimension(100, 300)); // Aumentei a altura

        prosseguir.setPreferredSize(new Dimension(300, 80));
        prosseguir.setText("Prosseguir");
        Font fonteB = prosseguir.getFont().deriveFont(35f);
        prosseguir.setFont(fonteB);
        prosseguir.setFocusable(false);
        prosseguir.addActionListener(this);
        painelBotao.add(prosseguir);

        // Adiciona os painéis à janela
        cadastro.add(painelTop, BorderLayout.NORTH);
        cadastro.add(painelTexto, BorderLayout.CENTER);
        cadastro.add(painelBotao, BorderLayout.SOUTH);

        cadastro.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prosseguir) {
            String nomeUsuario = inputNome.getText().trim();
            Usuario usuario;

            if (nomeUsuario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O seu nome não pode ser vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (escolhaUsuarioFrame.getEscolhaUsr().equals("Comum")) {
                usuario = new UsuarioGratuito(nomeUsuario);
            } else {
                usuario = new UsuarioPremium(nomeUsuario);
            }

            cadastro.dispose();
            new Menu(this, usuario);
        }
    }
}