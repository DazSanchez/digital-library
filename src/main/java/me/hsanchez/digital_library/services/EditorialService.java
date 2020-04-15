package me.hsanchez.digital_library.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.hsanchez.digital_library.dao.EditorialDAO;
import me.hsanchez.digital_library.dto.EditorialDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class EditorialService {
	private Logger logger = Logger.getLogger(EditorialService.class.getName());
	
	private EditorialDAO editorialDAO;
	
	public EditorialService() {
		this.editorialDAO = new EditorialDAO();
	}
	
	public List<EditorialDTO> getEditorialCoincidences(EditorialDTO editorial) {
		logger.info("Service Start: getEditorialCoincidences");
		
		try {
			List<EditorialDTO> results = this.editorialDAO.getEditorialCoincidences(editorial);
			logger.info("Service End: getEditorialCoincidences");
			
			return results;
		} catch (QueryExecutionException e) {
			logger.severe("Severe Error: " + e.getTechnicalReason());
			return new ArrayList<EditorialDTO>();
		}
	}
}
