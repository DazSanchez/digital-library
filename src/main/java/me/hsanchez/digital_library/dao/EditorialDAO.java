package me.hsanchez.digital_library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import me.hsanchez.digital_library.dao.queries.EditorialQueries;
import me.hsanchez.digital_library.datasource.MainDataSource;
import me.hsanchez.digital_library.dto.EditorialDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class EditorialDAO {
	private Logger logger = Logger.getLogger(EditorialDAO.class.getName());

	public List<EditorialDTO> getEditorialCoincidences(EditorialDTO editorial) throws QueryExecutionException {
		logger.info("Dao Start: getEditorialCoincidences");
		QueryRunner query = new QueryRunner();

		try (Connection connection = MainDataSource.getConnection()) {
			BeanListHandler<EditorialDTO> blh = new BeanListHandler<EditorialDTO>(EditorialDTO.class);

			List<EditorialDTO> results = query.query(connection, EditorialQueries.GET_EDITORIALS_BY_NAME, blh,
					"%" + editorial.getName() + "%");

			logger.info("Dao Start: getEditorialCoincidences");
			return results;
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());
			throw new QueryExecutionException(null, e.getMessage());
		}
	}

	public void updateEditorial(EditorialDTO editorial) throws QueryExecutionException {
		logger.info("Dao Start: updateEditorial");
		try (Connection connection = MainDataSource.getConnection()) {
			QueryRunner runner = new QueryRunner();

			int updates = runner.update(connection, EditorialQueries.UPDATE_EDITORIAL, editorial.getName(), editorial.getId());

			if (updates == 0) {
				throw new SQLException("No se encontro la editorial con id: " + editorial.getId());
			}

			logger.info("Dao End: updateEditorial");
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());
			throw new QueryExecutionException("No se pudo actualizar la editorial", e.getMessage());
		}
	}
}
