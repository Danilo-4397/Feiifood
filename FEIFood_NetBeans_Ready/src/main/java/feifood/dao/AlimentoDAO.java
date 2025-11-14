package feifood.dao;

import feifood.ConnectionFactory;
import feifood.model.Alimento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO {

    public List<Alimento> buscarPorNome(String nome) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM alimento WHERE LOWER(nome) LIKE LOWER(?) ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("🔌 Conectando ao banco: " + conn.getMetaData().getURL());
            System.out.println("👤 Usuário: " + conn.getMetaData().getUserName());

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setCategoria(rs.getString("tipo")); // <--- coluna correta
                a.setPreco(rs.getDouble("preco"));
                a.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Alimento> listarTodos() {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM alimento ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("🔌 Conectando ao banco: " + conn.getMetaData().getURL());
            System.out.println("👤 Usuário: " + conn.getMetaData().getUserName());

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setCategoria(rs.getString("tipo")); // <--- coluna correta
                a.setPreco(rs.getDouble("preco"));
                a.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
                lista.add(a);
            }

            System.out.println("✅ " + lista.size() + " alimento(s) carregado(s).");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
