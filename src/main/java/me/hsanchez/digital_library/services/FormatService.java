package me.hsanchez.digital_library.services;

import java.util.List;
import java.util.logging.Logger;

import me.hsanchez.digital_library.dao.FormatDAO;
import me.hsanchez.digital_library.dto.FormatDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class FormatService {
	private Logger logger = Logger.getLogger(FormatService.class.getName());
	
	private FormatDAO formatDAO;
	
	public FormatService() {
		this.formatDAO = new FormatDAO();
	}
	
	public List<FormatDTO> getFormats() throws QueryExecutionException {
		logger.info("Service Start: getFormats");
		
		List<FormatDTO> results = this.formatDAO.getFormats();
		
		logger.info("Service End: getFormats");
		return results;
	}
}
