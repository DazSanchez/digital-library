package me.hsanchez.digital_library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import me.hsanchez.digital_library.dao.queries.GenreQueries;
import me.hsanchez.digital_library.datasource.MainDataSource;
import me.hsanchez.digital_library.dto.GenreDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class GenreDAO {
	private Logger logger = Logger.getLogger(GenreDAO.class.getName());

	public List<GenreDTO> findCoincidences(GenreDTO genre) throws QueryExecutionException {
		logger.info("Dao Start: findGenderCoincidences");

		QueryRunner query = new QueryRunner();

		try (Connection connection = MainDataSource.getConnection()) {
			BeanListHandler<GenreDTO> blh = new BeanListHandler<GenreDTO>(GenreDTO.class);

			List<GenreDTO> results = query.query(connection, GenreQueries.GET_GENRES_BY_NAME, blh,
					"%" + genre.getName() + "%");

			logger.info("Dao End: findGenderCoincidences");
			return results;
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());
			throw new QueryExecutionException();
		}
	}
	
	public void updateGenre(GenreDTO genre) throws QueryExecutionException {
		logger.info("Dao Start: updateGenre");
		try(Connection connection = MainDataSource.getConnection()) {
			QueryRunner runner = new QueryRunner();
			
			int updates = runner.update(connection, GenreQueries.UPDATE_GENRE, genre.getName(), genre.getId());
			
			if(updates == 0) {
				throw new SQLException("No se encontro el genero con id: " + genre.getId());
			}
			
			logger.info("Dao End: updateGenre");
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());
			throw new QueryExecutionException("No se pudo actualizar el genero", e.getMessage());
		}
	}
}
