package me.hsanchez.digital_library.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.hsanchez.digital_library.utils.Utils;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
@WebServlet(name = "DocumentDetailServlet", urlPatterns = {"/detail/*"})
public class DocumentDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        
        if(!Utils.isInteger(id)) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/document/detail.jsp");
        dispatcher.forward(req, resp);
    }
}
