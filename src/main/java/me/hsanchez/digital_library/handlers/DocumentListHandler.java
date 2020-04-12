/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbutils.BasicRowProcessor;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class DocumentListHandler extends BasicRowProcessor{

    @Override
    public <T> T toBean(ResultSet rs, Class<T> type) throws SQLException {
        return null;
    }
    
}
