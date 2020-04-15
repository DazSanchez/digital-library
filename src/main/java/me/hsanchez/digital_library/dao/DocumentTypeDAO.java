package me.hsanchez.digital_library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import me.hsanchez.digital_library.dao.queries.DocumentTypeQueries;
import me.hsanchez.digital_library.datasource.MainDataSource;
import me.hsanchez.digital_library.dto.DocumentTypeDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class DocumentTypeDAO {
	private Logger logger = Logger.getLogger(DocumentTypeDAO.class.getName());

	public List<DocumentTypeDTO> getDocumentTypes() throws QueryExecutionException {
		logger.info("Dao Start: getDocumentTypes");
		QueryRunner runner = new QueryRunner();
		BeanListHandler<DocumentTypeDTO> handler = new BeanListHandler<DocumentTypeDTO>(DocumentTypeDTO.class);

		try (Connection connection = MainDataSource.getConnection()) {
			List<DocumentTypeDTO> results = runner.query(connection, DocumentTypeQueries.GET_DOCUMENT_TYPES, handler);

			logger.info("Dao End: getDocumentTypes");
			return results;
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());
			throw new QueryExecutionException("No se han podido obtener los tipos de documento", e.getMessage());
		}
	}
}
