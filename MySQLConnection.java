//BIBLIOTECAS
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//CONEXÃO COM BANCO DE DADOS
public class MySQLConnection {
    private static final String URL = "";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}