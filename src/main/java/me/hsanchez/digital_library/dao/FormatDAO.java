package me.hsanchez.digital_library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import me.hsanchez.digital_library.dao.queries.FormatQueries;
import me.hsanchez.digital_library.datasource.MainDataSource;
import me.hsanchez.digital_library.dto.FormatDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class FormatDAO {
	private Logger logger = Logger.getLogger(FormatDAO.class.getName());
	
	public List<FormatDTO> getFormats() throws QueryExecutionException {
		logger.info("Dao Start: getFormats");
		QueryRunner runner = new QueryRunner();
		BeanListHandler<FormatDTO> handler = new BeanListHandler<FormatDTO>(FormatDTO.class);
		
		try(Connection connection = MainDataSource.getConnection()) {
			List<FormatDTO> results = runner.query(connection, FormatQueries.GET_FORMATS, handler);
			
			logger.info("Dao End: getFormats");
			return results;
		} catch (SQLException e) {
			logger.severe("Dao Errro: " + e.getMessage());
			throw new QueryExecutionException("No se han podido obtener los formatos", e.getMessage());
		}
	}
}
