package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.services.DocumentsService;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
@WebServlet("/document/detail/*")
public class DocumentDetailServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(DocumentDetailServlet.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 6335319428651320181L;

	private DocumentsService documentsService;

	public DocumentDetailServlet() {
		super();
		this.documentsService = new DocumentsService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("Controller Start: /document/detail/:id");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/document/detail.jsp");

		String id = req.getPathInfo().substring(1);

		logger.info("id: " + id);

		if (!NumberUtils.isCreatable(id)) {
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}

		DocumentDTO document = null;

		try {
			document = this.documentsService.getDocumentById(Long.valueOf(id));
		} catch (NumberFormatException e) {
			logger.severe("Controller Error: " + e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		} catch (QueryExecutionException e) {
			req.setAttribute("error", e.getMessage());
		}
		
		req.setAttribute("document", document);
		req.setAttribute("documentId", id);
		
		logger.info("Controller End: /document/detail/:id");
		dispatcher.forward(req, resp);
	}
}
