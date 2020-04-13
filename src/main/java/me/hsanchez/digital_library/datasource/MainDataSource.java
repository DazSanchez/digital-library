/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class MainDataSource {

    private static Connection CONNECTION = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No suitable driver found");
        }
        
        boolean isProduction = Boolean.parseBoolean(System.getenv("PROD"));

        if (CONNECTION == null || CONNECTION.isClosed()) {
            CONNECTION = isProduction ? getGCPConnection() : getLocalConnection();
        }

        return CONNECTION;
    }

    private static Connection getGCPConnection() throws SQLException {
        System.out.println("DATASOURCE: Connected with GCP");
        String url = String.format(
                "jdbc:mysql:///%s?cloudSqlInstance=%s&socketFactory=%s&useSSL=false",
                System.getenv("DB_NAME"),
                System.getenv("CLOUD_SQL_CONNECTION_NAME"),
                "com.google.cloud.sql.mysql.SocketFactory"
        );

        return DriverManager.getConnection(url, System.getenv("DB_USER"), System.getenv("DB_PASS"));
    }

    private static Connection getLocalConnection() throws SQLException {
        System.out.println("DATASOURCE: Connected with Local");
        String url = String.format("jdbc:mysql://127.0.0.1:3306/%s?useSSL=false", System.getenv("DB_NAME"));

        return DriverManager.getConnection(url, System.getenv("DB_USER"), System.getenv("DB_PASS"));
    }
}
