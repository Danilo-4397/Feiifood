package feifood.dao;

import feifood.ConnectionFactory;
import feifood.model.Pedido;
import feifood.model.Alimento;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class PedidoDAO {

    public boolean cadastrarPedido(Pedido pedido) {
        String sqlPedido = "INSERT INTO pedido (id_usuario, data_pedido) VALUES (?, ?) RETURNING id";
        String sqlPedidoAlimento = "INSERT INTO pedido_alimento (id_pedido, id_alimento, quantidade) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmtPedido = null;
        PreparedStatement stmtItens = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            if (conn == null) {
                System.out.println("❌ Falha na conexão com o banco.");
                return false;
            }

            conn.setAutoCommit(false);

            // Insere o pedido principal
            stmtPedido = conn.prepareStatement(sqlPedido);
            stmtPedido.setInt(1, pedido.getIdUsuario());
            stmtPedido.setDate(2, Date.valueOf(LocalDate.now()));
            rs = stmtPedido.executeQuery();

            int idPedidoGerado = -1;
            if (rs.next()) {
                idPedidoGerado = rs.getInt("id");
            }

            // Insere cada alimento no pedido_alimento
            stmtItens = conn.prepareStatement(sqlPedidoAlimento);
            for (Alimento alimento : pedido.getItens()) {
                stmtItens.setInt(1, idPedidoGerado);
                stmtItens.setInt(2, alimento.getId());
                stmtItens.setInt(3, 1); // quantidade padrão 1
                stmtItens.addBatch();
            }
            stmtItens.executeBatch();

            conn.commit();
            System.out.println("✅ Pedido cadastrado com sucesso! ID: " + idPedidoGerado);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;

        } finally {
            try {
                if (rs != null) rs.close();
                if (stmtPedido != null) stmtPedido.close();
                if (stmtItens != null) stmtItens.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
public boolean avaliarPedido(int idPedido, int nota, String comentario) {
    String sql = "UPDATE pedido SET nota_avaliacao = ?, comentario = ? WHERE id = ?";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, nota);
        stmt.setString(2, comentario);
        stmt.setInt(3, idPedido);

        int linhas = stmt.executeUpdate();
        if (linhas > 0) {
            System.out.println("⭐ Pedido " + idPedido + " avaliado com nota " + nota);
            return true;
        } else {
            System.out.println("⚠️ Pedido não encontrado para avaliação.");
            return false;
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

  
}
