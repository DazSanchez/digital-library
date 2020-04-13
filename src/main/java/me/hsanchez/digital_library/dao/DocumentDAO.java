/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import me.hsanchez.digital_library.datasource.MainDataSource;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.handlers.SearchResultHandler;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class DocumentDAO {
	public List<DocumentDTO> getDocumentsBy(int type, String title, int page, int perPage)
			throws QueryExecutionException {
		System.out.println("DAO - Start: getDocumentsBy");
		try (Connection connection = MainDataSource.getConnection()) {
			QueryRunner queryRunner = new QueryRunner();

			int offset = page * perPage;

			List<DocumentDTO> documents = queryRunner.query(connection,
					"SELECT d.ID id, TITLE title, PRICE price,"
							+ "a.NAME name, a.FIRST_SURNAME firstSurname, a.SECOND_SURNAME secondSurname,"
							+ "g.NAME genreName FROM DOCUMENT d INNER JOIN AUTHOR a ON d.AUTHOR_ID = a.ID "
							+ "INNER JOIN CAT_GENRE g ON d.GENRE_ID = g.ID "
							+ "WHERE d.DOCUMENT_TYPE_ID = ? AND d.TITLE LIKE ? LIMIT ?, ?",
					new SearchResultHandler(), type, "%"+title+"%", offset, perPage);

			System.out.println("DAO - End: getDocumentsBy");
			return documents;
		} catch (SQLException ex) {
			System.err.println("DAO - Error: " + ex.getMessage());
			throw new QueryExecutionException();
		}
	}

	public void getDocumentById() {
//		queryRunner.query(connection, "SELECT d.ID id, TITLE title, PRICE price, PAGE_NUMBER pageNumber, "
//		+ "AUTHOR_ID authorId, a.NAME name, a.FIRST_SURNAME firstSurname, a.SECOND_SURNAME secondSurname,"
//		+ "GENRE_ID genreId, g.NAME genreName, f.NAME format,"
//		+ "DELIVERY_TIME_ID deliveryTimeId, dt.`TIME` `time`, dt.UNIT unit,"
//		+ "e.ID editorialId, e.NAME editorialName FROM DOCUMENT d"
//		+ "INNER JOIN AUTHOR a ON d.AUTHOR_ID = a.ID INNER JOIN CAT_GENRE g ON d.GENRE_ID = g.ID"
//		+ "INNER JOIN CAT_FORMAT f ON d.FORMAT_ID = f.ID"
//		+ "INNER JOIN DELIVERY_TIME dt ON d.DELIVERY_TIME_ID = dt.ID"
//		+ "INNER JOIN EDITORIAL e ON d.EDITORIAL_ID = e.ID;", null);
	}
}
