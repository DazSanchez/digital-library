/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.services;

import java.util.logging.Logger;

import me.hsanchez.digital_library.dao.DocumentDAO;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.SearchResultDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class DocumentsService {
	private Logger logger = Logger.getLogger(DocumentsService.class.getName());

	private DocumentDAO documentDAO;

	public DocumentsService() {
		this.documentDAO = new DocumentDAO();
	}

	public SearchResultDTO<DocumentDTO> getDocumentsBy(int type, String title, int page, int perPage)
			throws QueryExecutionException {
		logger.info("Service Start: getDocumentsBy");

		SearchResultDTO<DocumentDTO> result = this.documentDAO.getDocumentsBy(type, title, page, perPage);

		logger.info("Service End: getDocumentsBy");
		return result;
	}

	public Integer saveDocument(DocumentDTO document) throws QueryExecutionException {
		logger.info("Service Start: saveDocument");

		Integer documentId = this.documentDAO.saveDocument(document);

		logger.info("Service End: saveDocument");
		return documentId;
	}

	public DocumentDTO getDocumentById(Long id) throws QueryExecutionException {
		logger.info("Service Start: getDocumentById");

		DocumentDTO document = this.documentDAO.getDocumentById(id);

		logger.info("Service End: getDocumentById");
		return document;
	}

	public void deleteDocumentById(Long id) throws QueryExecutionException {
		logger.info("Service Start: deleteDocumentById");

		this.documentDAO.deleteDocumentById(id);

		logger.info("Service End: deleteDocumentById");
	}

	public String getTitleById(Long id) throws QueryExecutionException {
		logger.info("Service Start: getTitleById");

		String title = this.documentDAO.getTitleById(id);

		logger.info("Service End: getTitleById");
		return title;
	}
	
	public void updateDocumentById(DocumentDTO document) throws QueryExecutionException {
		logger.info("Service Start: updateDocumentById");
		
		this.documentDAO.updateDocumentById(document);
		
		logger.info("Service End: updateDocumentById");
	}

}
