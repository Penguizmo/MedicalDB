package com.medicaldb.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
                Properties prop = new Properties();
                if (input == null) {
                    throw new RuntimeException("Sorry, unable to find database.properties");
                }
                prop.load(input);

                String url = "jdbc:mysql://localhost:3306/healthinsurancedb";
                String user = "root";
                String password = "root";

                connection = DriverManager.getConnection(url, user, password);
            } catch (IOException | SQLException e) {
                throw new RuntimeException("Error connecting to database", e);
            }
        }
        return connection;
    }
}
