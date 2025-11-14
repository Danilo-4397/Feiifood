package feifood.view;

import feifood.dao.UsuarioDAO;
import feifood.model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaCadastroUsuario extends JFrame {
    private JTextField tfNome, tfEmail;
    private JPasswordField pfSenha;
    private JButton btnCadastrar, btnVoltar;

    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuário - FEIFood");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 300);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        JLabel lblTitle = new JLabel("Novo Cadastro", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(lblTitle, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(3,2,8,8));
        center.add(new JLabel("Nome:"));
        tfNome = new JTextField();
        center.add(tfNome);

        center.add(new JLabel("Email:"));
        tfEmail = new JTextField();
        center.add(tfEmail);

        center.add(new JLabel("Senha:"));
        pfSenha = new JPasswordField();
        center.add(pfSenha);

        panel.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnCadastrar = new JButton("Cadastrar");
        btnVoltar = new JButton("Voltar");
        bottom.add(btnCadastrar);
        bottom.add(btnVoltar);
        panel.add(bottom, BorderLayout.SOUTH);

        add(panel);

        btnCadastrar.addActionListener(this::cadastrarUsuario);
        btnVoltar.addActionListener(e -> dispose());
    }

    private void cadastrarUsuario(ActionEvent e) {
        String nome = tfNome.getText().trim();
        String email = tfEmail.getText().trim();
        String senha = new String(pfSenha.getPassword()).trim();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Usuario u = new Usuario();
        u.setNome(nome);
        u.setEmail(email);
        u.setSenha(senha);
        u.setAdmin(false);

        UsuarioDAO dao = new UsuarioDAO();
        if (dao.inserir(u)) {
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário.");
        }
    }
}
