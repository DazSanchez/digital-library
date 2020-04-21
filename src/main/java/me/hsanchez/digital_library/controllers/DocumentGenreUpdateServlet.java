package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.GenreDTO;
import me.hsanchez.digital_library.exceptions.PreRequirementException;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.services.GenreService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class DocumentGenreUpdateServlet
 */
@WebServlet("/document/genre/update")
public class DocumentGenreUpdateServlet extends HttpServlet {
	private Logger logger = Logger.getAnonymousLogger();

	private static final long serialVersionUID = 1L;

	private GenreService genreService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DocumentGenreUpdateServlet() {
		super();
		this.genreService = new GenreService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: GET /document/genre/update");

		DocumentDTO document = null;

		try {
			logger.info("Get target document from session");
			document = SessionUtils.getDocument(request);
			request.setAttribute("document", document);
		} catch (PreRequirementException e) {
			response.sendRedirect(request.getContextPath() + e.getUrl());
			return;
		}

		this.renderFormPage(request, response, new ArrayList<String>());
		logger.info("Controller End: GET /document/genre/update");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: POST /document/genre/update");

		DocumentDTO document = null;

		try {
			logger.info("Get target document from session");
			document = SessionUtils.getDocument(request);
			request.setAttribute("document", document);
		} catch (PreRequirementException e) {
			response.sendRedirect(request.getContextPath() + e.getUrl());
			return;
		}

		GenreDTO genre = document.getGenre();
		genre.setName(request.getParameter("genre"));

		try {
			this.genreService.updateGenre(genre);
		} catch (QueryExecutionException e) {
			logger.info("Controller Error: " + e.getMessage() + " - " + e.getTechnicalReason());

			List<String> errors = new ArrayList<String>();
			errors.add("Error: " + e.getMessage());

			this.renderFormPage(request, response, errors);
			return;
		}
		
		logger.info("Controller End: POST /document/genre/update");
		response.sendRedirect(request.getContextPath() + "/document/update/detail/" + document.getId());
	}

	private void renderFormPage(HttpServletRequest request, HttpServletResponse response, List<String> errors)
			throws ServletException, IOException {
		request.setAttribute("errors", errors);
		request.setAttribute("hasErrors", errors.size() > 0);
		request.getRequestDispatcher("/document/update/genre.jsp").forward(request, response);
	}

}
