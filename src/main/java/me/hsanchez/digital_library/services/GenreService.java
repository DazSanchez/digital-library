package me.hsanchez.digital_library.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.hsanchez.digital_library.dao.GenreDAO;
import me.hsanchez.digital_library.dto.GenreDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;

public class GenreService {
	private Logger logger = Logger.getLogger(GenreService.class.getName());
	
	private GenreDAO genreDAO;
	
	public GenreService() {
		this.genreDAO = new GenreDAO();
	}
	
	public List<GenreDTO> getGenreCoincidences(GenreDTO genre) {
		logger.info("Service Start: getGenreCoincidences");
		
		try {
			List<GenreDTO> results = this.genreDAO.findCoincidences(genre);
			logger.info("Service End: getGenreCoincidences");
			return results;
		} catch (QueryExecutionException e) {
			logger.severe("Service Error: " + e.getTechnicalReason());
			return new ArrayList<GenreDTO>();
		}
	}
	
	public void updateGenre(GenreDTO genre) throws QueryExecutionException {
		logger.info("Service Start: updateGenre");
		
		this.genreDAO.updateGenre(genre);
		
		logger.info("Service End: updateGenre");
	}
}
