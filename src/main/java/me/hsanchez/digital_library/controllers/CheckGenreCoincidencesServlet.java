package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.exceptions.PreRequirementException;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class CheckGenreCoincidencesServlet
 */
@WebServlet("/document/create/genre-coincidences")
public class CheckGenreCoincidencesServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(CheckGenreCoincidencesServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckGenreCoincidencesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: GET /document/create/genre-coincidences");

		try {
			DocumentDTO document = SessionUtils.getDocument(request);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/document/coincidences/genre.jsp");
			request.setAttribute("current", document.getGenre());

			logger.info("Controller End: GET /document/create/genre-coincidences");
			dispatcher.forward(request, response);
		} catch (PreRequirementException e) {
			logger.warning("Warning: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + e.getUrl());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: POST /document/create/genre-coincidences");

		String selected = request.getParameter("selectedGenre");

		try {
			DocumentDTO document = SessionUtils.getDocument(request);

			if (selected != null) {
				document.getGenre().setId(BigInteger.valueOf(Long.parseLong(selected)));
			}

			List<?> editorials = (List<?>) request.getAttribute("editorialCoincidences");

			logger.info("Controller End: POST /document/create/genre-coincidences");
			if (editorials != null && !editorials.isEmpty()) {
				response.sendRedirect(request.getContextPath() + "/document/create/editorial-coincidences");
			} else {
				request.getRequestDispatcher("/document/create/save").forward(request, response);
			}
		} catch (PreRequirementException e) {
			logger.warning("Warning: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + e.getUrl());
		}
	}

}
