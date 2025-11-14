package feifood.view;

import feifood.dao.PedidoDAO;
import feifood.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelaAvaliacao extends JFrame {
    private Usuario usuarioLogado;
    private JComboBox<String> comboPedidos;
    private JSlider sliderNota;
    private JTextArea txtComentario;
    private JButton btnSalvar;
    private List<Integer> idsPedidos = new ArrayList<>();

    public TelaAvaliacao(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;

        setTitle("⭐ Avaliar Pedido - FEIFood");
        setSize(480, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        carregarPedidosUsuario();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Avalie seu pedido, " + usuarioLogado.getNome() + " ⭐", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(4, 1, 10, 10));

        comboPedidos = new JComboBox<>();
        center.add(new JLabel("Selecione o Pedido:"));
        center.add(comboPedidos);

        sliderNota = new JSlider(1, 5, 3);
        sliderNota.setMajorTickSpacing(1);
        sliderNota.setPaintTicks(true);
        sliderNota.setPaintLabels(true);
        center.add(new JLabel("Escolha a nota (1 a 5):"));
        center.add(sliderNota);

        txtComentario = new JTextArea(4, 20);
        txtComentario.setBorder(BorderFactory.createTitledBorder("Comentário (opcional):"));

        btnSalvar = new JButton("Salvar Avaliação");

        panel.add(center, BorderLayout.CENTER);
        panel.add(txtComentario, BorderLayout.SOUTH);
        panel.add(btnSalvar, BorderLayout.PAGE_END);

        add(panel);

        btnSalvar.addActionListener(this::salvarAvaliacao);
    }

    private void carregarPedidosUsuario() {
        idsPedidos.clear();
        comboPedidos.removeAllItems();

        try (Connection conn = feifood.ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id FROM pedido WHERE id_usuario = ? ORDER BY id DESC")) {
            stmt.setInt(1, usuarioLogado.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                idsPedidos.add(id);
                comboPedidos.addItem("Pedido #" + id);
            }

            if (idsPedidos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Você ainda não fez nenhum pedido.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarAvaliacao(ActionEvent e) {
        int index = comboPedidos.getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para avaliar!");
            return;
        }

        int idPedido = idsPedidos.get(index);
        int nota = sliderNota.getValue();
        String comentario = txtComentario.getText();

        PedidoDAO dao = new PedidoDAO();
        boolean sucesso = dao.avaliarPedido(idPedido, nota, comentario);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "✅ Avaliação salva com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Erro ao salvar avaliação!");
        }
    }

    public static void main(String[] args) {
        Usuario fake = new Usuario();
        fake.setId(1);
        fake.setNome("Danilo");
        SwingUtilities.invokeLater(() -> new TelaAvaliacao(fake).setVisible(true));
    }
}
