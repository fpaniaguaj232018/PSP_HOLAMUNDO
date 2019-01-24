/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernandopaniagua.controller;

import com.fernandopaniagua.exceptions.ConnectionNotEnabledException;
import com.fernandopaniagua.exceptions.DuplicateEmailException;
import com.fernandopaniagua.exceptions.UnknownException;
import com.fernandopaniagua.model.User;
import com.fernandopaniagua.model.UsersManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreadorUsuariosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Recogida de info
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String ip = request.getRemoteAddr();
        //Llamada al business
        User nuevoUser = new User(email,password,name,surname,ip);
        UsersManager um = new UsersManager();
        try {
            um.addUser(nuevoUser);
            //Generación de la salida
            request.setAttribute("nuevoUsuario", nuevoUser);
            RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/haidobien.jsp");
                dispatcher.forward(request, response);
        } catch (ConnectionNotEnabledException ex) {
            request.setAttribute("mensaje", "CONEXION NO DISPONIBLE");
            RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/haidomal.jsp");
                dispatcher.forward(request, response);
        } catch (DuplicateEmailException ex) {
            request.setAttribute("mensaje", "EMAIL DUPLICADO");
            RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/haidomal.jsp");
                dispatcher.forward(request, response);
        } catch (UnknownException ex) {
            request.setAttribute("mensaje", ex.getMessage());
            RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/haidomal.jsp");
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
