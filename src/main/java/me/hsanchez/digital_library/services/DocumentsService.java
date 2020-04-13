/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.services;

import java.util.List;

import me.hsanchez.digital_library.dao.DocumentDAO;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class DocumentsService {

	private DocumentDAO documentDAO;

	public DocumentsService() {
		this.documentDAO = new DocumentDAO();
	}

	public List<DocumentDTO> getDocumentsBy(int type, String title, int page, int perPage) throws QueryExecutionException {
		System.out.println("SERVICE - Start: getDocumentsBy");

		List<DocumentDTO> documents = this.documentDAO.getDocumentsBy(type, title, page, perPage);

		System.out.println("SERVICE - End: getDocumentsBy");
		return documents;
	}

}
