package me.hsanchez.digital_library.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.math.NumberUtils;

import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.services.DocumentsService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class UpdateDocumentMenuServlet
 */
@WebServlet("/document/update/detail/*")
public class UpdateDocumentMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DocumentsService documentsService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDocumentMenuServlet() {
        super();
        this.documentsService = new DocumentsService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isLoggedIn = SessionUtils.isLoggedIn(request);
		
		if(!isLoggedIn) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		String id = request.getPathInfo().substring(1);

		if (!NumberUtils.isCreatable(id)) {
			response.sendRedirect(request.getContextPath() + "/document/update/search");
			return;
		}
		
		DocumentDTO document = null;
		
		try {
			document = this.documentsService.getDocumentById(Long.valueOf(id));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		} catch (QueryExecutionException e) {
			request.setAttribute("error", e.getMessage());
		}
		
		HttpSession session = request.getSession(false);
		session.setAttribute("document", document);
		
		request.setAttribute("documentId", id);
		request.getRequestDispatcher("/document/update/index.jsp").forward(request, response);
	}

}
