package feifood.view;

import feifood.dao.AlimentoDAO;
import feifood.model.Alimento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaBuscaAlimento extends JFrame {

    private JTextField tfBusca;
    private JButton btnBuscar, btnListarTodos, btnVoltar;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaBuscaAlimento() {
        setTitle("Buscar Alimentos - FEIFood");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 400);
        setLocationRelativeTo(null);
        initComponents();
        listarTodos(); // carrega tudo ao abrir
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("🍔 Busca de Alimentos - FEIFood", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // ----- TOPO (busca) -----
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        tfBusca = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        btnListarTodos = new JButton("Listar Todos");
        btnVoltar = new JButton("Voltar");

        topPanel.add(new JLabel("Nome:"));
        topPanel.add(tfBusca);
        topPanel.add(btnBuscar);
        topPanel.add(btnListarTodos);
        topPanel.add(btnVoltar);
        panel.add(topPanel, BorderLayout.NORTH);

        // ----- TABELA -----
        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Categoria", "Preço (R$)", "Estabelecimento"},
                0
        );
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        // ----- EVENTOS -----
        btnBuscar.addActionListener(this::buscarAlimento);
        btnListarTodos.addActionListener(e -> listarTodos());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void buscarAlimento(ActionEvent e) {
        String termo = tfBusca.getText().trim();

        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do alimento para buscar.");
            return;
        }

        AlimentoDAO dao = new AlimentoDAO();
        List<Alimento> resultados = dao.buscarPorNome(termo);

        modeloTabela.setRowCount(0); // limpa tabela

        System.out.println("🔍 Buscando alimentos com termo: " + termo);
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum alimento encontrado para: " + termo);
        } else {
            for (Alimento a : resultados) {
                modeloTabela.addRow(new Object[]{
                        a.getId(),
                        a.getNome(),
                        a.getCategoria(),
                        a.getPreco(),
                        a.getIdEstabelecimento()
                });
            }
            System.out.println("✅ " + resultados.size() + " alimento(s) encontrado(s).");
        }
    }

    private void listarTodos() {
        AlimentoDAO dao = new AlimentoDAO();
        List<Alimento> alimentos = dao.listarTodos();

        modeloTabela.setRowCount(0);

        if (alimentos == null || alimentos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum alimento encontrado no banco de dados.");
            System.out.println("⚠️ Nenhum alimento encontrado no banco.");
        } else {
            for (Alimento a : alimentos) {
                modeloTabela.addRow(new Object[]{
                        a.getId(),
                        a.getNome(),
                        a.getCategoria(),
                        a.getPreco(),
                        a.getIdEstabelecimento()
                });
            }
            System.out.println("✅ " + alimentos.size() + " alimento(s) carregado(s) da base de dados.");
        }
    }

    public static void main(String[] args) {
        // 🔧 Teste rápido de DAO e conexão
        System.out.println("🔌 Testando busca no banco...");
        AlimentoDAO dao = new AlimentoDAO();
        List<Alimento> teste = dao.listarTodos();

        if (teste == null || teste.isEmpty()) {
            System.out.println("⚠️ Nenhum alimento retornado! Verifique a tabela ou a conexão.");
        } else {
            teste.forEach(a -> System.out.println(
                    a.getId() + " - " + a.getNome() + " - " + a.getCategoria() + " - R$ " + a.getPreco()
            ));
        }

        SwingUtilities.invokeLater(() -> new TelaBuscaAlimento().setVisible(true));
    }
}
