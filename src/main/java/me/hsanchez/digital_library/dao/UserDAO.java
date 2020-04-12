package me.hsanchez.digital_library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import me.hsanchez.digital_library.config.DatabaseConnection;
import me.hsanchez.digital_library.dto.UserDTO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class UserDAO {

    public UserDTO getUserByUsername(String username) {
        System.out.println("DAO - Start: getUserByUsername");
        QueryRunner queryRunner = new QueryRunner();

        try (Connection connection = DatabaseConnection.getConnection()) {
            ResultSetHandler<UserDTO> rsh = new BeanHandler<>(UserDTO.class);

            UserDTO user = queryRunner.query(
                    connection,
                    "SELECT USERNAME as username, PASSWORD as password FROM USERS WHERE USERNAME = ?",
                    rsh,
                    username
            );

            System.out.println("DAO - End: getUserByUsername");
            return user;
        } catch (SQLException ex) {
            System.err.println("DAO - Error: " + ex.getMessage());
            return null;
        }
    }

}
    