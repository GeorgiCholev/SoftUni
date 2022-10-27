package _1_Intro.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db";
    private static final String PROPERTY_USERNAME = "root";
    private static final String PROPERTY_PASSWORD = "";


    public static Connection connect() throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("username", PROPERTY_USERNAME);
        properties.setProperty("password", PROPERTY_PASSWORD);

        return DriverManager.getConnection(URL, properties);
    }
}
