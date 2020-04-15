package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.services.DocumentsService;
import me.hsanchez.digital_library.utils.Utils;

/**
 *
 * @author hsanchez
 */
@WebServlet(name = "SearchResultsServlet", urlPatterns = { "/search_results" })
public class SearchResultsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3010919572264452769L;
	private DocumentsService documentService;

	public SearchResultsServlet() {
		super();
		this.documentService = new DocumentsService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CONTROLLER - Start: GET search_results");
		String type = req.getParameter("type");
		String title = req.getParameter("title");
		String page = req.getParameter("page");
		String perPage = req.getParameter("per_page");

		if (title == null || type == null || title.isEmpty() || type.isEmpty() || !Utils.isInteger(type)) {
			resp.sendRedirect(req.getContextPath() + "/search");
			return;
		}

		int parsedType = Integer.parseInt(type, 10);
		int parsedPage = page == null || page.isEmpty() ? 1 : Integer.parseInt(page);
		int parsedPerPage = perPage == null || perPage.isEmpty() ? 10 : Integer.parseInt(perPage);

		req.setAttribute("title", title);

		try {
			List<DocumentDTO> documents = this.documentService.getDocumentsBy(parsedType, title, parsedPage,
					parsedPerPage);

			req.setAttribute("documents", documents);
			req.setAttribute("isEmpty", documents.isEmpty());
			req.setAttribute("currentPage", parsedPage);
		} catch (QueryExecutionException e) {
			req.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search/results.jsp");
		System.out.println("CONTROLLER - End: GET search_results");
		dispatcher.forward(req, resp);
	}

}
