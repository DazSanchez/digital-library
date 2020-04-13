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
import me.hsanchez.digital_library.dto.UserDTO;
import me.hsanchez.digital_library.exceptions.AuthenticationException;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.services.UsersService;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    private final UsersService usersService;
    
    public LoginServlet() {
        this.usersService = new UsersService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CONTROLLER - Start: GET login");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/login.jsp");
        
        System.out.println("CONTROLLER - End: GET login");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CONTROLLER - Start: POST login");
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        try {
            UserDTO user = this.usersService.checkCredentials(username, password);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/dashboard.jsp");
            
            req.setAttribute("user", user);
            
            System.out.println("CONTROLLER - End: POST login");
            dispatcher.forward(req, resp);
        } catch (QueryExecutionException | AuthenticationException ex) {
            RequestDispatcher dispatcher =  req.getRequestDispatcher("/admin/login.jsp");
            
            req.setAttribute("error", ex.getMessage());
        
            System.out.println("CONTROLLER - Error: " + ex.getMessage());
            dispatcher.forward(req, resp);
        }
    }
}
