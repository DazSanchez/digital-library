package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.math.BigInteger;
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
 * Servlet implementation class CheckEditorialCoincidencesServlet
 */
@WebServlet("/document/create/editorial-coincidences")
public class CheckEditorialCoincidencesServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(CheckEditorialCoincidencesServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckEditorialCoincidencesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: GET /document/create/editorial-coincidences");

		try {
			DocumentDTO document = SessionUtils.getDocument(request);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/document/coincidences/editorial.jsp");
			request.setAttribute("current", document.getEditorial());

			logger.info("Controller End: GET /document/create/editorial-coincidences");
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
		logger.info("Controller Start: POST /document/create/editorial-coincidences");

		String selected = request.getParameter("selectedEditorial");

		try {
			DocumentDTO document = SessionUtils.getDocument(request);

			if (selected != null) {
				document.getEditorial().setId(BigInteger.valueOf(Long.parseLong(selected)));
			}

			logger.info("Controller End: POST /document/create/editorial-coincidences");
			request.getRequestDispatcher("/document/create/save").forward(request, response);
		} catch (PreRequirementException e) {
			logger.warning("Warning: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + e.getUrl());
		}
	}

}
