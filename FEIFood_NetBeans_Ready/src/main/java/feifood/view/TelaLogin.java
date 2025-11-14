package feifood.view;

import feifood.dao.UsuarioDAO;
import feifood.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {
    private JTextField tfEmail;
    private JPasswordField pfSenha;
    private JButton btnLogin, btnCadastro;
    private JLabel lblTitulo;

    public TelaLogin() {
        setTitle("🍔 FEIFood - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 420);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        // Painel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Título
        lblTitulo = new JLabel("Bem-vindo ao FEIFood", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(200, 50, 50));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Painel central (inputs)
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 3, 3));
        centerPanel.setBackground(new Color(245, 245, 245));

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tfEmail = new JTextField();
        estilizarCampo(tfEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pfSenha = new JPasswordField();
        estilizarCampo(pfSenha);

        centerPanel.add(lblEmail);
        centerPanel.add(tfEmail);
        centerPanel.add(lblSenha);
        centerPanel.add(pfSenha);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Painel inferior (botões)
        JPanel bottomPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        bottomPanel.setBackground(new Color(245, 245, 245));

        btnLogin = new JButton("Entrar");
        estilizarBotao(btnLogin, new Color(220, 70, 70), Color.WHITE);
        btnLogin.addActionListener(this::fazerLogin);

        btnCadastro = new JButton("Cadastrar Novo Usuário");
        estilizarBotao(btnCadastro, new Color(255, 200, 80), Color.BLACK);
        btnCadastro.addActionListener(this::abrirCadastro);

        bottomPanel.add(btnLogin);
        bottomPanel.add(btnCadastro);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
    }

    // Inputs com texto visível (padding reduzido)
    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 16)); 
        campo.setForeground(Color.DARK_GRAY);
        campo.setBackground(Color.WHITE);
        campo.setCaretColor(Color.BLACK);

        // Padding corrigido
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                campo.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220, 70, 70), 2, true),
                        BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                campo.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                        BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
            }
        });

        campo.setPreferredSize(new Dimension(campo.getPreferredSize().width, 35));
    }

    private void estilizarBotao(JButton botao, Color bg, Color fg) {
        botao.setBackground(bg);
        botao.setForeground(fg);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void fazerLogin(ActionEvent e) {
        String email = tfEmail.getText().trim();
        String senha = new String(pfSenha.getPassword()).trim();

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.autenticar(email, senha);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "✔ Login realizado com sucesso!");
            new TelaPrincipal(usuario).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "❌ E-mail ou senha incorretos!");
        }
    }

    private void abrirCadastro(ActionEvent e) {
        new TelaCadastroUsuario().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}

