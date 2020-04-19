/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		boolean isLoggedIn = session != null && session.getAttribute("user") != null;
		
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search/index.jsp");
        req.setAttribute("loggedIn", isLoggedIn);
        dispatcher.forward(req, resp);
    }

}
