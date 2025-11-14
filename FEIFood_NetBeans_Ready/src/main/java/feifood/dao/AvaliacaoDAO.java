package feifood.dao;

import feifood.ConnectionFactory;
import feifood.model.Avaliacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {

    public boolean inserir(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao (id_pedido, nota, comentario) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, avaliacao.getIdPedido());
            ps.setInt(2, avaliacao.getNota());
            ps.setString(3, avaliacao.getComentario());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public List<Avaliacao> listarPorUsuario(int idUsuario) {
        List<Avaliacao> lista = new ArrayList<>();
        String sql;
        sql = "SELECT a.id, a.id_pedido, a.nota, a.comentario\n" + "FROM avaliacao a\n" + "JOIN pedido p ON a.id_pedido = p.id\n" + "WHERE p.id_usuario = ?\n" + "ORDER BY a.id DESC\n";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Avaliacao av = new Avaliacao();
                av.setId(rs.getInt("id"));
                av.setIdPedido(rs.getInt("id_pedido"));
                av.setNota(rs.getInt("nota"));
                av.setComentario(rs.getString("comentario"));
                lista.add(av);
            }

        } catch (SQLException e) {
        }

        return lista;
    }
}
