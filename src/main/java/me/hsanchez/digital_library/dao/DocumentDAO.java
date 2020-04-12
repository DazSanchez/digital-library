/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import me.hsanchez.digital_library.config.DatabaseConnection;
import me.hsanchez.digital_library.dto.DocumentDTO;
import org.apache.commons.dbutils.QueryRunner;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class DocumentDAO {
    public List<DocumentDTO> getDocumentsBy(int type, String title) {
        try(Connection connection = DatabaseConnection.getConnection()) {
            QueryRunner queryRunner = new QueryRunner();
            
            queryRunner.query(connection, "", null);
            
            return null;
        } catch(SQLException ex) {
            return new ArrayList<>();
        }
    }
}
