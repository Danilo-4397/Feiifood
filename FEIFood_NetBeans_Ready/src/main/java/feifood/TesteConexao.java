package feifood;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        System.out.println("🔍 Testando conexão com o PostgreSQL...");

        try (Connection conn = ConnectionFactory.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Conexão estabelecida com sucesso!");
            } else {
                System.out.println("❌ Conexão retornou nula.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Falha na conexão com o banco de dados:");
        }
    }
}

