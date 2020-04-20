package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.hsanchez.digital_library.dto.DocumentTypeDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.services.DocumentTypeService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class DeleteDocumentSearch
 */
@WebServlet("/document/delete/search")
public class DeleteDocumentSearchServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(DeleteDocumentSearchServlet.class.getName());
	
	private static final long serialVersionUID = 1L;
	
	private DocumentTypeService documentTypeService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDocumentSearchServlet() {
        super();
        this.documentTypeService = new DocumentTypeService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Controller Start: GET /document/delete-search");
		
		boolean isLoggedIn = SessionUtils.isLoggedIn(request);
		
		if(!isLoggedIn) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		try {
			List<DocumentTypeDTO> types = this.documentTypeService.getDocumentTypes();
			request.setAttribute("types", types);
		} catch (QueryExecutionException e) {
			request.setAttribute("error", "Ha ocurrido un error obteniendo los tipos de documento: " + e.getMessage());
		}
		
		logger.info("Controller End: GET /document/delete-search");
		request.getRequestDispatcher("/document/delete/search/index.jsp").forward(request, response);
	}

}
