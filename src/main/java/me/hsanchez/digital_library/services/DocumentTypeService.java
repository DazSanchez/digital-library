package me.hsanchez.digital_library.services;

import java.util.List;
import java.util.logging.Logger;

import me.hsanchez.digital_library.dao.DocumentTypeDAO;
import me.hsanchez.digital_library.dto.DocumentTypeDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class DocumentTypeService {
	private Logger logger = Logger.getLogger(DocumentTypeService.class.getName());

	private DocumentTypeDAO documentTypeDAO;

	public DocumentTypeService() {
		this.documentTypeDAO = new DocumentTypeDAO();
	}

	public List<DocumentTypeDTO> getDocumentTypes() throws QueryExecutionException {
		logger.info("Service Start: getDocumentTypes");

		List<DocumentTypeDTO> list = this.documentTypeDAO.getDocumentTypes();

		logger.info("Service End: getDocumentTypes");
		return list;
	}
}
