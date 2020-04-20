/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 *
 * @author hsanchez
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6462016077479083983L;
	
	private DocumentTypeService documentTypeService;
	
	public SearchServlet() {
		super();
		this.documentTypeService = new DocumentTypeService();
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isLoggedIn = SessionUtils.isLoggedIn(req);
		
		try {
			List<DocumentTypeDTO> types = this.documentTypeService.getDocumentTypes();
			req.setAttribute("types", types);
		} catch (QueryExecutionException e) {
			req.setAttribute("error", "Ha ocurrido un error obteniendo los tipos de documento: " + e.getMessage());
		}
		
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search/index.jsp");
        req.setAttribute("loggedIn", isLoggedIn);
        dispatcher.forward(req, resp);
    }

}
