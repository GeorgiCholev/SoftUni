package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private static Connection connection;

    private static String connectionURL = "jdbc:mysql://localhost:3306/";

    private Connector() {}

    public static void createConnection(String username, String password, String dbName) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        String url = connectionURL + dbName;
        connection = DriverManager.getConnection(url, properties);
    }

    public static Connection getConnection() {
        return connection;
    }
}
