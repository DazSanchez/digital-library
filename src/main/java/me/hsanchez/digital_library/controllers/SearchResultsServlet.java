package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hsanchez
 */
@WebServlet(name = "SearchResultsServlet", urlPatterns = {"/search_results"})
public class SearchResultsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CONTROLLER - Start: GET search_results");
        String type = req.getParameter("type");
        String title = req.getParameter("title");
        
        List<Object> results = new ArrayList<>();
        
        req.setAttribute("title", title);
        req.setAttribute("results", results);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search/results.jsp");
        System.out.println("CONTROLLER - End: GET search_results");
        dispatcher.forward(req, resp);
    }

}
