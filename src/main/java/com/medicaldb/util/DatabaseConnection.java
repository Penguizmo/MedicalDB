package com.medicaldb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {} // Private constructor to enforce singleton

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/healthinsurancedb";
                String user = "root";
                String password = "root";
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException("Error connecting to database", e);
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        System.out.println(conn != null ? "Connection successful" : "Connection failed");
    }
}