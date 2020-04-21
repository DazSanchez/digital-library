package me.hsanchez.digital_library.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.hsanchez.digital_library.dao.AuthorDAO;
import me.hsanchez.digital_library.dto.AuthorDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class AuthorService {
	private static Logger logger = Logger.getLogger(AuthorService.class.getName());

	private AuthorDAO authorDAO;

	public AuthorService() {
		this.authorDAO = new AuthorDAO();
	}

	public List<AuthorDTO> getAuthorCoincidences(AuthorDTO author) {
		logger.info("Service Start: getAuthorCoincidences");
		try {
			List<AuthorDTO> results = this.authorDAO.findAuthorCoincidences(author);

			logger.info("Service End: getAuthorCoincidences");
			return results;
		} catch (QueryExecutionException e) {
			logger.severe("Service Error: " + e.getTechnicalReason());
			return new ArrayList<AuthorDTO>();
		}
	}

	public void updateAuthor(AuthorDTO author) throws QueryExecutionException {
		logger.info("Service Start: updateAuthor");

		this.authorDAO.updateAuthor(author);

		logger.info("Service End: updateAuthor");
	}
}
