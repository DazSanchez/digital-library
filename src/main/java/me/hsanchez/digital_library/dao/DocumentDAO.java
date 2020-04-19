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
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.handlers.DocumentHandler;
import me.hsanchez.digital_library.handlers.SearchResultHandler;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class DocumentDAO {
	private Logger logger = Logger.getLogger(DocumentDAO.class.getName());

	public List<DocumentDTO> getDocumentsBy(int type, String title, int page, int perPage)
			throws QueryExecutionException {
		logger.info("Dao Start: getDocumentsBy");
		try (Connection connection = MainDataSource.getConnection()) {
			QueryRunner queryRunner = new QueryRunner();

			int offset = (page - 1) * perPage;

			List<DocumentDTO> documents = queryRunner.query(connection, DocumentQueries.GET_DOCUMENTS,
					new SearchResultHandler(), type, "%" + title + "%", offset, perPage);

			logger.info("Dao End: getDocumentsBy");
			return documents;
		} catch (SQLException ex) {
			logger.severe("Dao Error: " + ex.getMessage());
			throw new QueryExecutionException();
		}
	}

	public BigInteger saveDocument(DocumentDTO document) throws QueryExecutionException {
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

			ScalarHandler<BigInteger> handler = new ScalarHandler<BigInteger>();

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

				author.setId(authorId);
			}

			logger.info("Check genre");
			if (genre.getId() == null) {
				logger.info("Insert genre");
				BigInteger genreId = query.insert(connection, GenreQueries.INSERT_GENRE, handler, genre.getName());

				genre.setId(genreId);
			}

			logger.info("Check editorial");
			if (editorial.getId() == null) {
				logger.info("Insert editorial");
				BigInteger editorialId = query.insert(connection, EditorialQueries.INSERT_EDITORIAL, handler,
						editorial.getName());

				editorial.setId(editorialId);
			}

			logger.info("Insert delivery time");
			BigInteger deliveryTimeId = query.insert(connection, DeliveryTimeQueries.INSERT_DELIVERY_TIME, handler,
					deliveryTime.getTime(), deliveryTime.getUnit());

			logger.info("Insert document");
			BigInteger documentId = query.insert(connection, DocumentQueries.INSERT_DOCUMENT, handler,
					document.getTitle(), document.getPrice(), document.getPageNumber(), author.getId(), genre.getId(),
					deliveryTimeId, editorial.getId(), format.getId(), documentType.getId(),
					document.getThumbnailUrl());

			logger.info("Commit transaction");
			connection.commit();

			logger.info("Dao End: saveDocument");
			return documentId;
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
}
