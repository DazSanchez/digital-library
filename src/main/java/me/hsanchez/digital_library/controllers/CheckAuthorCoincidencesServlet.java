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
 * Servlet implementation class CheckAuthorCoincidencesServlet
 */
@WebServlet("/document/create/author-coincidences")
public class CheckAuthorCoincidencesServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(CheckAuthorCoincidencesServlet.class.getCanonicalName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckAuthorCoincidencesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: GET /document/create/author-coincidences");

		try {
			DocumentDTO document = SessionUtils.getDocument(request);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/document/coincidences/author.jsp");
			request.setAttribute("current", document.getAuthor());

			logger.info("Controller End: GET /document/create/author-coincidences");
			dispatcher.forward(request, response);
		} catch (PreRequirementException e) {
			logger.warning("Warning: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + e.getUrl());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("Controller Start: POST /document/create/author-coincidences");
		String selected = req.getParameter("selectedAuthor");

		try {
			DocumentDTO document = SessionUtils.getDocument(req);

			if (selected != null) {
				document.getAuthor().setId(BigInteger.valueOf(Long.parseLong(selected)));
			}
			
			List<?> genres = (List<?>) req.getAttribute("genreCoincidences");
			List<?> editorials = (List<?>) req.getAttribute("editorialCoincidences");
			
			logger.info("Controller End: POST /document/create/author-coincidences");
			if(genres != null && !genres.isEmpty()) {
				resp.sendRedirect(req.getContextPath() + "/document/create/genre-coincidences");
			} else if(editorials != null && !editorials.isEmpty()) {
				resp.sendRedirect(req.getContextPath() + "/document/create/editorial-coincidences");
			} else {
				req.getRequestDispatcher("/document/create/save").forward(req, resp);
			}
		} catch (PreRequirementException e) {
			logger.warning("Warning: " + e.getMessage());
			resp.sendRedirect(req.getContextPath() + e.getUrl());
		}
	}

}
