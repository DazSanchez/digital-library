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

import me.hsanchez.digital_library.dto.AuthorDTO;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.exceptions.PreRequirementException;
import me.hsanchez.digital_library.services.AuthorService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class CheckAuthorCoincidencesServlet
 */
@WebServlet("/document/create/author-coincidences")
public class CheckAuthorCoincidencesServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(CheckAuthorCoincidencesServlet.class.getCanonicalName());
	private static final long serialVersionUID = 1L;
	
	private AuthorService authorService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAuthorCoincidencesServlet() {
        super();
        this.authorService = new AuthorService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Controller Start: GET /document/create/author-coincidences");
		
		try {
			DocumentDTO document = SessionUtils.getDocument(request);
			
			List<AuthorDTO> results = this.authorService.getAuthorCoincidences(document.getAuthor());
			
			String fowardUrl = "/document/create/genre-coincidences";
			
			if(results.isEmpty()) {
				logger.info("No coincidences found");
			} else {
				logger.info("Found coincidences: " + results.size());
				request.setAttribute("authors", results);
				request.setAttribute("current", document.getAuthor());
				fowardUrl = "/document/author-coincidences.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(fowardUrl);
			
			logger.info("Controller End: GET /document/create/author-coincidences");
			dispatcher.forward(request, response);
		} catch(PreRequirementException e) {
			logger.warning("Warning: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + e.getUrl());
		}
	}

}
