package me.hsanchez.digital_library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import me.hsanchez.digital_library.dao.queries.AuthorQueries;
import me.hsanchez.digital_library.datasource.MainDataSource;
import me.hsanchez.digital_library.dto.AuthorDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class AuthorDAO {
	private Logger logger = Logger.getLogger(AuthorDAO.class.getCanonicalName());

	public List<AuthorDTO> findAuthorCoincidences(AuthorDTO author) throws QueryExecutionException {
		logger.info("Dao Start: findAuthorCoincidences");
		QueryRunner query = new QueryRunner();

		try (Connection connection = MainDataSource.getConnection()) {
			BeanListHandler<AuthorDTO> blh = new BeanListHandler<AuthorDTO>(AuthorDTO.class);

			List<AuthorDTO> coincidences = query.query(connection, AuthorQueries.GET_AUTHORS_BY_FULL_NAME, blh,
					"%" + author.getName() + "%", "%" + author.getFirstSurname() + "%",
					"%" + author.getSecondSurname() + "%");

			logger.info("Dao End: findAuthorCoincidences");
			return coincidences;
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());
			throw new QueryExecutionException(null, e.getMessage());
		}
	}
}
