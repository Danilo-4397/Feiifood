package feifood.view;

import feifood.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {
    private Usuario usuarioLogado;
    private JButton btnPedido, btnAvaliar, btnBuscar; // removido btnSair

    public TelaPrincipal(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;

        setTitle("🍔 FEIFood - Menu Principal");
        setSize(500, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // 🔹 Título
        JLabel titulo = new JLabel("Bem-vindo, " + usuarioLogado.getNome() + " 👋", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(200, 50, 50));
        panel.add(titulo, BorderLayout.NORTH);

        // 🔹 Ícone central
        JLabel icone = new JLabel("🍟", SwingConstants.CENTER);
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 70));
        panel.add(icone, BorderLayout.CENTER);

        // 🔹 Painel de botões (AGORA 3 BOTÕES)
        JPanel botoes = new JPanel(new GridLayout(3, 1, 15, 15));
        botoes.setBackground(new Color(245, 245, 245));

        // 🍔 Fazer Pedido
        btnPedido = new JButton("🍔 Fazer Pedido");
        estilizarBotao(btnPedido, new Color(220, 70, 70), Color.WHITE);
        btnPedido.addActionListener(this::abrirTelaPedido);

        // 🔍 Buscar Alimento (NOVO)
        btnBuscar = new JButton("🔍 Buscar Alimento");
        estilizarBotao(btnBuscar, new Color(150, 80, 200), Color.WHITE);
        btnBuscar.addActionListener(e -> abrirTelaBusca());

        // ⭐ Avaliar Pedido
        btnAvaliar = new JButton("⭐ Avaliar Pedido");
        estilizarBotao(btnAvaliar, new Color(255, 200, 60), Color.BLACK);
        btnAvaliar.addActionListener(this::abrirTelaAvaliacao);

        // adicionando na ordem desejada
        botoes.add(btnPedido);
        botoes.add(btnBuscar);
        botoes.add(btnAvaliar);

        panel.add(botoes, BorderLayout.SOUTH);
        add(panel);
    }

    private void estilizarBotao(JButton botao, Color bg, Color fg) {
        botao.setBackground(bg);
        botao.setForeground(fg);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void abrirTelaPedido(ActionEvent e) {
        new TelaPedido(usuarioLogado).setVisible(true);
        dispose();
    }

    private void abrirTelaBusca() {
        new TelaBuscaAlimento().setVisible(true);
    }

    private void abrirTelaAvaliacao(ActionEvent e) {
        new TelaAvaliacao(usuarioLogado).setVisible(true);
    }

    // Para testes
    public static void main(String[] args) {
        Usuario fake = new Usuario();
        fake.setId(1);
        fake.setNome("Danilo");
        SwingUtilities.invokeLater(() -> new TelaPrincipal(fake).setVisible(true));
    }
}
