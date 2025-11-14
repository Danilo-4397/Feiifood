package feifood;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5433/Feiifood";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Danilo12";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Erro na conexão com o banco de dados!");
            return null;
        }
    }
}
