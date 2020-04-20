package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.services.DocumentsService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class DeleteDocumentConfirmServlet
 */
@WebServlet("/document/delete/confirm/*")
public class DeleteDocumentConfirmServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(DeleteDocumentConfirmServlet.class.getName());

	private static final long serialVersionUID = 1L;

	private DocumentsService documentsService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteDocumentConfirmServlet() {
		super();
		this.documentsService = new DocumentsService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: GET /document/delete/confirm/:id");
		boolean isLoggedIn = SessionUtils.isLoggedIn(request);

		if (!isLoggedIn) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String id = request.getPathInfo().substring(1);

		if (!NumberUtils.isCreatable(id)) {
			response.sendRedirect(request.getContextPath() + "/document/delete/search");
			return;
		}

		String title = null;

		try {
			title = this.documentsService.getTitleById(Long.valueOf(id));
			if (title == null) {
				throw new QueryExecutionException("No se ha encontrado un documento con id: " + id, null);
			}
		} catch (NumberFormatException e) {
			logger.severe("Controller Error: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/document/delete/search");
			return;
		} catch (QueryExecutionException e) {
			request.setAttribute("error", e.getMessage());
		}

		request.setAttribute("title", title);
		logger.info("Controller End: GET /document/delete/confirm/:id");
		request.getRequestDispatcher("/document/delete/confirm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: POST /document/delete/confirm/:id");

		boolean isLoggedIn = SessionUtils.isLoggedIn(request);

		if (!isLoggedIn) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String id = request.getPathInfo().substring(1);
		String confirm = request.getParameter("confirm");

		if (!NumberUtils.isCreatable(id) || !Boolean.parseBoolean(confirm)) {
			response.sendRedirect(request.getContextPath() + "/document/delete/search");
			return;
		}

		try {
			this.documentsService.deleteDocumentById(Long.valueOf(id));
		} catch (NumberFormatException e) {
			logger.severe("Controller Error: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/document/delete/search");
			return;
		} catch (QueryExecutionException e) {
			request.setAttribute("error", e.getMessage());
		}

		logger.info("Controller End: POST /document/delete/confirm/:id");
		request.getRequestDispatcher("/document/delete/deleted.jsp").forward(request, response);
	}

}
