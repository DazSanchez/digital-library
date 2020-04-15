package me.hsanchez.digital_library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import me.hsanchez.digital_library.dao.queries.UserQueries;
import me.hsanchez.digital_library.datasource.MainDataSource;
import me.hsanchez.digital_library.dto.UserDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class UserDAO {
	private static Logger logger = Logger.getLogger(UserDAO.class.getCanonicalName());

	public UserDTO getUserByUsername(String username) throws QueryExecutionException {
		logger.info("DAO - Start: getUserByUsername");
		QueryRunner queryRunner = new QueryRunner();

		try (Connection connection = MainDataSource.getConnection()) {
			ResultSetHandler<UserDTO> rsh = new BeanHandler<>(UserDTO.class);

			UserDTO user = queryRunner.query(connection, UserQueries.GET_USER_BY_USERNAME, rsh, username);

			logger.info("DAO - End: getUserByUsername");
			return user;
		} catch (SQLException ex) {
			logger.severe("DAO - Error: " + ex.getMessage());
			throw new QueryExecutionException();
		}
	}

}
