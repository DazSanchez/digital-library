package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.SearchResultDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.services.DocumentsService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class UpdateDocumentSearchResultServlet
 */
@WebServlet("/document/update/search/results")
public class UpdateDocumentSearchResultServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(UpdateDocumentSearchResultServlet.class.getName());

	private static final long serialVersionUID = 1L;

	private DocumentsService documentsService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDocumentSearchResultServlet() {
		super();
		this.documentsService = new DocumentsService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: GET /document/update/search/results");

		boolean isLoggedIn = SessionUtils.isLoggedIn(request);

		if (!isLoggedIn) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String type = request.getParameter("type");
		String title = request.getParameter("title");
		String page = request.getParameter("page");
		String perPage = request.getParameter("per_page");

		if (type == null || title == null || type.isEmpty() || title.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/document/update/search");
			return;
		}

		request.setAttribute("title", title);

		int parsedType = Integer.parseInt(type, 10);
		int parsedPage = page == null || page.isEmpty() ? 1 : Integer.parseInt(page);
		int parsedPerPage = perPage == null || perPage.isEmpty() ? 10 : Integer.parseInt(perPage);

		try {
			SearchResultDTO<DocumentDTO> result = this.documentsService.getDocumentsBy(parsedType, title, parsedPage,
					parsedPerPage);

			List<DocumentDTO> documents = result.getResults();

			request.setAttribute("documents", documents);
			request.setAttribute("isEmpty", documents.isEmpty());
			request.setAttribute("currentPage", parsedPage);
			request.setAttribute("hasPrev", parsedPage > 1);
			request.setAttribute("hasNext", result.getTotal() - (parsedPage * parsedPerPage) > 0);
		} catch (QueryExecutionException e) {
			request.setAttribute("error", e.getMessage());
		}

		logger.info("Controller End: GET /document/update/search/results");
		request.getRequestDispatcher("/document/update/search/results.jsp").forward(request, response);
	}
}
