package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    private static Connection connection;
    private static String user = "postgres";
    private static String password = "1234";
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    public static Connection getConnection(){
        try {
            if(connection == null || connection.isClosed() ) {
                connection = DriverManager.getConnection(url, user, password);
            }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return connection;
    }
}
