/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class DatabaseConnection {

    private static Connection connection;
    
    private static boolean checkDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            if(!checkDriver()) {
                throw new SQLException("No driver found");
            }
            
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/digital_library?useSSL=false&allowPublicKeyRetrieval=true",
                "biblioteca_user",
                "biblioteca_user_password"
            );
        }

        return connection;
    }
}
