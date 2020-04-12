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
        
        UserDTO user = this.usersService.checkCredentials(username, password);
        
        if(user == null) {
            RequestDispatcher dispatcher =  req.getRequestDispatcher("/admin/login.jsp");
            
            req.setAttribute("error", "Nombre de usuario o contraseña incorrecto");
        
            System.out.println("CONTROLLER - Error: Bad credentials");
            dispatcher.forward(req, resp);
        }
    }
}
