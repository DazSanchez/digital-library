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
import me.hsanchez.digital_library.dto.EditorialDTO;
import me.hsanchez.digital_library.exceptions.PreRequirementException;
import me.hsanchez.digital_library.services.EditorialService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class CheckEditorialCoincidencesServlet
 */
@WebServlet("/document/create/editorial-coincidences")
public class CheckEditorialCoincidencesServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(CheckEditorialCoincidencesServlet.class.getName());
	private static final long serialVersionUID = 1L;
	
	private EditorialService editorialService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEditorialCoincidencesServlet() {
        super();
        this.editorialService = new EditorialService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Controller Start: GET /document/create/editorial-coincidences");
		
		try {
			DocumentDTO document = SessionUtils.getDocument(request);
			
			List<EditorialDTO> results = this.editorialService.getEditorialCoincidences(document.getEditorial());
			
			String fowardUrl = "/document/create/save";
			
			if(results.isEmpty()) {
				logger.info("No coincidences found");
			} else {
				logger.info("Found coincidences: " + results.size());
				request.setAttribute("editorials", results);
				request.setAttribute("current", document.getAuthor());
				fowardUrl = "/document/editorial-coincidences.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(fowardUrl);
			
			logger.info("Controller End: GET /document/create/editorial-coincidences");
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
