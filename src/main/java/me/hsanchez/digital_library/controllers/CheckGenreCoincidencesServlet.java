package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.GenreDTO;
import me.hsanchez.digital_library.exceptions.PreRequirementException;
import me.hsanchez.digital_library.services.GenreService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class CheckGenreCoincidencesServlet
 */
@WebServlet("/document/create/genre-coincidences")
public class CheckGenreCoincidencesServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(CheckGenreCoincidencesServlet.class.getName());
	private static final long serialVersionUID = 1L;
	
	private GenreService genreService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckGenreCoincidencesServlet() {
        super();
        this.genreService = new GenreService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Controller Start: GET /document/create/genre-coincidences");
		
		try {
			DocumentDTO document = SessionUtils.getDocument(request);
			
			List<GenreDTO> results = this.genreService.getGenreCoincidences(document.getGenre());
			
			String fowardUrl = "/document/create/editorial-coincidences";
			
			if(results.isEmpty()) {
				logger.info("No coincidences found");
			} else {
				logger.info("Found coincidences: " + results.size());
				request.setAttribute("genres", results);
				request.setAttribute("current", document.getAuthor());
				fowardUrl = "/document/genre-coincidences.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(fowardUrl);
			
			logger.info("Controller End: GET /document/create/genre-coincidences");
			dispatcher.forward(request, response);
		} catch(PreRequirementException e) {
			logger.warning("Warning: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + e.getUrl());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
