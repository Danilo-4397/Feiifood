package feifood.view;

import feifood.dao.AlimentoDAO;
import feifood.dao.PedidoDAO;
import feifood.model.Alimento;
import feifood.model.Pedido;
import feifood.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaPedido extends JFrame {
    private Usuario usuarioLogado;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaAlimentos;
    private JButton btnAdicionar, btnRemover, btnFinalizar, btnAvaliar, btnVoltar;
    private JComboBox<String> comboAlimentos;
    private List<Alimento> alimentos;
    private Pedido pedidoAtual;
    private JLabel lblTotal;

    public TelaPedido(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        this.pedidoAtual = new Pedido();

        setTitle("🍔 FEIFood - Novo Pedido");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponents();
        carregarAlimentos();
    }

    private void initComponents() {
        // 🔹 Painel principal com cor de fundo suave
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // 🔸 Cabeçalho
        JLabel titulo = new JLabel("Monte seu Pedido, " + usuarioLogado.getNome() + " 🍽️", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(200, 50, 50));
        panel.add(titulo, BorderLayout.NORTH);

        // 🔸 Parte superior (combobox e botões)
        JPanel painelSuperior = new JPanel(new BorderLayout(10, 10));
        painelSuperior.setBackground(new Color(245, 245, 245));

        comboAlimentos = new JComboBox<>();
        comboAlimentos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        painelSuperior.add(comboAlimentos, BorderLayout.CENTER);

        btnAdicionar = new JButton("➕ Adicionar");
        btnAdicionar.setBackground(new Color(220, 70, 70));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        painelSuperior.add(btnAdicionar, BorderLayout.EAST);

        panel.add(painelSuperior, BorderLayout.NORTH);

        // 🔹 Lista central com alimentos
        modeloLista = new DefaultListModel<>();
        listaAlimentos = new JList<>(modeloLista);
        listaAlimentos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaAlimentos.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        JScrollPane scroll = new JScrollPane(listaAlimentos);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                "Itens do Pedido",
                0, 0, new Font("Segoe UI", Font.BOLD, 14),
                new Color(100, 100, 100)
        ));
        panel.add(scroll, BorderLayout.CENTER);

        // 🔸 Painel inferior (botões e total)
        JPanel painelInferior = new JPanel(new GridLayout(2, 1, 10, 10));
        painelInferior.setBackground(new Color(245, 245, 245));

        // Label do total
        lblTotal = new JLabel("Total: R$ 0,00", SwingConstants.RIGHT);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setForeground(new Color(50, 100, 50));
        painelInferior.add(lblTotal);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        botoes.setBackground(new Color(245, 245, 245));

        btnRemover = new JButton("❌ Remover");
        btnRemover.setBackground(new Color(240, 240, 240));
        btnRemover.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnFinalizar = new JButton("✅ Finalizar Pedido");
        btnFinalizar.setBackground(new Color(50, 180, 50));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btnAvaliar = new JButton("⭐ Avaliar Pedido");
        btnAvaliar.setBackground(new Color(255, 200, 50));
        btnAvaliar.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btnVoltar = new JButton("↩️ Sair");
        btnVoltar.setBackground(new Color(230, 230, 230));
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 12));

        botoes.add(btnRemover);
        botoes.add(btnFinalizar);
        botoes.add(btnAvaliar);
        botoes.add(btnVoltar);

        painelInferior.add(botoes);
        panel.add(painelInferior, BorderLayout.SOUTH);

        add(panel);

        // 🎯 Eventos
        btnAdicionar.addActionListener(this::adicionarItem);
        btnRemover.addActionListener(this::removerItem);
        btnFinalizar.addActionListener(this::finalizarPedido);
        btnAvaliar.addActionListener(e -> new TelaAvaliacao(usuarioLogado).setVisible(true));
        btnVoltar.addActionListener(e -> voltarTelaLogin());
    }

    private void carregarAlimentos() {
        AlimentoDAO dao = new AlimentoDAO();
        alimentos = dao.listarTodos();
        comboAlimentos.removeAllItems();

        if (alimentos == null || alimentos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Nenhum alimento encontrado no banco!");
            return;
        }

        for (Alimento a : alimentos) {
            comboAlimentos.addItem(a.getNome() + " - R$ " + String.format("%.2f", a.getPreco()));
        }
    }

    private void adicionarItem(ActionEvent e) {
        int index = comboAlimentos.getSelectedIndex();
        if (index >= 0) {
            Alimento selecionado = alimentos.get(index);
            pedidoAtual.adicionarItem(selecionado);
            modeloLista.addElement(selecionado.getNome() + " - R$ " + String.format("%.2f", selecionado.getPreco()));
            atualizarTotal();
        }
    }

    private void removerItem(ActionEvent e) {
        int index = listaAlimentos.getSelectedIndex();
        if (index >= 0) {
            modeloLista.remove(index);
            pedidoAtual.getItens().remove(index);
            atualizarTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para remover!");
        }
    }

    private void atualizarTotal() {
        double total = pedidoAtual.getItens().stream().mapToDouble(Alimento::getPreco).sum();
        lblTotal.setText("Total: R$ " + String.format("%.2f", total));
    }

    private void finalizarPedido(ActionEvent e) {
        if (pedidoAtual.getItens().isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Nenhum item adicionado ao pedido!");
            return;
        }

        pedidoAtual.setIdUsuario(usuarioLogado.getId());
        PedidoDAO dao = new PedidoDAO();

        boolean sucesso = dao.cadastrarPedido(pedidoAtual);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "✅ Pedido cadastrado com sucesso!");
            modeloLista.clear();
            pedidoAtual.getItens().clear();
            atualizarTotal();
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ Erro ao cadastrar pedido!");
        }
    }

    private void voltarTelaLogin() {
        new TelaLogin().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        Usuario fake = new Usuario();
        fake.setId(1);
        fake.setNome("Danilo");
        SwingUtilities.invokeLater(() -> new TelaPedido(fake).setVisible(true));
    }
}
    