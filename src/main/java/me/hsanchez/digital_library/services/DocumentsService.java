/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.services;

import java.util.List;
import java.util.logging.Logger;

import me.hsanchez.digital_library.dao.DocumentDAO;
import me.hsanchez.digital_library.dto.DocumentDTO;
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

	public List<DocumentDTO> getDocumentsBy(int type, String title, int page, int perPage) throws QueryExecutionException {
		logger.info("Service Start: getDocumentsBy");

		List<DocumentDTO> documents = this.documentDAO.getDocumentsBy(type, title, page, perPage);

		logger.info("Service End: getDocumentsBy");
		return documents;
	}
	
	public Long saveDocument(DocumentDTO document) throws QueryExecutionException {
		logger.info("Service Start: saveDocument");
		
		Long documentId = this.documentDAO.saveDocument(document);
		
		logger.info("Service Start: saveDocument");
		return documentId;
	}

}
