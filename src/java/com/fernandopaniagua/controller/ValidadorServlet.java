package com.fernandopaniagua.controller;

import com.fernandopaniagua.exceptions.UserNotFoundException;
import com.fernandopaniagua.model.User;
import com.fernandopaniagua.model.UsersManager;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidadorServlet extends HttpServlet {

    protected void processRequest(
            HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        UsersManager um = new UsersManager();
        try {
            boolean isOk = um.checkCredential(new User(email, password));
            if (isOk){
                //OK
                RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/userok.html");
                dispatcher.forward(request, response);
            } else {
                //KO
                RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/userko.html");
                dispatcher.forward(request, response);
            }
        } catch (UserNotFoundException ex) {
            //KO
            RequestDispatcher dispatcher = 
                    getServletContext().getRequestDispatcher("/userko.html");
            dispatcher.forward(request, response);
        }
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
