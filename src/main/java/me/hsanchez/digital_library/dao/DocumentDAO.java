/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import me.hsanchez.digital_library.dao.queries.AuthorQueries;
import me.hsanchez.digital_library.dao.queries.DeliveryTimeQueries;
import me.hsanchez.digital_library.dao.queries.DocumentQueries;
import me.hsanchez.digital_library.dao.queries.EditorialQueries;
import me.hsanchez.digital_library.dao.queries.GenreQueries;
import me.hsanchez.digital_library.datasource.MainDataSource;
import me.hsanchez.digital_library.dto.AuthorDTO;
import me.hsanchez.digital_library.dto.DeliveryTimeDTO;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.DocumentTypeDTO;
import me.hsanchez.digital_library.dto.EditorialDTO;
import me.hsanchez.digital_library.dto.FormatDTO;
import me.hsanchez.digital_library.dto.GenreDTO;
import me.hsanchez.digital_library.dto.SearchResultDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.handlers.DocumentHandler;
import me.hsanchez.digital_library.handlers.SearchResultHandler;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class DocumentDAO {
	private Logger logger = Logger.getLogger(DocumentDAO.class.getName());

	public SearchResultDTO<DocumentDTO> getDocumentsBy(int type, String title, int page, int perPage)
			throws QueryExecutionException {
		logger.info("Dao Start: getDocumentsBy");
		try (Connection connection = MainDataSource.getConnection()) {
			QueryRunner queryRunner = new QueryRunner();

			int offset = (page - 1) * perPage;

			Long total = queryRunner.query(connection, DocumentQueries.COUNT_DOCUMENTS, new ScalarHandler<Long>(), type,
					"%" + title + "%");

			List<DocumentDTO> documents = queryRunner.query(connection, DocumentQueries.GET_DOCUMENTS,
					new SearchResultHandler(), type, "%" + title + "%", offset, perPage);

			logger.info("Dao End: getDocumentsBy");

			SearchResultDTO<DocumentDTO> resultDTO = new SearchResultDTO<DocumentDTO>();
			resultDTO.setResults(documents);
			resultDTO.setTotal(total);

			return resultDTO;
		} catch (SQLException ex) {
			logger.severe("Dao Error: " + ex.getMessage());
			throw new QueryExecutionException();
		}
	}

	public Integer saveDocument(DocumentDTO document) throws QueryExecutionException {
		logger.info("Dao Start: saveDocument");
		QueryRunner query = new QueryRunner();
		Connection connection = null;

		try {
			connection = MainDataSource.getConnection();
		} catch (SQLException e) {
			throw new QueryExecutionException();
		}

		try {
			connection.setAutoCommit(false);

			ScalarHandler<BigInteger> handler = new ScalarHandler<>();

			AuthorDTO author = document.getAuthor();
			GenreDTO genre = document.getGenre();
			DeliveryTimeDTO deliveryTime = document.getDeliveryTime();
			EditorialDTO editorial = document.getEditorial();
			FormatDTO format = document.getFormat();
			DocumentTypeDTO documentType = document.getDocumentType();

			logger.info("Check author");
			if (author.getId() == null) {
				logger.info("Insert author");
				BigInteger authorId = query.insert(connection, AuthorQueries.INSERT_AUTHOR, handler, author.getName(),
						author.getFirstSurname(), author.getSecondSurname());

				author.setId(authorId.intValue());
			}

			logger.info("Check genre");
			if (genre.getId() == null) {
				logger.info("Insert genre");
				BigInteger genreId = query.insert(connection, GenreQueries.INSERT_GENRE, handler, genre.getName());

				genre.setId(genreId.intValue());
			}

			logger.info("Check editorial");
			if (editorial.getId() == null) {
				logger.info("Insert editorial");
				BigInteger editorialId = query.insert(connection, EditorialQueries.INSERT_EDITORIAL, handler,
						editorial.getName());

				editorial.setId(editorialId.intValue());
			}

			logger.info("Insert delivery time");
			BigInteger deliveryTimeId = query.insert(connection, DeliveryTimeQueries.INSERT_DELIVERY_TIME, handler,
					deliveryTime.getTime(), deliveryTime.getUnit());

			logger.info("Insert document");
			BigInteger documentId = query.insert(connection, DocumentQueries.INSERT_DOCUMENT, handler,
					document.getTitle(), document.getPrice(), document.getPageNumber(), author.getId(), genre.getId(),
					deliveryTimeId.intValue(), editorial.getId(), format.getId(), documentType.getId(),
					document.getThumbnailUrl());

			logger.info("Commit transaction");
			connection.commit();

			logger.info("Dao End: saveDocument");
			return documentId.intValue();
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());
			try {
				logger.info("Rolling back");
				connection.rollback();
			} catch (SQLException e1) {
				logger.severe("Error rolling back, database could be unstable: " + e1.getMessage());
				e1.printStackTrace();
			}
		} finally {
			try {
				logger.info("Closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.severe("Error closing connection: " + e.getMessage());
				e.printStackTrace();
			}
		}

		throw new QueryExecutionException("No se ha podido guardar el registro, intente de nuevo", null);
	}

	public DocumentDTO getDocumentById(Long id) throws QueryExecutionException {
		logger.info("Dao Start: getDocumentId");

		QueryRunner runner = new QueryRunner();
		ResultSetHandler<DocumentDTO> handler = new DocumentHandler();

		try (Connection connection = MainDataSource.getConnection()) {

			DocumentDTO document = runner.query(connection, DocumentQueries.GET_DOCUMENT_BY_ID, handler, id);

			logger.info("Dao End: getDocumentId");
			return document;
		} catch (SQLException ex) {
			logger.severe("Dao Error: " + ex.getMessage());
			throw new QueryExecutionException();
		}
	}

	public void deleteDocumentById(Long id) throws QueryExecutionException {
		logger.info("Dao Start: deleteDocumentById");
		Connection connection = null;

		try {
			connection = MainDataSource.getConnection();
		} catch (SQLException e) {
			throw new QueryExecutionException();
		}

		try {
			connection.setAutoCommit(false);

			QueryRunner runner = new QueryRunner();
			ScalarHandler<Integer> handler = new ScalarHandler<Integer>();

			logger.info("Get delivery time by document id: " + id);
			Integer deliveryTimeId = runner.query(connection, DocumentQueries.GET_DELIVERY_TIME_BY_DOCUMENT_ID, handler,
					id);

			if (deliveryTimeId == null) {
				throw new SQLException(
						"Inconsistencia en la base de datos, no se encontró un registro con deliveryTimeId: "
								+ deliveryTimeId);
			}

			logger.info("Delete document by id: " + id);
			int documentsDeleted = runner.update(connection, DocumentQueries.DELETE_DOCUMENT_BY_ID, id);

			if (documentsDeleted == 0) {
				throw new SQLException("No se encontró un documento con id: " + id);
			}

			logger.info("Delete delivery time by id: " + deliveryTimeId);
			int timesDeleted = runner.update(connection, DeliveryTimeQueries.DELETE_DELIVERY_TIME_BY_ID,
					deliveryTimeId);

			if (timesDeleted != 1) {
				throw new SQLException(
						"Inconsistencia en la base de datos, no se encontró un registro con deliveryTimeId: "
								+ deliveryTimeId);
			}

			logger.info("Commit changes");
			connection.commit();
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());
			try {
				logger.info("Rolling back");
				connection.rollback();
			} catch (SQLException e1) {
				logger.severe("Error rolling back, database could be unstable: " + e1.getMessage());
				e1.printStackTrace();
			}

			throw new QueryExecutionException("No se ha podido eliminar el documento", e.getMessage());
		} finally {
			try {
				logger.info("Closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.severe("Error closing connection: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public String getTitleById(Long id) throws QueryExecutionException {
		logger.info("Dao Start: getTitleById");

		try (Connection connection = MainDataSource.getConnection()) {
			QueryRunner runner = new QueryRunner();
			ScalarHandler<String> handler = new ScalarHandler<String>();

			String title = runner.query(connection, DocumentQueries.GET_DOCUMENT_TITLE_BY_ID, handler, id);

			logger.info("Dao End: getTitleById");
			return title;
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());
			throw new QueryExecutionException();
		}
	}

	public void updateDocumentById(DocumentDTO document) throws QueryExecutionException {
		logger.info("Dao Start: updateDocumentById");

		Connection connection = null;
		try {
			connection = MainDataSource.getConnection();
		} catch (SQLException e1) {
			logger.severe("Dao Error: " + e1.getMessage());
			throw new QueryExecutionException();
		}

		try {
			connection.setAutoCommit(false);
			QueryRunner runner = new QueryRunner();

			int updated = 0;

			DeliveryTimeDTO deliveryTime = document.getDeliveryTime();

			updated = runner.update(connection, DeliveryTimeQueries.UPDATE_DELIVERY_TIME, deliveryTime.getTime(),
					deliveryTime.getUnit(), deliveryTime.getId());

			if (updated == 0) {
				throw new SQLException("No se encontró el tiempo de entrega con id: " + deliveryTime.getId());
			}

			if (document.getThumbnailUrl() != null) {
				updated = runner.update(connection, DocumentQueries.updateBasicData(true), document.getTitle(),
						document.getPrice(), document.getPageNumber(), document.getFormat().getId(),
						document.getDocumentType().getId(), document.getThumbnailUrl(), document.getId());
			} else {
				updated = runner.update(connection, DocumentQueries.updateBasicData(false), document.getTitle(),
						document.getPrice(), document.getPageNumber(), document.getFormat().getId(),
						document.getDocumentType().getId(), document.getId());
			}

			if (updated == 0) {
				throw new SQLException("No se encontró el documento con id: " + document.getId());
			}

			logger.info("Commiting changes");
			connection.commit();
			
			logger.info("Dao End: updateDocumentById");
		} catch (SQLException e) {
			logger.severe("Dao Error: " + e.getMessage());

			try {
				logger.info("Rolling back changes");
				connection.rollback();
			} catch (SQLException e1) {
				logger.severe("Error rolling back, database could be unstable: " + e.getMessage());
				e1.printStackTrace();
			}

			throw new QueryExecutionException("No se ha podido actualizar el documento", e.getMessage());
		} finally {
			try {
				logger.info("Closing connection");
				connection.close();
			} catch (SQLException e) {
				logger.severe("Error closing connection: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
