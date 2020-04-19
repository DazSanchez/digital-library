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
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.services.DocumentsService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class SaveDocumentServlet
 */
@WebServlet("/document/create/save")
public class SaveDocumentServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(SaveDocumentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	private DocumentsService documentsService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveDocumentServlet() {
		super();
		this.documentsService = new DocumentsService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: POST /document/create/save");

		try {
			DocumentDTO document = SessionUtils.getDocument(request);
			SessionUtils.removeDocument(request);

			BigInteger documentId = this.documentsService.saveDocument(document);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/document/save.jsp");
			request.setAttribute("documentId", documentId);

			logger.info("Controller End: POST /document/create/save");
			dispatcher.forward(request, response);
		} catch (PreRequirementException e) {
			logger.warning("Warning: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + e.getUrl());
		} catch (QueryExecutionException e) {
			logger.severe("Controller Error: " + e.getTechnicalReason());
			request.setAttribute("error", e.getMessage());
		}
	}

}
